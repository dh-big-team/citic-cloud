package com.dhcc.citic.cloud.common;

import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * 带分页信息的数据集返回
 * 文件名称:     PageResult.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年8月16日下午2:11:31 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年8月16日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
public class PageData {
	//总数据条数
	private long data_count=0;
	//当前数据条数
	private long curr_count=0;
	//总页数
	private long page_count=0;
	//当前页码
	private long curr_page=0;
	//每页显示条数
	private long page_size=0;
	//结果集
	private List<Object> ret_data;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageData(PageInfo pageInfo){
		this.setRet_data(pageInfo.getList());
		this.setData_count(pageInfo.getTotal());
		this.setCurr_count(pageInfo.getSize());
		this.setPage_count(pageInfo.getPages());
		this.setCurr_page(pageInfo.getPageNum());
		this.setPage_size(pageInfo.getPageSize());
	}
	
	public long getData_count() {
		return data_count;
	}

	public void setData_count(long data_count) {
		this.data_count = data_count;
	}

	public long getCurr_count() {
		return curr_count;
	}

	public void setCurr_count(long curr_count) {
		this.curr_count = curr_count;
	}

	public long getPage_count() {
		return page_count;
	}

	public void setPage_count(long page_count) {
		this.page_count = page_count;
	}

	public long getCurr_page() {
		return curr_page;
	}

	public void setCurr_page(long curr_page) {
		this.curr_page = curr_page;
	}

	public long getPage_size() {
		return page_size;
	}

	public void setPage_size(long page_size) {
		this.page_size = page_size;
	}

	public List<Object> getRet_data() {
		return ret_data;
	}

	public void setRet_data(List<Object> ret_data) {
		this.ret_data = ret_data;
	}

	
}
