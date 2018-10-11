package com.dhcc.citic.cloud.common;

import com.dhcc.citic.cloud.config.EnumConfig.ErrCode;
import com.dhcc.citic.cloud.config.EnumConfig.RetCode;

/**
 * 常规信息返回
 * 文件名称:     BaseResult.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年8月16日下午2:10:12 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年8月16日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public final class BaseResult {
	//返回码
	private String ret_code;
	//返回提示
	private String ret_msg;
	//错误码
	private String err_code;
	//错误提示
	private String err_msg;
	//返回数据
	private Object ret_data;
	
	private BaseResult(){}
	
	public BaseResult(RetCode retCode){
		setRet_code(retCode.getCode());
		setRet_msg(retCode.getDesc());
	}
	
	/**
	 * 返回枚举中列出的错误
	 * @param retCode
	 * @param errCode
	 */
	public BaseResult(RetCode retCode,ErrCode errCode){
		setRet_code(retCode.getCode());
		setRet_msg(retCode.getDesc());
		setErr_code(errCode.getCode());
		setErr_msg(errCode.getDesc());
	}
	
	/**
	 * 返回自定义的错误
	 * @param retCode
	 * @param errCode
	 * @param errMsg
	 */
	public BaseResult(RetCode retCode,String errCode,String errMsg){
		setRet_code(retCode.getCode());
		setRet_msg(retCode.getDesc());
		setErr_code(errCode);
		setErr_msg(errMsg);
	}
	
	/**
	 * 返回成功
	 * @param retData
	 */
	public BaseResult(Object retData){
		this.setRet_code(RetCode.OK.getCode());
		this.setRet_msg(RetCode.OK.getDesc());
		this.setRet_data(retData);
	}
	
	/**
	 * 返回通用枚举下的自定义错误内容
	 * @param retCode
	 * @param errCode
	 * @param errMsg
	 * @return
	 */
	public static BaseResult getDesignErr(RetCode retCode,ErrCode errCode,String errMsg){
		BaseResult baseResult = new BaseResult();
		baseResult.setRet_code(retCode.getCode());
		baseResult.setRet_msg(retCode.getDesc());
		baseResult.setErr_code(errCode.getCode());
		baseResult.setErr_msg(String.format(errCode.getDesc(), errMsg));
		return baseResult;
	}

	public String getRet_code() {
		return ret_code;
	}

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

	public String getRet_msg() {
		return ret_msg;
	}

	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	public Object getRet_data() {
		return ret_data;
	}
	public void setRet_data(Object ret_data) {
		this.ret_data = ret_data;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}

	

	
	
}
