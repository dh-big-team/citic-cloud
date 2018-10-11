package com.dhcc.citic.cloud.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dhcc.citic.cloud.model.SysUser;

/**
 * 自定义监听器，监听Session
 * 文件名称:     MyHttpSessionListener.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月23日下午2:54:53 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月23日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener{

	private static final Logger Log=LoggerFactory.getLogger(MyHttpSessionListener.class);
			
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		SysUser user = (SysUser)se.getSession().getAttribute("sysUser");
		Log.debug("--------sessionCreated----------");
		if(user!=null){
			Log.debug("--------userName={}--------",user.getUserName());
		}
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		SysUser user = (SysUser)se.getSession().getAttribute("sysUser");
		Log.debug("--------sessionDestroyed----------");
		if(user!=null){
			Log.debug("--------userName={}--------",user.getUserName());
		}
		
	}

}
