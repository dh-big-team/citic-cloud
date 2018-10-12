package com.dhcc.citic.cloud.common;

import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
