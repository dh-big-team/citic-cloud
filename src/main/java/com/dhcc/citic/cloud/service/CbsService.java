package com.dhcc.citic.cloud.service;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

public interface CbsService {
	/**
	 * 查询云硬盘实例列表
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult citicDescribeDisks(String serviceId,String userId,JSONObject jsonParam) throws TencentCloudSDKException;
}
