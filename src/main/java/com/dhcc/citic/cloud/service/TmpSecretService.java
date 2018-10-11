package com.dhcc.citic.cloud.service;

import com.dhcc.citic.cloud.model.TmpSecret;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

public interface TmpSecretService {
	
	public TmpSecret getTmpSecret(String qcloudUin) throws TencentCloudSDKException;
	
}
