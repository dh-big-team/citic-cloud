package com.dhcc.citic.cloud.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 腾讯侧返回错误信息接收类
 * 文件名称:     JsonResponseErrModel.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年10月11日下午4:21:48 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class JsonResponseErrModel {
	@SerializedName("RequestId")
	@Expose
	public String requestId;
	
	@SerializedName("Error")
	@Expose
	public ErrorInfo error;
	
	class ErrorInfo {
		@SerializedName("Code")
		@Expose
		public String code;
		
		@Expose
		@SerializedName("Message")
		public String message;
	}
}
