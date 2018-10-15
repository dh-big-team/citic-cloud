package com.example.bootdemo;

import java.util.HashMap;
import com.dhcc.citic.cloud.utils.ReqParamUtil;

public class Test {
	
	public static void main(String args[]){
		//String newPassword = new SimpleHash("md5", "123456", ByteSource.Util.bytes("123"), 1).toHex();
		//System.out.println(newPassword);
		
		 String jsonStr = "{\"Placement\":{\"Zone\":\"ss\",\"ProjectId\":\"ss\",\"HostIds\":[\"sdf\",\"df\"]},\"HostChargePrepaid\":{\"Period\":\"fff\",\"RenewFlag\":\"fff\"},\"HostChargeType\":\"ffff\",\"HostType\":\"dddd\"}";
		 System.out.println(jsonStr);
		 HashMap<String,String> paramMap = new HashMap<String,String>();
		 ReqParamUtil.jsonStrToMap(paramMap,jsonStr);
		 for(String key : paramMap.keySet()){
			 System.out.println(key+"："+paramMap.get(key));
		 }
		
		int totalRecord = 0;
		int pageSize = 3;
		int totalPage = (totalRecord  +  pageSize  - 1) / pageSize;
		System.out.println(totalPage);
		
		 
	}
	
	
}
