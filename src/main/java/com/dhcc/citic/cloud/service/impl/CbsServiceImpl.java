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
/**
 * 
 * 类名称		       云硬盘业务接口实现类
 * 文件名称:     CbsService.java
 * 内容摘要: 
 * @author:   duan zhang quan
 * @version:  1.0  
 * @Date:     2018年10月15日
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@Service("cbsService")
public class CbsServiceImpl implements  CbsService{
	@Autowired
	QcloudConfig qcloudConfig;
	@Autowired
	ServiceIdMappingConfig serviceIdMappingConfig;
	@Autowired
	TmpSecretService tmpSecretService;
	@Override
	public BaseResult describeDisks(String orgId,JSONObject params)throws TencentCloudSDKException {
		//将实体转化成MAP，主要是将复杂结构的数据的key转化成key1.index.key2等，如DataDisks.0.DiskType=LOCAL_BASIC
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonObjectToMap(reqMap, params);
		//生成腾讯鉴权
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(orgId);
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());
		//构造请求client
		ApiRequest req = new ApiRequest("DescribeDisks", cred);
		JSONObject rep = req.recvResponseRequest(reqMap);
		CiticQueryResult result = new CiticQueryResult(reqMap,rep,"DiskSet");
		//将数据包装成中信要求的格式
		return new BaseResult(result);
		
	}

}
