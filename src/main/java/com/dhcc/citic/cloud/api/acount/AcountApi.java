package com.dhcc.citic.cloud.api.acount;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.QcloudConfig;
import com.dhcc.citic.cloud.ctrl.BaseCtrl;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.HttpProfile;

@RestController
@RequestMapping(value = "/api/account")
public class AcountApi extends BaseCtrl{
	
	@Autowired
	QcloudConfig qcloudConfig;
	
	@RequestMapping(value = "/DescribeProject",produces = "application/json;charset=UTF-8")
	public BaseResult apiDescribeInstances(@RequestBody String recvParam) throws TencentCloudSDKException{
		Credential cred = new Credential(qcloudConfig.getSecretId(), qcloudConfig.getSecretKey());
		ApiRequest req = new ApiRequest("account.api.qcloud.com","2017-03-12","/v2/index.php", cred, "ap-guangzhou");
		req.getClientProfile().getHttpProfile().setReqMethod(HttpProfile.REQ_GET);
		HashMap<String,String> reqParam =super.jsonToMap(recvParam);
		JSON rep = req.recvCodeRequest(reqParam, "DescribeProject");
		return new BaseResult(rep);
	}
}
