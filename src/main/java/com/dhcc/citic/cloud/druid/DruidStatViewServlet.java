package com.dhcc.citic.cloud.druid;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * 配置druid访问路径
 * 文件名称:     DruidStatViewServlet.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月21日下午5:28:18 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月21日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@WebServlet(urlPatterns="/druid/*",initParams={
	@WebInitParam(name="allow",value="192.168.1.72,127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
	@WebInitParam(name="deny",value="192.168.1.73"),// IP黑名单 (存在共同时，deny优先于allow)
	@WebInitParam(name="loginUsername",value="admin"),// 用户名
	@WebInitParam(name="loginPassword",value="123456"),// 密码
	@WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
}
)
public class DruidStatViewServlet extends StatViewServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
