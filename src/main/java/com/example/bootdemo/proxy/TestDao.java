package com.example.bootdemo.proxy;

/**
 * 代理目标类
 * 文件名称:     TestDao.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年6月29日下午2:14:02 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年6月29日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
public class TestDao implements ITestDao {

	@Override
	public void save() {
		System.out.println("真身保存成功！");
	}

}
