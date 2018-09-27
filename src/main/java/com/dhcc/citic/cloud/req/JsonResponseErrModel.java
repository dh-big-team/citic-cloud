package com.dhcc.citic.cloud.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
