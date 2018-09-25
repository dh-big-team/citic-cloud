package com.dhcc.citic.cloud.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.model.SysMenu;
import com.dhcc.citic.cloud.model.SysUser;



/**
 * 登录处理
 * 文件名称:     LoginCtrl.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年7月24日上午10:26:23 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年7月24日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Controller
public class LoginCtrl {
	
	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping({"/","/index"})
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		SysUser sysUser = (SysUser)SecurityUtils.getSubject().getSession().getAttribute("sysUser");
		view.addObject("user",sysUser);
		view.setViewName("index");
		return view;
	}
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping({"/main"})
	public String main(){
		return "main";
	}
	
	/**
	 * 登录处理
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path="/login")
	public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
		String exception = (String) request.getAttribute("shiroLoginFailure");
		String msg=null;
		if(exception!=null){
			if(UnknownAccountException.class.getName().equals(exception)){
				//账号不存在
				msg="账号或密码输入错误！";
			}else if(IncorrectCredentialsException.class.getName().equals(exception)){
				//密码不正确
				msg = "账号或密码输入错误！";
			}else if("kaptchaValidateFailed".equals(exception)){
				//验证码错误
				msg="验证码输入错误！";
			}else{
				msg = exception;
			}
		}
		map.put("msg", msg);
		return "login";
	}
	
	/**
	 * 获取用户菜单
	 * @param pmenuId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path="/umenu")
	public String getCurrUserMenu(@RequestParam(name = "pmenuId", required = true) Integer pmenuId,
			HttpServletRequest request){
		SysUser sysUser = (SysUser)SecurityUtils.getSubject().getSession().getAttribute("sysUser");
		if(sysUser!=null && sysUser.getMenuList()!=null){
			List<SysMenu> cmenuList = new ArrayList<SysMenu>(); 
			for(SysMenu sysMenu : sysUser.getMenuList()){
				if(sysMenu.getParentId() == pmenuId){
					cmenuList.add(sysMenu);
				}
			}
			return JSONObject.toJSONString(cmenuList);
		}
		throw new RuntimeException("用户登录信息异常！");
	}
	
	
	
}
