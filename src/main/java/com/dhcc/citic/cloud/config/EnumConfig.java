package com.dhcc.citic.cloud.config;

/**
 * 返回码定义
 * 文件名称:     ResultCodeConfig.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年8月16日下午2:13:27 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年8月16日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
public class EnumConfig {
	
	public enum RetCode{
		OK("00","成功"),
		SYS_ERR("01","系统异常"),
		RUN_ERR("02","运行异常"),
		DATA_ERR("03","数据异常"),
		BUSI_ERR("04","业务逻辑异常"),
		VALID_ERR("05","数据校验异常"),
		OTHER_ERR("06","其它异常");
		
		private String code;
		private String desc;
		
		private RetCode(String code,String desc){
			this.code = code;
			this.desc = desc;
		}
		public String getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}

		
	}
	
	public enum ErrCode{
		//系统Exception类异常自定义提示
		COM_SYS_ERR("010001","COM_SYS_ERR：%s"),	
		//系统RuntimeExcpetion类异常自定义提示
		COM_RUN_ERR("020001","COM_RUN_ERR：%s"),
		//数据异常错误自定义提示
		COM_DATA_ERR("030001","COM_DATA_ERR：%s"),
		//业务逻辑错误自定义提示
		COM_BUSI_ERR("040001","COM_BUSI_ERR：%s"),
		//校验类错误自定义提示
		COM_VALID_ERR("050001","COM_VALID_ERR：%s"),
		VALID_NAMEPWD_ERR("050002","用户名或密码错误"),
		//其他异常
		COM_OTHER_ERR("060001","COM_OTHER_ERR：%s");
		
		private String code;
		private String desc;
		
		private ErrCode(String code,String desc){
			this.code = code;
			this.desc = desc;
		}
		public String getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
	}
	
	//用户状态
	public enum UserStatus{
		Active('1'),InActive('0');
		private final char status;
	    private UserStatus(char status){
	    	this.status = status;
	    }
	    public char getStatus() {
	        return status;
	    }
	}
}
