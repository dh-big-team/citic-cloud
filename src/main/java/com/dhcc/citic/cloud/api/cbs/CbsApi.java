package com.dhcc.citic.cloud.api.cbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.QcloudConfig;
import com.dhcc.citic.cloud.ctrl.BaseCtrl;
import com.dhcc.citic.cloud.service.CbsService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
@RestController
@RequestMapping(value = "/api/cbs")
public class CbsApi extends BaseCtrl {
	@Autowired
	QcloudConfig qcloudConfig;
	@Autowired
	CbsService  cbsService;
	/**
	 * 查询云硬盘实例列表
	 * @param recvParam
	 * @return
	 * @throws TencentCloudSDKException
	 */
	
	@RequestMapping(value = "/DescribeDisks",produces = "application/json;charset=UTF-8")
	public BaseResult apiDescribeDisks(@RequestBody String jsonParam) throws TencentCloudSDKException{
		JSONObject jsonObj = JSONObject.parseObject(jsonParam);
		JSONObject params = jsonObj.getJSONObject("params");
		JSONObject citicInfo = jsonObj.getJSONObject("citicInfo");
		JSONObject otherInfo = jsonObj.getJSONObject("otherInfo");
		String serviceId = (String) jsonObj.get("serviceId");
		String instanceId = (String) jsonObj.get("instanceId");		
		String requestId = (String) jsonObj.get("requestId");
		//取得userId
		String userId = citicInfo.getString("userId");
		return this.cbsService.citicDescribeDisks(serviceId,userId,params);
	}
}
