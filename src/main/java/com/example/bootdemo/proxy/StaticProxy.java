package com.example.bootdemo.proxy;

/**
 * 静态代理类
 * 文件名称:     StaticProxy.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年6月29日下午2:13:47 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年6月29日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
public class StaticProxy implements ITestDao{
	
	private ITestDao target;
	
	public StaticProxy(ITestDao target){
		this.target = target;
	}

	@Override
	public void save() {
		 System.out.println("开始保存...");
	     target.save();//执行目标对象的方法
	     System.out.println("保存成功...");
	}

}
