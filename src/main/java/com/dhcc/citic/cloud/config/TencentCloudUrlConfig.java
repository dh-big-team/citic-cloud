package com.dhcc.citic.cloud.config;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
/**
 * 
 * 类名称	                   腾讯云接口地址配置类
 * 文件名称:     TencentCloudUrlConfig.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月11日上午9:49:49
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市吾爱付信息有限公司
 */
public class TencentCloudUrlConfig
{
	//中信商品ID对应腾讯产品ID的关系（对应关系还没定，暂时先这么写）
	private final static String serviceToProductId = "{cvm:\"cvm\",cbs:\"cbs\",csec=\"csec\",cdb=\"cdb\",vpc=\"vpc\"}";
	/**
	 * 根据商品ID，组装腾讯云接口地址
	 * @param serviceId 商品ID
	 * @param flag 版本号,v2写2,v3写3
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	public static String getEndPoint(String serviceId,Integer flag) throws TencentCloudSDKException
	{
		JSONObject serviceIds = JSONObject.parseObject(serviceToProductId);
		String productId = serviceIds.getString(serviceId);
		if (flag == 2 && productId != null)
		{
			//腾讯云v2版本的接口地址
			return serviceId+".api.qcloud.com";
		} else if(flag == 3 && productId != null){
			//腾讯云v3版本的接口地址
			return serviceId+".tencentcloudapi.com";
		}else{
			throw new TencentCloudSDKException("The TencentCloudUrl is not found!");
		}		
	}
	
	
	
	/**
	 * 从请求的Url中取得标识，通过标识组装腾讯云接口地址
	 * @param reqUrl
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	/*public static String getEndPoint(String reqUrl) throws TencentCloudSDKException
	{
		//去掉URL末尾多余的"/"
		String[] strs = reqUrl.replace("/", " ").trim().split(" ");
		String code = strs[strs.length-1];
		System.out.println("code==="+code);
		if (ArrayUtils.contains(codes, code))
		{
			if (code.contains("1"))
			{
				//腾讯云v2版本的接口地址
				return code.substring(0, code.length()-1)+".api.qcloud.com";
			} else
			{
				//腾讯云v3版本的接口地址
				return code+".tencentcloudapi.com";
			}			
		}else{
			throw new TencentCloudSDKException("The url is wrong!");
		}		
	}*/
}
