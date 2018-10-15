package com.dhcc.citic.cloud.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.common.CiticQueryResult;
import com.dhcc.citic.cloud.config.ServiceIdMappingConfig;
import com.dhcc.citic.cloud.model.TmpSecret;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.dhcc.citic.cloud.service.MysqlService;
import com.dhcc.citic.cloud.service.TmpSecretService;
import com.dhcc.citic.cloud.utils.ReqParamUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

/**
 * @Description:云产品-Mysql数据库相关api业务实现类
 * @filename:MysqlServiceImpl.java
 * @author:Zeng Dongcheng
 * @version:1.0  
 * @Date:下午2:39:20 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                  版本 	   修改内容  
 * ----------------------------------------------  
 * 2018年10月12日     Zeng Dongcheng   1.0     XXXX
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@Service("mysqlService")
public class MysqlServiceImpl implements MysqlService{
	
	@Autowired
	private TmpSecretService tmpSecretService;
	@Autowired
	ServiceIdMappingConfig serviceIdMappingConfig;
	
	/**
	 * @Description:查询mysql实例列表
	 * @param urlCode 地址前缀
	 * @param orgId   中信侧的租户id,对应腾讯侧qcloudUin
	 * @param params  json格式的请求参数
	 * @return
	 * @throws TencentCloudSDKException
	 */
	@Override
	public BaseResult describeDBInstances(String orgId, JSONObject params)
			throws TencentCloudSDKException {
		//json格式的请求参数转为一符合腾讯api格式的一维map
		HashMap<String, String> reqMap = new HashMap<String, String>();
		ReqParamUtil.jsonObjectToMap(reqMap, params);
		//获取用户临时秘钥信息
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(orgId);
		//构造认证类(tmpSecretId,tmpSecretKey,sessionToken)
		//如果传入的是用户临时信息，则此处需要传入sessionToken，否则传入secretId、secretKey即可
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());
		//构造请求client
		ApiRequest req = new ApiRequest("DescribeDBInstances",cred);
		//调用腾讯接口
		JSONObject rep = req.recvResponseRequest(reqMap);
		CiticQueryResult result = new CiticQueryResult(reqMap,rep,"Items");
		//将数据包装成中信要求的格式
		return new BaseResult(result);
	}

	/**
	 * @Description:  创建mysql实例(包年包月)
	 * @param urlCode 地址前缀
	 * @param orgId   中信侧的租户id,对应腾讯侧qcloudUin
	 * @param params  json格式的请求参数
	 * @return
	 * @throws TencentCloudSDKException
	 */
	@Override
	public BaseResult createDBInstance(String orgId, JSONObject params)
			throws TencentCloudSDKException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult createDBInstanceHour(String orgId, JSONObject params)
			throws TencentCloudSDKException {
		// TODO Auto-generated method stub
		return null;
	}

}
