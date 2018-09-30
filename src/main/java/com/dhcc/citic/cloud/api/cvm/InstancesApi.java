package com.dhcc.citic.cloud.api.cvm;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.ctrl.BaseCtrl;

@RequestMapping(value = "/api/cvm",produces = "application/json;charset=UTF-8")
public class InstancesApi extends BaseCtrl{
	
	@RequestMapping(value = "/DescribeInstances", method = RequestMethod.POST)
	public BaseResult apiDescribeInstances(@RequestBody JSONObject jsonParam){
		return null;
	}
}
