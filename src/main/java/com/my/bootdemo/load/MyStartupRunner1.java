package com.my.bootdemo.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 自定义启动加载
 * 文件名称:     MyStartupRunner1.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月23日下午4:34:20 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月23日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Component
@Order(value=2)
public class MyStartupRunner1 implements CommandLineRunner{

	private static final Logger Log=LoggerFactory.getLogger(MyStartupRunner1.class);
			
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Log.info("-------------------MyStartupRunner1 is running");
	}

}
