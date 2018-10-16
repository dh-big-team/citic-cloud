package com.dhcc.citic.cloud.service;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

/**
 * @Description:Nat网关产品的业务接口类
 * @filename:NatGatewayService.java
 * @author:duan zhang quan
 * @version:1.0  

 * 
 * 修改历史:  
 * 修改日期                     修改人员                                  版本 	   修改内容  
 * ----------------------------------------------  
 * 2018年10月12日     duan zhang quan   1.0     XXXX
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public interface NatGatewayService {
	/**
	 * 查询NAT网关实例
	 * @param orgId
	 * @param params
	 * @return
	 */
	public BaseResult describeNatGateway(String orgId, JSONObject params) throws TencentCloudSDKException;
}
