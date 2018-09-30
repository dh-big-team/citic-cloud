package com.dhcc.citic.cloud.api.acount;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.QcloudConfig;
import com.dhcc.citic.cloud.config.EnumConfig.ErrCode;
import com.dhcc.citic.cloud.config.EnumConfig.RetCode;
import com.dhcc.citic.cloud.ctrl.BaseCtrl;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.Sign;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

@RestController
@RequestMapping(value = "/asingle")
public class AsingleLogin extends BaseCtrl{

	@Autowired
	QcloudConfig qcloudConfig;
	
	@RequestMapping(value = "/login")
	public BaseResult asingleLogin(HttpServletRequest req,HttpServletResponse rep) throws TencentCloudSDKException, IOException{
		String uin = req.getParameter("uin");
		if(StringUtils.isBlank(uin)){
			return BaseResult.getDesignErr(RetCode.VALID_ERR,ErrCode.COM_VALID_ERR,"请传入uin");
		}
		Credential cred = new Credential(qcloudConfig.getSecretId(), qcloudConfig.getSecretKey());
		ApiRequest apiReq = new ApiRequest("open.api.qcloud.com","2017-03-12","/v2/index.php", cred, "ap-guangzhou");
		HashMap<String,String> reqParam = new HashMap<String,String>();
		reqParam.put("qcloudUin", uin);
		JSONObject apiRep = (JSONObject)apiReq.recvCodeRequest(reqParam, "ChannelGetLoginToken");
		String token  = apiRep.getString("token");
		String signature = Sign.sign(apiRep.getString("key"), apiRep.getString("token") + qcloudConfig.getPeerUin(), "HmacSHA1");
		rep.sendRedirect("https://cloud.tencent.com/login/channelAccessCallback?fromUin="
                + qcloudConfig.getPeerUin() //传递主UIN
                + "&token=" + token
                + "&signature=" + signature + "&redirect_uri=http://console.cloud.tencent.com/");
		return new BaseResult(apiRep);
	}

}
