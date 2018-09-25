package com.dhcc.citic.cloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 * 获取环境变量信息
 * 文件名称:     MyEnvironmentAware.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月29日下午2:53:53 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月29日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Configuration
public class MyEnvironmentAware implements EnvironmentAware{

	private static final Logger Log=LoggerFactory.getLogger(MyEnvironmentAware.class);
		
	@Value("${spring.datasource.url}")
	private String myUrl;
	
	
	@Override
	public void setEnvironment(Environment environment) {
		//通过 environment 获取到系统属性
		Log.info("-------JAVA_HOME={}",environment.getProperty("JAVA_HOME"));
		//通过注入获取配置信息
		Log.info("-------myUrl={}",myUrl);
		//通过 environment 同样能获取到application.properties配置的属性
		Log.info("-------myUrl={}",environment.getProperty("spring.datasource.url"));
		
	}

}
