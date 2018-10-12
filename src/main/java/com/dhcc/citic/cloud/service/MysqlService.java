package com.dhcc.citic.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

public interface MysqlService {
	public BaseResult citicDescribeDBInstances(String urlcode,String orgId,JSONObject params) throws TencentCloudSDKException;
}
