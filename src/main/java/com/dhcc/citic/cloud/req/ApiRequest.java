package com.dhcc.citic.cloud.req;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.config.PublicParamConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Response;
import com.tencentcloudapi.common.AbstractClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.JsonResponseModel;
import com.tencentcloudapi.common.Sign;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.http.HttpConnection;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 腾讯侧api请求包装类
 * 文件名称:     ApiRequest.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年10月11日下午4:21:12 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class ApiRequest{
    
	private Credential credential;
	private ClientProfile profile;
	private String endpoint= "cvm.tencentcloudapi.com";
	private String region= "ap-guangzhou";
	private String path="/";
    private String sdkVersion="SDK_JAVA_3.0.8";
    private String apiVersion="2017-03-12";
    private String actionName;
    public Gson gson;
    
    /**
	 * 设置产品地域
	 * @param region 产品地域
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	
	/**
	 * 返回产品地域
	 * @return 地域名称
	 */
	public String getRegion() {
		return this.region;
	}
	
	/**
	 * 设置配置实例
	 * @param profile 配置实例
	 */
	public void setClientProfile(ClientProfile profile) {
		this.profile = profile;
	}
	
	/**
	 * 获取配置实例
	 * @return 配置实例
	 */
	public ClientProfile getClientProfile() {
		return this.profile;
	}
	
	
	/**
	 * 设置认证信息实例
	 * @param credential 认证信息实例
	 */
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	
	/**
	 * 获取认证信息实例
	 * @return 认证信息实例
	 */
	public Credential getCredential() {
		return this.credential;
	}
	
	
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	
    
    /**
     * 构造请求request(区域传默认值ap-guangzhou建议用这个)
     * @param actionName  腾讯侧api的接口名称
     * @param credential  用户认证信息
     */
    public ApiRequest(String actionName,Credential credential) {
    	this.actionName = actionName;
    	this.credential = credential;
		this.profile = new ClientProfile();
		PublicParamConfig.PublicParam param = PublicParamConfig.publicParamMaps.get(actionName);
		this.endpoint = param.getApiUrl();
		this.path = param.getApiPath();
		this.apiVersion = param.getApiVersion();
		this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
    
    /**
     * 构造请求request(自定义区域)
     * @param actionName  腾讯侧api的接口名称
     * @param credential  用户认证信息
     * @param region	     区域
     */
    public ApiRequest(String actionName,Credential credential,String region) {
    	this.actionName = actionName;
    	this.credential = credential;
		this.profile = new ClientProfile();
		PublicParamConfig.PublicParam param = PublicParamConfig.publicParamMaps.get(actionName);
		this.endpoint = param.getApiUrl();
		this.path = param.getApiPath();
		this.apiVersion = param.getApiVersion();
		this.region = region;
		this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
    
    /**
     * 构造request(特殊案例走原生传值)
     * @param endpoint   api地址
     * @param version    api版本
     * @param path       api路径(/或/v2/index)
     * @param credential 秘钥相关
     * @param region     区域(默认ap-guangzhou)
     */
    public ApiRequest(String actionName,String endpoint,String path,Credential credential, 
    		String region,String apiVersion) {
    	this.actionName = actionName;
    	this.credential = credential;
		this.profile = new ClientProfile();
		this.endpoint = endpoint;
		this.region = region;
		this.path = path;
		this.apiVersion = apiVersion;
		this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
    
    
    /**
     * 返回结构包含Response方式的请求
     * @param param
     * @param actionName
     * @return
     * @throws TencentCloudSDKException
     */
    public JSONObject recvResponseRequest(Map<String, String> param)  throws TencentCloudSDKException {
		
		Response okRsp = null;
		String endpoint = this.endpoint;
		if (!(this.getClientProfile().getHttpProfile().getEndpoint() == null)) {
			endpoint = this.getClientProfile().getHttpProfile().getEndpoint();
		}
		String strParam = this.formatRequestData(actionName, param);
		HttpConnection conn = new HttpConnection(this.profile.getHttpProfile().getConnTimeout(), 
				this.profile.getHttpProfile().getReadTimeout(), this.profile.getHttpProfile().getWriteTimeout());
		
		if (this.profile.getHttpProfile().getReqMethod().equals(HttpProfile.REQ_GET)) {
			
			okRsp = conn.getRequest(this.profile.getHttpProfile().getProtocol() + 
					endpoint + this.path +  "?" + strParam);

		} else if (this.profile.getHttpProfile().getReqMethod().equals(HttpProfile.REQ_POST)) {
			
			okRsp = conn.postRequest(this.profile.getHttpProfile().getProtocol() + 
					endpoint + this.path, strParam);
			
		} else {
			throw new TencentCloudSDKException("Method only support (GET, POST)");
		}
		
		if (okRsp.code() != AbstractClient.HTTP_RSP_OK) {
			throw new TencentCloudSDKException(okRsp.code()+okRsp.message());
		}
		String strResp = null;
		try {
			strResp = okRsp.body().string();
		} catch (IOException e) {
			throw new TencentCloudSDKException(e.getMessage());
		}

		JsonResponseModel<JsonResponseErrModel> errResp = null;
		try {
				Type errType = new TypeToken<JsonResponseModel<JsonResponseErrModel>>() {  
				}.getType(); 
				errResp  = gson.fromJson(strResp, errType);	
				
		} catch (JsonSyntaxException e) {
			throw new TencentCloudSDKException(e.getMessage());
		}
		if (errResp!=null && errResp.response.error != null) {
			throw new TencentCloudSDKException(errResp.response.error.code + "-" + errResp.response.error.message, 
					errResp.response.requestId);
		}
		
		return JSONObject.parseObject(strResp).getJSONObject("Response");
	}
    
    /**
     * 返回结构为{code:0,message:xx,data:xxx}格式的请求
     * @param param
     * @param actionName
     * @return
     * @throws TencentCloudSDKException
     */
    public JSON recvCodeRequest(Map<String, String> param)  throws TencentCloudSDKException {
		Response okRsp = null;
		String endpoint = this.endpoint;
		if (!(this.getClientProfile().getHttpProfile().getEndpoint() == null)) {
			endpoint = this.getClientProfile().getHttpProfile().getEndpoint();
		}
		String strParam = this.formatRequestData(actionName, param);
		HttpConnection conn = new HttpConnection(this.profile.getHttpProfile().getConnTimeout(), 
				this.profile.getHttpProfile().getReadTimeout(), this.profile.getHttpProfile().getWriteTimeout());
		
		if (this.profile.getHttpProfile().getReqMethod().equals(HttpProfile.REQ_GET)) {
			
			okRsp = conn.getRequest(this.profile.getHttpProfile().getProtocol() + 
					endpoint + this.path +  "?" + strParam);

		} else if (this.profile.getHttpProfile().getReqMethod().equals(HttpProfile.REQ_POST)) {
			
			okRsp = conn.postRequest(this.profile.getHttpProfile().getProtocol() + 
					endpoint + this.path, strParam);
			
		} else {
			throw new TencentCloudSDKException("Method only support (GET, POST)");
		}
		
		if (okRsp.code() != AbstractClient.HTTP_RSP_OK) {
			throw new TencentCloudSDKException(okRsp.code()+okRsp.message());
		}
		String strResp = null;
		try {
			strResp = okRsp.body().string();
		} catch (IOException e) {
			throw new TencentCloudSDKException(e.getMessage());
		}
		JSONObject jsonRep = JSONObject.parseObject(strResp);
		if(jsonRep!=null && !"0".equals(jsonRep.getString("code"))){
			throw new TencentCloudSDKException(jsonRep.getString("code") + "-" + jsonRep.getString("message"));
		}
		//返回的data中可能是数组
		if(jsonRep.get("data") instanceof JSONArray){
			return jsonRep.getJSONArray("data");
		}
		return jsonRep.getJSONObject("data");
	}
    
    private String formatRequestData(String action, Map<String, String> param) throws TencentCloudSDKException {
		
		param.put("Action", action);
		param.put("RequestClient", this.sdkVersion);
		param.put("Nonce", String.valueOf(Math.abs(new Random().nextInt())));
		param.put("Timestamp", String.valueOf(System.currentTimeMillis() / 1000));
		param.put("Version", this.apiVersion);
		
		if (this.credential.getSecretId() != null && (!this.credential.getSecretId().isEmpty())) {
			param.put("SecretId", this.credential.getSecretId());
		}
		
		if (this.region != null && (!this.region.isEmpty())) {
			param.put("Region", this.region);
		}
		
		if (this.profile.getSignMethod() != null && (!this.profile.getSignMethod().isEmpty())) {
			param.put("SignatureMethod", this.profile.getSignMethod());
		}
		
		if (this.credential.getToken() != null && (!this.credential.getToken().isEmpty())) {
			param.put("Token", this.credential.getToken());
		}
		
		String endpoint = this.endpoint;
		if (!(this.profile.getHttpProfile().getEndpoint() == null)) {
			endpoint = this.profile.getHttpProfile().getEndpoint();
		}
		
		String sigInParam = Sign.makeSignPlainText(new TreeMap<String, String>(param), 
				this.profile.getHttpProfile().getReqMethod(), endpoint, this.path);
		String sigOutParam = Sign.sign(this.credential.getSecretKey(), sigInParam, this.profile.getSignMethod());

		
		String strParam = "";
		try {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				strParam += (URLEncoder.encode(entry.getKey(), "utf-8") + "=" + 
						URLEncoder.encode(entry.getValue(), "utf-8") + "&");
			}
			strParam += ("Signature=" + URLEncoder.encode(sigOutParam, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new TencentCloudSDKException(e.getMessage());
		}
		return strParam;
	}
    
	
}
