package com.dhcc.citic.cloud.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;

@Configuration
public class ShiroConfiguration {
	
	
	/**
	* ShiroFilterFactoryBean 处理拦截资源文件问题。
	* 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	* 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	*
	Filter Chain定义说明
	1、一个URL可以配置多个Filter，使用逗号分隔
	2、当设置多个过滤器时，全部验证通过，才视为通过
	3、部分过滤器可指定参数，如perms，roles
	*
	*/
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		//配置静态资源放行
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/json/**", "anon");
        //配置验证码放行
        filterChainDefinitionMap.put("/captcha/**", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
		//配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/index", "user");
		filterChainDefinitionMap.put("/", "user");
		//过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
		//authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		filterChainDefinitionMap.put("/**", "authc");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
		filters.put("authc", new MyFormAuthenticationFilter());//将自定义 的FormAuthenticationFilter注入shiroFilter中
		return shiroFilterFactoryBean;
	}
	
	/**
	* 身份认证realm
	* @return 
	*/ 
	@Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
	
	/**
	* 凭证匹配器 
	* （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 
	* 所以我们需要修改下doGetAuthenticationInfo中的代码;）
	* @return 
	*/ 
	@Bean 
	public HashedCredentialsMatcher hashedCredentialsMatcher(){ 
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		//散列算法:这里使用MD5算法; 
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}



    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //securityManager.setSessionManager(sessionManager());
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
    
    
    @Bean
    public SessionManager sessionManager(){
    	DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    	return sessionManager;
    }
    
    /**
    * 开启shiro aop注解支持. 
    * 使用代理方式;所以需要开启代码支持
    * @param securityManager 
    * @return 
    */ 
    @Bean 
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){ 
    	AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor(); 
    	authorizationAttributeSourceAdvisor.setSecurityManager(securityManager); 
    	return authorizationAttributeSourceAdvisor;
    }
    
    /**
     * 必须添加此方法
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    
    /**
     * cookie对象
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
	    System.out.println("ShiroConfiguration.rememberMeCookie()");
	    //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	    //<!-- 记住我cookie生效时间30天 ,单位秒;-->
	    simpleCookie.setMaxAge(259200);
	    return simpleCookie;
    }
    
    /**
    * cookie管理对象;
    * @return
    */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
	    System.out.println("ShiroConfiguration.rememberMeManager()");
	    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	    cookieRememberMeManager.setCookie(rememberMeCookie());
	    return cookieRememberMeManager;
    }

}
