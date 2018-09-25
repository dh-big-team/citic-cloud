package com.dhcc.citic.cloud.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.dhcc.citic.cloud.model.SysUser;
import com.google.code.kaptcha.Constants;


public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	
	private String captchaParam = "captchaCode"; //前台提交的验证码参数名  

    private String failureKeyAttribute = "shiroLoginFailure";  //验证失败后存储到的属性名  

    public String getCaptchaCode(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    /**
     * 每次登录都会到这里来，这里用来处理 不注销之前已登录用户下，再次登录
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, 
    		ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response))
        {
            if (isLoginSubmission(request, response))
            {
                //本次用户登陆账号
                String account = this.getUsername(request);
                Subject subject = this.getSubject(request, response);
                //之前登陆的用户
                SysUser user = (SysUser) subject.getPrincipal();
                //同一session登录不管账号是否一致都将原session注销
                if (account != null && user != null)
                {
                    //获取session，获取验证码
                    HttpServletRequest httpServletRequest=(HttpServletRequest) request;
                    HttpSession session= httpServletRequest.getSession();
                    String captchaCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
                    //注销登录，同时会使session失效
                    subject.logout();
                    //所以重新设置session，需要将验证码重新存入该session中
                    HttpSession session1= httpServletRequest.getSession();
                    session1.setAttribute(Constants.KAPTCHA_SESSION_KEY, captchaCode);
                }
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 重写FormAuthenticationFilter的onLoginSuccess方法
     * 指定的url传递进去，这样就实现了跳转到指定的页面；
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
            ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);//清理了session中保存的请求信息
        WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
        return false;
    }

    /**
     * 验证码验证
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 从session获取正确的验证码
        Session session = SecurityUtils.getSubject().getSession();
        //页面输入的验证码
        String captchaCode = getCaptchaCode(request);
        String validateCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 若验证码为空，返回true表示不通过
        if(captchaCode == null || validateCode==null) {
            return true;
        }
        //验证码校验，忽略大小写
        if(!captchaCode.toLowerCase().equals(validateCode.toLowerCase())) {
        	request.setAttribute(failureKeyAttribute, "kaptchaValidateFailed");  
            return true;
        }
        return super.onAccessDenied(request, response);
    }
    
    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

}
