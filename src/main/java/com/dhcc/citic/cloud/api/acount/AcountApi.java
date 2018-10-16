package com.dhcc.citic.cloud.api.acount;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.dhcc.citic.cloud.api.product.Base;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.QcloudConfig;
import com.dhcc.citic.cloud.model.TmpSecret;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.dhcc.citic.cloud.service.TmpSecretService;
import com.dhcc.citic.cloud.utils.ReqParamUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 账号相关api
 * 文件名称:     AcountApi.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年10月11日下午4:15:10 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@RestController
@RequestMapping(value = "/api/account",produces = "application/json;charset=UTF-8")
public class AcountApi extends Base{
	
	@Autowired
	private QcloudConfig qcloudConfig;
	
	@Autowired
	private TmpSecretService tmpSecretService;
	
	
	/**
	 * 创建渠道用户
	 * @param recvParam
	 * @return
	 * @throws TencentCloudSDKException
	 */
	@RequestMapping(value = "/user")
	public BaseResult addPrivateChildUser(@RequestBody String recvParam) throws TencentCloudSDKException{
		Credential cred = new Credential(qcloudConfig.getSecretId(), qcloudConfig.getSecretKey());
		ApiRequest req = new ApiRequest("ChannelRegisterUser", cred);
		req.getClientProfile().getHttpProfile().setReqMethod(HttpProfile.REQ_GET);
		HashMap<String,String> reqParam =new HashMap<String,String>();
		ReqParamUtil.jsonStrToMap(reqParam, recvParam);
		JSON rep = req.recvCodeRequest(reqParam);
		return new BaseResult(rep);
	}
	
	/**
	 * 获取用户临时秘钥
	 * @param recvParam
	 * @return
	 * @throws TencentCloudSDKException
	 */
	@RequestMapping(value = "/tmpsecret")
	public BaseResult getTmpSecret(String uin) throws TencentCloudSDKException{
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(uin);
		return new BaseResult(tmpSecret);
	}
}
