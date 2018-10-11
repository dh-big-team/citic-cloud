package com.dhcc.citic.cloud.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.QcloudConfig;
import com.dhcc.citic.cloud.config.TencentCloudUrlConfig;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.dhcc.citic.cloud.service.CvmService;
import com.dhcc.citic.cloud.utils.ReqParamUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.cvm.v20170312.models.RunInstancesRequest;
/**
 * 
 * 类名称		       云服务器业务类
 * 文件名称:     CvmServiceImpl.java
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
@Service("cvmService")
public class CvmServiceImpl implements CvmService
{
	@Autowired
	QcloudConfig qcloudConfig;
	@Override
	public BaseResult citicRunInstances(String serviceId,String userId,String params) throws TencentCloudSDKException
	{
		//将实体转化成MAP，主要是将复杂结构的数据的key转化成key1.index.key2等，如DataDisks.0.DiskType=LOCAL_BASIC
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonStrToMap(reqMap, params);
		
		//生成腾讯鉴权
		Credential cred = new Credential(qcloudConfig.getSecretId(), qcloudConfig.getSecretKey());
		
		//根据商品ID，取得腾讯API对应接口，如需V2接口，则加参数flag=2,
		String endPoint = TencentCloudUrlConfig.getEndPoint(serviceId, 3);
		ApiRequest req = new ApiRequest(endPoint,"2017-03-12", cred, "ap-guangzhou");
		
		//调用腾讯接口
		JSONObject rep = req.recvResponseRequest(reqMap, "RunInstances");
		return new BaseResult(rep);
	}
	
}
