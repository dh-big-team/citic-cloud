package com.example.bootdemo;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Test {
	
	public static void main(String args[]){
		String newPassword = new SimpleHash("md5", "123456", ByteSource.Util.bytes("123"), 1).toHex();
		System.out.println(newPassword);
	}
}
