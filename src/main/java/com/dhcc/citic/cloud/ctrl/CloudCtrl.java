package com.dhcc.citic.cloud.ctrl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.service.ServiceManager;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

/**
 * 
 * 类名称		       中信云适配器控制器
 * 文件名称:     CloudCtrl.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月12日下午5:15:55
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月12日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@RestController
@RequestMapping(value = "/qcloud",produces = "application/json;charset=UTF-8")
public class CloudCtrl extends BaseCtrl{
	
	private static final Logger LOG = LoggerFactory.getLogger(CloudCtrl.class);
	@Autowired
	private ServiceManager serviceManager;

	/**
	 * 查询实例列表接口
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@RequestMapping(value = "/service_instances",method = RequestMethod.GET)
	public BaseResult QueryInstances() throws TencentCloudSDKException{
		//取得请求参数
		Map<String, String[]> reqMap = new HashMap<>(super.getParameterMap());
		String[] instanceIds = reqMap.get("instanceIds");
		String[] filters = reqMap.get("filters");
		String pageIndex = reqMap.get("pageIndex")[0];
		String limit = reqMap.get("pageSize")[0];
		String orgId = reqMap.get("orgId")[0];		
		String serviceId = reqMap.get("serviceId")[0];
		
		//对pageIndex、limit设定默认值
		pageIndex = pageIndex == null?"0":pageIndex;
		limit = limit == null?"20":limit;
		
		//中信和腾讯的起始页相差1
		Integer offset = Integer.parseInt(pageIndex)-1;
		
		//组装接口访问参数
		JSONObject params = new JSONObject();
		params.put("instanceIds", instanceIds);
		params.put("filters", filters);
		params.put("offset", offset.toString());
		params.put("limit", limit);
		
		LOG.info("开始查询serviceId="+serviceId+"实例列表：");
		return serviceManager.doSelectList(serviceId, orgId, params);
	}
	/**
	 * 创建实例接口
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@RequestMapping(value = "/service_instances",method = RequestMethod.POST)
	public BaseResult createInstances(@RequestBody JSONObject jsonParam) throws TencentCloudSDKException{
		
		//取出参数
		JSONObject citicInfo = jsonParam.getJSONObject("citicInfo");
		JSONObject params = jsonParam.getJSONObject("params");
		String serviceId = (String) jsonParam.get("serviceId");
		String orgId = citicInfo.getString("orgId");
		//暂时用不到
		/*JSONObject otherInfo = jsonParam.getJSONObject("otherInfo");
		String instanceId = (String) jsonParam.get("instanceId");		
		String requestId = (String) jsonParam.get("requestId");*/
		
		LOG.info("开始创建serviceId="+serviceId+"实例：");
		return serviceManager.doCreate(serviceId, orgId, params);
	}
	
	/**
	 * 修改实例接口
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@RequestMapping(value = "/service_instances",method = RequestMethod.PUT)
	public BaseResult changeInstances(@RequestBody JSONObject jsonParam) throws TencentCloudSDKException{
		
		//取出参数
		JSONObject citicInfo = jsonParam.getJSONObject("citicInfo");
		JSONObject params = jsonParam.getJSONObject("params");
		String serviceId = (String) jsonParam.get("serviceId");
		String orgId = citicInfo.getString("orgId");
		String instanceId = citicInfo.getString("instanceId");
		String operationType = citicInfo.getString("operationType");
		//暂时用不到
		/*JSONObject otherInfo = jsonParam.getJSONObject("otherInfo");
		String instanceId = (String) jsonParam.get("instanceId");		
		String requestId = (String) jsonParam.get("requestId");*/
		
		LOG.info("开始修改serviceId="+serviceId+"实例：");
		return serviceManager.doCreate(serviceId, orgId, params);
	}

}
