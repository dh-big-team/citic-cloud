package com.dhcc.citic.cloud.service.impl;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.common.CiticQueryResult;
import com.dhcc.citic.cloud.config.QcloudConfig;
import com.dhcc.citic.cloud.config.ServiceIdMappingConfig;
import com.dhcc.citic.cloud.model.TmpSecret;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.dhcc.citic.cloud.service.CbsService;
import com.dhcc.citic.cloud.service.TmpSecretService;
import com.dhcc.citic.cloud.utils.ReqParamUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
@Service("cbsService")
public class CbsServiceImpl implements  CbsService{
	@Autowired
	QcloudConfig qcloudConfig;
	@Autowired
	ServiceIdMappingConfig serviceIdMappingConfig;
	@Autowired
	TmpSecretService tmpSecretService;
	@Override
	public BaseResult citicDescribeDisks(String urlCode,String orgId,JSONObject params)throws TencentCloudSDKException {
		//将实体转化成MAP，主要是将复杂结构的数据的key转化成key1.index.key2等，如DataDisks.0.DiskType=LOCAL_BASIC
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonObjectToMap(reqMap, params);
		
		//生成腾讯鉴权
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(orgId);
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());

		//组合接口域名
		String endPoint = urlCode + serviceIdMappingConfig.getUrlSuffixV3();
		//调用腾讯接口
		ApiRequest req = new ApiRequest(endPoint, cred);
		
		JSONObject rep = req.recvResponseRequest(reqMap, "DescribeDisks");
		
		//将数据包装成中信要求的格式
		CiticQueryResult result = new CiticQueryResult(reqMap,rep,"DiskSet");	
		return new BaseResult(result);
		
	}

}
