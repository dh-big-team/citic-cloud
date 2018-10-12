package com.dhcc.citic.cloud.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.service.ServiceManager;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

@RestController
@RequestMapping(value = "/qcloud",produces = "application/json;charset=UTF-8")
public class CloudCtrl extends BaseCtrl{
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CloudCtrl.class);
	@Autowired
	private ServiceManager serviceManager;

	/**
	 * 查询实例列表接口
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@RequestMapping(value = "/select_instances",method = RequestMethod.GET)
	public BaseResult QueryInstances(@RequestBody JSONObject jsonParam) throws TencentCloudSDKException{
		JSONArray instanceIds = JSONArray.parseArray((String)jsonParam.get("instanceIds"));
		JSONArray filters = JSONArray.parseArray((String)jsonParam.get("filters"));
		String pageIndex = (String)jsonParam.get("pageIndex");
		String limit = (String) jsonParam.get("pageSize");
		String orgId = (String) jsonParam.get("orgId");		
		String serviceId = (String) jsonParam.get("serviceId");
		//中信和腾讯的起始页相差1
		Integer offset = Integer.parseInt(pageIndex)-1;
		JSONObject paramObj = new JSONObject();
		paramObj.put("instanceIds", instanceIds);
		paramObj.put("filters", filters);
		paramObj.put("offset", offset.toString());
		paramObj.put("limit", limit);
		String params = paramObj.toJSONString();
		return serviceManager.doSelectList(serviceId, orgId, params);
	}
	/**
	 * 创建实例接口
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/create_instances",method = RequestMethod.POST)
	public BaseResult createInstances(@RequestBody JSONObject jsonParam) throws TencentCloudSDKException{
		JSONObject citicInfo = jsonParam.getJSONObject("citicInfo");
		JSONObject params = jsonParam.getJSONObject("params");
		JSONObject otherInfo = jsonParam.getJSONObject("otherInfo");
		String serviceId = (String) jsonParam.get("serviceId");
		String instanceId = (String) jsonParam.get("instanceId");		
		String requestId = (String) jsonParam.get("requestId");
		//取得userId
		String orgId = citicInfo.getString("orgId");
		return serviceManager.doCreate(serviceId, orgId, params.toJSONString());
	}

}
