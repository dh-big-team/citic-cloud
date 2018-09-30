package com.dhcc.citic.cloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.dhcc.citic.cloud.config.QcloudConfig;

/**
 * 文件名称:     BootDemoApplication.java
 * 内容摘要:     在SpringBootApplication 上使用@ServletComponentScan 注解后，
 * Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月30日上午9:22:37 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月30日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties({QcloudConfig.class})
//指定扫描的包路径，默认扫描Application当前目录及子目录
//@ComponentScan(basePackages={"com.my.bootdemo","com.example.bootdemo"})
public class BootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
		//修改启动时的Banner
		/*
		* Banner.Mode.OFF:关闭;
		* Banner.Mode.CONSOLE:控制台输出，默认方式;
		* Banner.Mode.LOG:日志输出方式;
		*/
//		SpringApplication application = new SpringApplication(BootDemoApplication.class);
//		application.setBannerMode(Mode.OFF);
//		application.run(args);
	}
}
