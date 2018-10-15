package com.dhcc.citic.cloud.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * 类名称		        加载自定义配置信息
 * 文件名称:     ServiceIdMappingConfig.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月11日下午4:44:05
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@Configuration  //Configuration注解必须加上，否则加载不了
@PropertySource("classpath:qcloud.properties")
@ConfigurationProperties(prefix = "mapping")
public class ServiceIdMappingConfig {
	
	private String serviceToProductId;	
	private Map<String, String> productIds = new HashMap<>();
	
	/**
	 * 根据中信商品ID取得对应的腾讯产品ID
	 * @param serviceId
	 * @return
	 */
	public String getProductId(String serviceId)
	{
		return productIds.get(serviceId);
	}
		
	public String getServiceToProductId()
	{
		return serviceToProductId;
	}
	public void setServiceToProductId(String serviceToProductId)
	{
		this.serviceToProductId = serviceToProductId;
		if (serviceToProductId != null)
		{
			String[] strings = serviceToProductId.split(",");
			for (String string : strings)
			{
				String[] strs = string.split(":");
				this.productIds.put(strs[0], strs[1]);
			}
		}
	}
}
