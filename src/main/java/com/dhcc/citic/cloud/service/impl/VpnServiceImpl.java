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
import com.dhcc.citic.cloud.service.VpnService;
import com.dhcc.citic.cloud.utils.ReqParamUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
/**
 * 
 * 类名称		  VPN业务实现类
 * 文件名称:     VpnServiceImpl.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月15日上午11:21:09
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月15日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@Service("vpnService")
public class VpnServiceImpl implements VpnService
{
	@Autowired
	QcloudConfig qcloudConfig;
	@Autowired
	ServiceIdMappingConfig serviceIdMappingConfig;
	@Autowired
	TmpSecretService tmpSecretService;
	
	@Override
	public BaseResult describeVpnGateways(String orgId, JSONObject params) throws TencentCloudSDKException
	{
		//将公用的参数名称instanceIds，改为VPN中对应的参数名称vpnGatewayIds
		String vpnGatewayIds = params.getString("instanceIds");
		params.remove("instanceIds");
		params.put("vpnGatewayIds", vpnGatewayIds);
		
		//将结构数据转化为一维字符串MAP
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonObjectToMap(reqMap, params);
		
		//生成腾讯鉴权
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(orgId);
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());
		
		//构造请求client
		ApiRequest req = new ApiRequest("DescribeVpnGateways",cred);
		
		//调用腾讯接口
		JSONObject rep = req.recvResponseRequest(reqMap);
		
		//将数据包装成中信要求的格式
		CiticQueryResult result = new CiticQueryResult(reqMap,rep,"VpnGatewaySet");
				
		return new BaseResult(result);
	}

	@Override
	public BaseResult createVpnGateway(String orgId,JSONObject params) throws TencentCloudSDKException
	{
		//将结构数据转化为一维字符串MAP
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonObjectToMap(reqMap, params);
		
		//生成腾讯鉴权
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(orgId);
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());
		
		//构造请求client
		ApiRequest req = new ApiRequest("CreateVpnGateway",cred);
		
		//调用腾讯接口
		JSONObject rep = req.recvResponseRequest(reqMap);
		
		
		return new BaseResult(rep);
	}
}
