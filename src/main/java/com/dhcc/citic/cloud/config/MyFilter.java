package com.dhcc.citic.cloud.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义过滤器
 * 文件名称:     MyFilter.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月23日下午4:11:36 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月23日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@WebFilter(urlPatterns={"/myfilter/*","/filter/*"})
public class MyFilter implements Filter{

	private static final Logger Log=LoggerFactory.getLogger(MyFilter.class);
			
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Log.debug("----myFilter init---");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Log.debug("----myFilter doFilter---");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		Log.debug("----myFilter destroy---");
	}

}
