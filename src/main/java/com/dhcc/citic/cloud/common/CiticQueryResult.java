package com.dhcc.citic.cloud.common;

import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 类名称		       查询实例列表返回实体类
 * 文件名称:     CiticQueryResult.java
 * 内容摘要: 
 * @author:   Zhao Xiaoman
 * @version:  1.0  
 * @Date:     2018年10月12日下午5:14:57
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月12日   Zhao Xiaoman       1.0       新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class CiticQueryResult
{
	private Integer currentPage;	//当前页
	private JSON data;	//返回的结果集
	private Integer totalCount;	//总共的条数
	private Integer totalPage;	//总共的页数
	
	/**
	 * 默认构造
	 */
	public CiticQueryResult(){};
	
	/**
	 * 根据腾讯云接口请求参数和返回数据，生成中信返回数据
	 * @param reqMap 接口请求参数
	 * @param rep	接口返回数据
	 * @param code	接口返回数据中数组的key
	 */
	public CiticQueryResult(Map<String, String> reqMap,JSONObject rep,String code){
		Integer limit = Integer.parseInt(reqMap.get("Limit"));
		this.currentPage = Integer.parseInt(reqMap.get("Offset"))+1;
		this.data = rep.getJSONArray(code);
		this.totalCount = rep.getInteger("TotalCount");
		this.totalPage = (totalCount-1)/limit+1;
	}
	
	/**
	 * 全参构造
	 * @param currentPage
	 * @param data
	 * @param totalCount
	 * @param totalPage
	 */
	public CiticQueryResult(Integer currentPage,JSON data,Integer totalCount,Integer totalPage){
		this.currentPage = currentPage;
		this.data = data;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
	}
	
	public Integer getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage)
	{
		this.currentPage = currentPage;
	}
	public JSON getData()
	{
		return data;
	}
	public void setData(JSON data)
	{
		this.data = data;
	}
	public Integer getTotalCount()
	{
		return totalCount;
	}
	public void setTotalCount(Integer totalCount)
	{
		this.totalCount = totalCount;
	}
	public Integer getTotalPage()
	{
		return totalPage;
	}
	public void setTotalPage(Integer totalPage)
	{
		this.totalPage = totalPage;
	}
}
