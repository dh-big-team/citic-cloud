package com.dhcc.citic.cloud.api.product;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.model.TmpSecret;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.dhcc.citic.cloud.service.TmpSecretService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesResponse;

@RestController
@RequestMapping(value = "/api",produces = "application/json;charset=UTF-8")
public class DemoApi extends Base{
	
	@Autowired
	private TmpSecretService tmpSecretService;
	
	/**
	 * 测试腾讯API（基于sdk3.0自带模式）
	 * @param jsonParam
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@RequestMapping(value = "/DescribeZones")
	public BaseResult describeZones() throws TencentCloudSDKException{
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
        Credential cred = new Credential("AKIDiKk3EIdzpcJp8Gr4mKpBQTKzSirprDh0", "GPSJMH0kMtHT1TZZPI5jCXlttiL0Yw9b");

        // 实例化要请求产品(以cvm为例)的client对象
        CvmClient client = new CvmClient(cred, "ap-guangzhou");

        // 实例化一个请求对象
        DescribeZonesRequest req = new DescribeZonesRequest();

        // 通过client对象调用想要访问的接口，需要传入请求对象
        DescribeZonesResponse resp = client.DescribeZones(req);
        
        // 输出json格式的字符串回包
       return new BaseResult(resp);
	}
	
	/**
	 * 测试腾讯APi（基于sdk3.0封装的通用模式）
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@RequestMapping(value = "/DescribeRegions")
	public BaseResult describeRegions(String uin) throws TencentCloudSDKException{
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(uin);
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());
		ApiRequest req = new ApiRequest("DescribeRegions", cred);
		JSONObject rep = req.recvResponseRequest(new HashMap<String,String>());
		return new BaseResult(rep);
	}
}
