package com.dhcc.citic.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载自定义配置信息
 * 文件名称:     TestConfig.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月30日上午11:11:45 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月30日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Configuration  //Configuration注解必须加上，否则加载不了
@PropertySource("classpath:qcloud.properties")
@ConfigurationProperties(prefix = "auth")
public class QcloudConfig {
	
	private String secretId;
	private String secretKey;
	private String peerUin;
	
	public String getSecretId() {
		return secretId;
	}
	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getPeerUin() {
		return peerUin;
	}
	public void setPeerUin(String peerUin) {
		this.peerUin = peerUin;
	}
	
}
