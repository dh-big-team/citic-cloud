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
		publicParamMaps.put("DescribeInstances", new PublicParam(ApiUrl.CVM,ApiPath.V1,ApiVersion.V20170312));
		publicParamMaps.put("DescribeDisks", new PublicParam(ApiUrl.CBS,ApiPath.V1,ApiVersion.V20170312));
		publicParamMaps.put("DescribeDBInstances", new PublicParam(ApiUrl.CDB,ApiPath.V1,ApiVersion.V20170320));
		publicParamMaps.put("DescribeNatGateway", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		publicParamMaps.put("DescribeNatGateway", new PublicParam(ApiUrl.VPC,ApiPath.V1,ApiVersion.V20170312));
		publicParamMaps.put("DescribeProject", new PublicParam(ApiUrl.ACC,ApiPath.V2,ApiVersion.V20170312));
		publicParamMaps.put("AddUser", new PublicParam(ApiUrl.ACC,ApiPath.V2,ApiVersion.V20170312));
		publicParamMaps.put("ChannelRegisterUser", new PublicParam(ApiUrl.OPEN,ApiPath.V2,ApiVersion.V20170312));
		publicParamMaps.put("ChannelGetLoginToken", new PublicParam(ApiUrl.OPEN,ApiPath.V2,ApiVersion.V20170312));
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
