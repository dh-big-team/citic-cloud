package com.dhcc.citic.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
/**
 * 
 * 类名称                      云服务器业务类
 * 文件名称:     CvmService.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月11日上午11:25:30
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public interface CvmService
{
	/**
	 * 查询云服务器实例列表
	 * @param serviceId
	 * @param userId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	public BaseResult describeInstances(String urlCode,String orgId,JSONObject params) throws TencentCloudSDKException;
	/**
	 * 创建云服务器实例
	 * @param serviceId
	 * @param userId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult runInstances(String urlCode,String orgId,JSONObject params) throws TencentCloudSDKException;
	

}
