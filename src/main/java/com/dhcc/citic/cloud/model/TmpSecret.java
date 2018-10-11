package com.dhcc.citic.cloud.model;

/**
 * 用户在腾讯侧临时secret信息
 * 文件名称:     TmpSecret.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年10月11日下午4:22:54 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class TmpSecret {
	private String tmpSecretId;
	private String tmpSecretKey;
	private String sessionToken;
	private String expiredTime;
	
	public String getTmpSecretId() {
		return tmpSecretId;
	}
	public void setTmpSecretId(String tmpSecretId) {
		this.tmpSecretId = tmpSecretId;
	}
	public String getTmpSecretKey() {
		return tmpSecretKey;
	}
	public void setTmpSecretKey(String tmpSecretKey) {
		this.tmpSecretKey = tmpSecretKey;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public String getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}
	
}
