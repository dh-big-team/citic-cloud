package com.dhcc.citic.cloud.service;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
/**
 * 
 * 类名称		       云硬盘业务接口类
 * 文件名称:     CbsService.java
 * 内容摘要: 
 * @author:   duan zhang quan
 * @version:  1.0  
 * @Date:     2018年10月15日
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public interface CbsService {
	
	/**
	 * 查询云硬盘实例列表
	 * @return
	 * @throws TencentCloudSDKException
	 */
	
	public BaseResult describeDisks(String orgId,JSONObject params) throws TencentCloudSDKException;
}
