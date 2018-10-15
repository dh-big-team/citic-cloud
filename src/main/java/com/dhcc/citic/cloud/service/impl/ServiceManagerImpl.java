package com.dhcc.citic.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.ServiceIdMappingConfig;
import com.dhcc.citic.cloud.service.CvmService;
import com.dhcc.citic.cloud.service.MysqlService;
import com.dhcc.citic.cloud.service.ServiceManager;
import com.dhcc.citic.cloud.service.VpnService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
/**
 * 
 * 类名称		        业务分发实现类
 * 文件名称:     ServiceManager.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月11日下午5:05:24
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@Service("serviceManager")
public class ServiceManagerImpl implements ServiceManager
{
	@Autowired
	ServiceIdMappingConfig serviceIdMappingConfig;
	@Autowired
	private CvmService cvmService;
	@Autowired
	private MysqlService mysqlService;
	@Autowired
	private VpnService vpnService;
	
	/**
	 * 查询实例列表业务分发
	 * @param serviceId
	 * @param orgId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult doSelectList(String serviceId, String orgId, JSONObject params) throws TencentCloudSDKException
	{
		//获取腾讯对应的产品
		String productId = serviceIdMappingConfig.getProductId(serviceId);
		//获取域名前缀
		String urlCode = serviceIdMappingConfig.getUrlCodeByProductId(productId);
		
		switch (productId)
		{
		case "cvm":
			return cvmService.describeInstances(urlCode, orgId, params);
		case "cbs":
			return null;
		case "mysql":
			return mysqlService.describeDBInstances(urlCode, orgId, params);
		case "nat":
			return null;
		case "vpn":
			return vpnService.describeVpnGateways(urlCode, orgId, params);
		case "IP":
			return null;
		default:
			throw new TencentCloudSDKException("The serviceId is nonsupport!");
		}
	}
	/**
	 * 创建实例业务分发
	 * @param serviceId
	 * @param orgId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult doCreate(String serviceId, String orgId, JSONObject params) throws TencentCloudSDKException
	{
		//获取腾讯对应的产品
		String productId = serviceIdMappingConfig.getProductId(serviceId);
		//获取域名前缀
		String urlCode = serviceIdMappingConfig.getUrlCodeByProductId(productId);
		
		switch (productId)
		{
		case "cvm":
			return cvmService.runInstances(urlCode, orgId, params);
		case "cbs":
			return null;
		case "mysql":
			return null;
		case "nat":
			return null;
		case "vpn":
			return vpnService.createVpnGateway(urlCode, orgId, params);
		case "IP":
			return null;
		default:
			throw new TencentCloudSDKException("The serviceId is nonsupport!");
		}
	}
	/**
	 * 修改实例业务分发
	 * @param serviceId
	 * @param orgId
	 * @param params
	 * @return
	 * @throws TencentCloudSDKException
	 */
	public BaseResult doUpdate(String serviceId, String orgId, JSONObject params) throws TencentCloudSDKException
	{
		//获取腾讯对应的产品
		String productId = serviceIdMappingConfig.getProductId(serviceId);
		//获取域名前缀
		String urlcode = serviceIdMappingConfig.getUrlCodeByProductId(productId);
		
		switch (productId)
		{
		case "cvm":
			return cvmService.runInstances(urlcode, orgId, params);
		case "cbs":
			return null;
		case "mysql":
			return null;
		case "nat":
			return null;
		case "vpn":
			return null;
		case "IP":
			return null;
		default:
			throw new TencentCloudSDKException("The serviceId is nonsupport!");
		}
	}
}
