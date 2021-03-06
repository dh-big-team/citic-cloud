package com.dhcc.citic.cloud.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.EnumConfig.ErrCode;
import com.dhcc.citic.cloud.config.EnumConfig.RetCode;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

/**
 * 异常统一捕获处理类
 * 文件名称:     MyExceptionHandler.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年8月16日下午2:08:59 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年8月16日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(MyExceptionHandler.class);
	
	/**
	 * Exception异常处理
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handlerMyException(Exception ex) {
        LOG.error("统一异常处理，ErrCode={}，ErrContent=",ErrCode.COM_SYS_ERR,ex);
        return new BaseResult(RetCode.SYS_ERR,ErrCode.COM_SYS_ERR.getCode(),
        		String.format(ErrCode.COM_SYS_ERR.getDesc(),ex.toString()));
    }

	/**
	 * RuntimeException异常处理
	 * @param ex
	 * @return
	 */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handlerMyRuntimeException(RuntimeException ex) {
    	LOG.error("统一异常处理，ErrCode={}，ErrContent=",ErrCode.COM_RUN_ERR,ex);
    	return new BaseResult(RetCode.RUN_ERR,ErrCode.COM_RUN_ERR.getCode(),
        		String.format(ErrCode.COM_RUN_ERR.getDesc(),ex.toString()));
    }
    
    /**
     * 空指针异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handlerNullPointerException(NullPointerException ex) {
    	LOG.error("统一异常处理，ErrCode={}，ErrContent=",ErrCode.COM_BUSI_ERR,ex);
        return new BaseResult(RetCode.BUSI_ERR,ErrCode.COM_BUSI_ERR.getCode(),
        		String.format(ErrCode.COM_BUSI_ERR.getDesc(),ex.toString()));
    }

    
    /**
     * 腾讯云API调用异常或返回错误
     * @param ex
     * @return
     */
    @ExceptionHandler(TencentCloudSDKException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handlerTencentCloudSDKException(TencentCloudSDKException ex){
    	LOG.error("统一异常处理，ErrCode={}，ErrContent=",ErrCode.COM_SDK_ERR,ex);
    	return new BaseResult(RetCode.SDK_ERR,ErrCode.COM_SDK_ERR.getCode(),
        		String.format(ErrCode.COM_SDK_ERR.getDesc(),ex.toString()));
    }
}
