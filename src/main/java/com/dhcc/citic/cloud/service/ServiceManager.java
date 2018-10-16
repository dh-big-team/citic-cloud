package com.dhcc.citic.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
/**
 * 
 * 类名称		       业务分发接口类
 * 文件名称:     ServiceManager.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月11日下午6:04:57
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public interface ServiceManager
{
	/**
	 * 分发查询实例列表业务
	 * @param serviceId
	 * @param orgId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult doSelectList(String serviceId, String orgId, JSONObject params) throws TencentCloudSDKException;
	/**
	 * 分发创建实例业务
	 * @param serviceId
	 * @param orgId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult doCreate(String serviceId, String orgId, JSONObject params) throws TencentCloudSDKException;
	/**
	 * 分发修改实例业务
	 * @param serviceId
	 * @param orgId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult doUpdate(String serviceId, String orgId, String operationType, JSONObject params) throws TencentCloudSDKException;
	
}
