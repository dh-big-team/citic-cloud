package com.dhcc.citic.cloud.service.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;	
import com.dhcc.citic.cloud.config.QcloudConfig;
import com.dhcc.citic.cloud.model.TmpSecret;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.dhcc.citic.cloud.service.TmpSecretService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * @Description:用户在腾讯侧临时秘钥处理类
 * @filename:TmpSecretServiceImpl.java
 * @author:Zeng Dongcheng
 * @version:1.0  
 * @Date:下午5:37:25 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                  版本 	   修改内容  
 * ----------------------------------------------  
 * 2018年10月11日     Zeng Dongcheng   1.0     XXXX
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@Service("tmpSecretService")
public class TmpSecretServiceImpl implements TmpSecretService{
	
	private static Logger logger = LoggerFactory.getLogger(TmpSecretServiceImpl.class);
	
	@Autowired
	QcloudConfig qcloudConfig;
	
	//为了开发方便，暂时用hashmap缓存tmpSecret，后续再改为redis
	public static final HashMap<String,TmpSecret> tmpSecretMaps = new HashMap<String,TmpSecret>();

	
	/**
	 * @Description:获取用户在腾讯侧临时秘钥
	 * @param qcloudUin 腾讯侧用户uin
	 * @return 用户临时秘钥
	 * @throws TencentCloudSDKException
	 */
	@Override
	public TmpSecret getTmpSecret(String qcloudUin) throws TencentCloudSDKException {
		if(tmpSecretMaps.get(qcloudUin)!=null){
			logger.debug("从缓存中取临时秘钥");
			return tmpSecretMaps.get(qcloudUin);
		}
		Credential cred = new Credential(qcloudConfig.getSecretId(), qcloudConfig.getSecretKey());
		ApiRequest req = new ApiRequest("sts.api.qcloud.com","/v2/index.php", cred);
		req.getClientProfile().getHttpProfile().setReqMethod(HttpProfile.REQ_GET);
		HashMap<String,String> reqParam =new HashMap<String,String>();
		//角色标识
		reqParam.put("roleArn", "qcs::cam::uin/" + qcloudUin + ":roleName/channel_QcsSpRole");
		//会话名称（填固定值channelCUCC）
		reqParam.put("roleSessionName", "channelCUCC");
		//过期时间（300s-7200s之间，不填默认为7200s)
		reqParam.put("durationSeconds", "7200");
		JSONObject rep = (JSONObject)req.recvCodeRequest(reqParam, "AssumeRole");
		if(rep!=null && rep.get("credentials")!=null){
			JSONObject creObj = rep.getJSONObject("credentials");
			TmpSecret tmpSecret = new TmpSecret();
			tmpSecret.setTmpSecretId(creObj.getString("tmpSecretId"));
			tmpSecret.setTmpSecretKey(creObj.getString("tmpSecretKey"));
			tmpSecret.setSessionToken(creObj.getString("sessionToken"));
			tmpSecret.setExpiredTime(rep.getString("expiredTime"));
			logger.debug("临时秘钥tmpSecretId="+tmpSecret.getTmpSecretId()+",tmpSecretKey="+tmpSecret.getTmpSecretKey());
			tmpSecretMaps.put(qcloudUin, tmpSecret);
			return tmpSecret;
		}else{
			throw new TencentCloudSDKException("临时秘钥获取失败!");
		}
	}

}
