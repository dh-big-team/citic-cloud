package com.dhcc.citic.cloud.model;

import java.io.Serializable;

/**
 * 分页请求参数
 * 文件名称:     PageParam.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年8月16日下午2:15:07 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年8月16日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class PageParam implements Serializable{
	
	private static final long serialVersionUID = 3902057713192588762L;

	private Integer pageNum = 1;
   
	private Integer pageSize = 10;
	
	public PageParam(){}
	
	public PageParam(Integer pageNum,Integer pageSize){
		setPageNum(pageNum);
		setPageSize(pageSize);
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	
    
    
}
