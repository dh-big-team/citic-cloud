package com.dhcc.citic.cloud.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.dhcc.citic.cloud.model.SysMenu;
import com.dhcc.citic.cloud.model.SysUser;
import com.dhcc.citic.cloud.service.MenuService;
import com.dhcc.citic.cloud.service.UserService;


/**
 * 身份校验核心类
 * 文件名称:     MyShiroRealm.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年6月27日下午1:53:46 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年6月27日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
public class MyShiroRealm extends AuthorizingRealm{

	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	
	
	/**
	* 此方法调用 hasRole,hasPermission的时候才会进行回调.
	*
	* 权限信息.(授权):
	* 1、如果用户正常退出，缓存自动清空；
	* 2、如果用户非正常退出，缓存自动清空；
	* 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
	* （需要手动编程进行实现；放在service进行调用）
	* 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
	* 调用clearCached方法；
	* Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
	* @param principals
	* @return
	*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*
		* 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
		* 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
		* 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
		* 缓存过期之后会再次执行。
		*/
		System.out.println("doGetAuthorizationInfo------------------------");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUser sysUser = (SysUser)principals.getPrimaryPrincipal();
		if(sysUser==null){
			return null;
		}
		//实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//		Set<SysMenu> menuList = menuService.findMenuByUserId(sysUser.getUserId());
		for(SysMenu p:sysUser.getMenuList()){
			authorizationInfo.addStringPermission(p.getMenuUrl());
		}
		return authorizationInfo;
	}

	/**
	* 认证信息.(身份验证)
	* Authentication 是用来验证用户身份
	* @param token
	* @return
	* @throws AuthenticationException
	*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("doGetAuthenticationInfo------------------------");
		//获取用户的输入的账号
		String loginName = (String)token.getPrincipal();
		//实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		SysUser sysUser = userService.findUserByLoginName(loginName);
		if(sysUser == null){
			return null;
		}
		//暂时先将用户的菜单集合放到这，后续改到doGetAuthorizationInfo方法中做缓存
		sysUser.setMenuList(menuService.findMenuByUserId(sysUser.getUserId()));
		
		Session session =  SecurityUtils.getSubject().getSession();
		session.setAttribute("sysUser", sysUser);
		//若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser,sysUser.getLoginPwd(),
				ByteSource.Util.bytes(sysUser.getPwdSalt()),sysUser.getLoginName());
	    return authenticationInfo;
	}

}
