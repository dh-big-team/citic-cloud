package com.dhcc.citic.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

/**
 * @Description:云产品mysql接口定义类
 * @filename:MysqlService.java
 * @author:Zeng Dongcheng
 * @version:1.0  
 * @Date:下午5:37:34 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                  版本 	   修改内容  
 * ----------------------------------------------  
 * 2018年10月12日     Zeng Dongcheng   1.0     XXXX
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public interface MysqlService {
	
	/**
	 * @Description:  查询mysql实例列表
	 * @param urlCode 地址前缀
	 * @param orgId   中信侧的租户id,对应腾讯侧qcloudUin
	 * @param params  json格式的请求参数
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult describeDBInstances(String orgId,JSONObject params) throws TencentCloudSDKException;
	
	/**
	 * @Description:  创建mysql实例(包年包月)
	 * @param urlCode 地址前缀
	 * @param orgId   中信侧的租户id,对应腾讯侧qcloudUin
	 * @param params  json格式的请求参数
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult createDBInstance(String orgId,JSONObject params) throws TencentCloudSDKException;
	
	
	/**
	 * @Description:  创建mysql实例(按量计费)
	 * @param urlCode 地址前缀
	 * @param orgId   中信侧的租户id,对应腾讯侧qcloudUin
	 * @param params  json格式的请求参数
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult createDBInstanceHour(String orgId,JSONObject params) throws TencentCloudSDKException;
	
}
