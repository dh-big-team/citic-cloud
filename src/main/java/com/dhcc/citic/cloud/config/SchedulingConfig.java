package com.dhcc.citic.cloud.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 * 文件名称:     SchedulingConfig.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月21日下午5:14:22 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月21日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
//@Configuration
//@EnableScheduling
public class SchedulingConfig {
	
	@Async("myAsync")
	@Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
	public void schedulerA() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+"任务A>>>>>>>>>begin");
		try {  
            TimeUnit.SECONDS.sleep(60);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }
		System.out.println(sdf.format(new Date())+"任务A>>>>>>>>>end");
	}
	
	@Async("mySimpleAsync")
	@Scheduled(cron = "0/10 * * * * ?") // 每10秒执行一次
	public void schedulerB() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+">>>>>>>>> 任务B");
	}
}
