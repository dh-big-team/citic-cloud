package com.dhcc.citic.cloud.model;

import com.alibaba.fastjson.JSON;

public class CiticQueryResult
{
	private Integer currentPage;	//当前页
	private JSON data;	//返回的结果集
	private Integer totalCount;	//总共的条数
	private Integer totalPage;	//总共的页数
	
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
