package com.dhcc.citic.cloud.service.impl;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.QcloudConfig;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.dhcc.citic.cloud.service.CbsService;
import com.dhcc.citic.cloud.utils.ReqParamUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
@Service("cbsService")
public class CbsServiceImpl implements  CbsService{
	@Autowired
	QcloudConfig qcloudConfig;
	@Override
	public BaseResult citicDescribeDisks(String serviceId, String userId, JSONObject jsonParam)throws TencentCloudSDKException {
		//将实体转化成MAP，主要是将复杂结构的数据的key转化成key1.index.key2等，如DataDisks.0.DiskType=LOCAL_BASIC
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonObjectToMap(reqMap, jsonParam);
		//生成腾讯鉴权
		Credential cred = new Credential(qcloudConfig.getSecretId(), qcloudConfig.getSecretKey());
		//根据商品ID，取得腾讯API对应接口，如需V2接口，则加参数flag=2,
		String endPoint =   "cbs.tencentcloudapi.com";  // TencentCloudUrlConfig.getEndPoint(serviceId, 3);
		// 
		ApiRequest req = new ApiRequest(endPoint,cred,reqMap.get("Region"));
		//调用腾讯接口
		JSONObject rep = req.recvResponseRequest(reqMap, "DescribeDisks");
		return new BaseResult(rep);
		
	}

}
