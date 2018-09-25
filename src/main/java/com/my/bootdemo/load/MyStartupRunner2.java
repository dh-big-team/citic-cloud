package com.my.bootdemo.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 自定义启动加载  @Order 注解标记启动顺序
 * 文件名称:     MyStartupRunner2.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月29日下午2:51:15 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月29日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Component
@Order(value=-1)
public class MyStartupRunner2 implements CommandLineRunner {

	private static final Logger Log=LoggerFactory.getLogger(MyStartupRunner2.class);
	
	@Override
	public void run(String... args) throws Exception {
		Log.info("-------------------MyStartupRunner2 is running");

	}

}
