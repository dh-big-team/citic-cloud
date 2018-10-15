package com.dhcc.citic.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
/**
 * 
 * 类名称                      弹性公网IP业务接口
 * 文件名称:     IpService.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月15日下午1:35:55
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月15日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public interface IpService
{
	/**
	 * 查询弹性公网IP实例列表
	 * @param orgId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	public BaseResult describeAddresses(String orgId,JSONObject params) throws TencentCloudSDKException;
	/**
	 * 创建弹性公网IP实例
	 * @param orgId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult allocateAddresses(String orgId,JSONObject params) throws TencentCloudSDKException;
	

}
