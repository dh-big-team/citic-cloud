package com.dhcc.citic.cloud.service;

import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

public interface MysqlService {
	public BaseResult citicDescribeDBInstances(String urlCode,String orgId,String params) throws TencentCloudSDKException;
}
