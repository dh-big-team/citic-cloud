package com.dhcc.citic.cloud.config;

import java.util.HashMap;
import java.util.Map;

import com.dhcc.citic.cloud.config.EnumConfig.ApiPath;
import com.dhcc.citic.cloud.config.EnumConfig.ApiUrl;
import com.dhcc.citic.cloud.config.EnumConfig.ApiVersion;

/**
 * @Description:腾讯api公共参数配置类
 * @filename:PublicParamConfig.java
 * @author:Zeng Dongcheng
 * @version:1.0  
 * @Date:下午3:10:59 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                  版本 	   修改内容  
 * ----------------------------------------------  
 * 2018年10月15日     Zeng Dongcheng   1.0     XXXX
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class PublicParamConfig {
	public static final Map<String,PublicParam> publicParamMaps= new HashMap<String,PublicParam>();
	
	static{
		/*********************云主机*****************/
		//查询实例列表
		publicParamMaps.put("DescribeInstances", new PublicParam(ApiUrl.CVM,ApiPath.V1,ApiVersion.V20170312));
		//创建实例
		publicParamMaps.put("RunInstances", new PublicParam(ApiUrl.CVM,ApiPath.V1,ApiVersion.V20170312));
		
		
		/*********************云硬盘*****************/
		//查询实例列表
		publicParamMaps.put("DescribeDisks", new PublicParam(ApiUrl.CBS,ApiPath.V1,ApiVersion.V20170312));
		
		
		/*********************云数据库mysql*****************/
		//查询实例列表
		publicParamMaps.put("DescribeDBInstances", new PublicParam(ApiUrl.CDB,ApiPath.V1,ApiVersion.V20170320));	
		//创建实例(包年包月)
		publicParamMaps.put("CreateDBInstance", new PublicParam(ApiUrl.CDB,ApiPath.V1,ApiVersion.V20170320));
		
		
		/*********************VPN网关*****************/
		//查询实例列表
		publicParamMaps.put("DescribeVpnGateways", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		//创建实例
		publicParamMaps.put("CreateVpnGateway", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		
		
		/*********************私有网络*****************/
		//查询实例列表（创建VPN网关需要VpcId）
		publicParamMaps.put("DescribeVpcs", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		//创建实例（暂时仅作测试用）
		publicParamMaps.put("CreateVpc", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		
		
		/*********************弹性公网IP*****************/
		//查询实例列表
		publicParamMaps.put("DescribeAddresses", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		//创建实例
		publicParamMaps.put("AllocateAddresses", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		
		
		/*********************Nat网关*****************/
		//查询实例
		publicParamMaps.put("DescribeNatGateway", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		
		
		/*********************用户相关*****************/
		//添加用户
		publicParamMaps.put("ChannelRegisterUser", new PublicParam(ApiUrl.OPEN,ApiPath.V2,ApiVersion.V20170312));
		//获取用户登录Token
		publicParamMaps.put("ChannelGetLoginToken", new PublicParam(ApiUrl.OPEN,ApiPath.V2,ApiVersion.V20170312));
		//获取用户临时秘钥
		publicParamMaps.put("AssumeRole", new PublicParam(ApiUrl.STS,ApiPath.V2,ApiVersion.V20170312));
		
		
		/*********************公共部分*****************/
		//获取区域
		publicParamMaps.put("DescribeRegions", new PublicParam(ApiUrl.CVM,ApiPath.V1,ApiVersion.V20170312));
		
	}

	
	public static class PublicParam{
		private String apiUrl;
		private String apiPath;
		private String apiVersion;
		
		
		/**
		 * 构造公共请求参数中的固定值
		 * @param apiPath  
		 * @param apiVersion
		 * @param apiUrl
		 */
		PublicParam(ApiUrl apiUrl,ApiPath apiPath,ApiVersion apiVersion){
			setApiUrl(apiUrl.getUrl());
			setApiPath(apiPath.getPath());
			setApiVersion(apiVersion.getVersion());
		}

		public String getApiPath() {
			return apiPath;
		}

		public void setApiPath(String apiPath) {
			this.apiPath = apiPath;
		}

		public String getApiVersion() {
			return apiVersion;
		}

		public void setApiVersion(String apiVersion) {
			this.apiVersion = apiVersion;
		}

		public String getApiUrl() {
			return apiUrl;
		}

		public void setApiUrl(String apiUrl) {
			this.apiUrl = apiUrl;
		}
	}
}
