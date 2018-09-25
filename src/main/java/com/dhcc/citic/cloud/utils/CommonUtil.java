package com.dhcc.citic.cloud.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class CommonUtil {
	
	public static String encrypUserPwd(String userPwd,String salt){
		//MD5,hash一次
		return new SimpleHash("md5", userPwd, ByteSource.Util.bytes(salt), 1).toHex();
	}
}
