package com.dhcc.citic.cloud.common;

/**
 * @Description:实例创建返回结果类(按中信侧结构封装)
 * @filename:CiticCreateResult.java
 * @author:Zeng Dongcheng
 * @version:1.0  
 * @Date:下午5:55:10 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                  版本 	   修改内容  
 * ----------------------------------------------  
 * 2018年10月12日     Zeng Dongcheng   1.0     XXXX
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class CiticCreateResult {
	//实例创建时间
	private String creationTime;
	//实例过期时间
	private String expiredTime;
	//中信传入的实例id
	private String instanceId;
	//供应商返回的实例id
	private String supplierInstanceId;
	
	/**
	 * @Description 构造实例创建返回结果
	 * @param creationTime
	 * @param expireTime
	 * @param instanceId
	 * @param supplierInstanceId
	 */
	public CiticCreateResult(String creationTime,String expiredTime,
			String instanceId,String supplierInstanceId){
		setCreationTime(creationTime);
		setExpiredTime(expiredTime);
		setInstanceId(instanceId);
		setSupplierInstanceId(supplierInstanceId);
	}
	
	/**
	 * @Description 构造实例创建返回结果
	 * @param instanceId
	 * @param supplierInstanceId
	 */
	public CiticCreateResult(String instanceId,String supplierInstanceId){
		//未完成
		setInstanceId(instanceId);
		setSupplierInstanceId(supplierInstanceId);
	}
	
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getSupplierInstanceId() {
		return supplierInstanceId;
	}
	public void setSupplierInstanceId(String supplierInstanceId) {
		this.supplierInstanceId = supplierInstanceId;
	}
	
	
}
