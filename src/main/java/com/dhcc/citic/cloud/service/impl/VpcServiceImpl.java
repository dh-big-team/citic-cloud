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
import com.dhcc.citic.cloud.service.TmpSecretService;
import com.dhcc.citic.cloud.service.VpcService;
import com.dhcc.citic.cloud.utils.ReqParamUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
/**
 * 
 * 类名称		       私有网络业务实现类
 * 文件名称:     VpcServiceImpl.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月11日上午11:24:43
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@Service("vpcService")
public class VpcServiceImpl implements VpcService
{
	@Autowired
	QcloudConfig qcloudConfig;
	@Autowired
	ServiceIdMappingConfig serviceIdMappingConfig;
	@Autowired
	TmpSecretService tmpSecretService;
	
	@Override
	public BaseResult describeVpcs(String orgId, JSONObject params) throws TencentCloudSDKException
	{
		//将公用的参数名称instanceIds，改为VPC中对应的参数名称vpcIds
		String vpcIds = params.getString("instanceIds");
		params.remove("instanceIds");
		params.put("vpcIds", vpcIds);
		
		//将结构数据转化为一维字符串MAP
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonObjectToMap(reqMap, params);
		
		//生成腾讯鉴权
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(orgId);
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());
		
		//构造请求client
		ApiRequest req = new ApiRequest("DescribeVpcs",cred);
		
		//调用腾讯接口
		JSONObject rep = req.recvResponseRequest(reqMap);
		
		//将数据包装成中信要求的格式
		CiticQueryResult result = new CiticQueryResult(reqMap,rep,"VpcSet");
				
		return new BaseResult(result);
	}

	@Override
	public BaseResult createVpc(String orgId,JSONObject params) throws TencentCloudSDKException
	{
		//将结构数据转化为一维字符串MAP
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonObjectToMap(reqMap, params);
		
		//生成腾讯鉴权
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(orgId);
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());
		
		//构造请求client
		ApiRequest req = new ApiRequest("CreateVpc",cred);
		
		//调用腾讯接口
		JSONObject rep = req.recvResponseRequest(reqMap);
				
		return new BaseResult(rep);
	}
}
