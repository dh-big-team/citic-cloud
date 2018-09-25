package com.example.bootdemo.proxy;

public class ProxyTest {
	
	public static void main(String args[]){
		ProxyTest.testDynamicProxy();
	}
	
	/**
	 * 测试静态代理
	 */
	public static void testStaticProxy(){
		ITestDao target = new TestDao();
		StaticProxy proxy = new StaticProxy(target);
		proxy.save();
	}
	
	public static void testDynamicProxy(){
		ITestDao target = new TestDao();
		ITestDao proxy = (ITestDao)new DynamicProxy(target).getProxyInstance();
		proxy.save();
	
	}
}
