package com.dhcc.citic.cloud.utils;

import java.util.HashMap;
import java.util.Set;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 腾讯侧api请求参数转换类
 * 文件名称:     ReqParamUtil.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年10月11日上午11:31:22 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月11日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class ReqParamUtil {
	
	/**
	 * 将json字符串转为腾讯api要求格式的map
	 * @param jsonStr
	 * @return
	 */
	public static void jsonStrToMap(HashMap<String,String> paramMap,String jsonStr){
		JSONObject json = JSONObject.parseObject(jsonStr);
		Set<String> it = json.keySet();
		for(String key : it){
			Object obj = json.get(key);
			//首字母大写处理
			key = key.substring(0, 1).toUpperCase() + key.substring(1);
			if(obj instanceof JSONObject || obj instanceof JSONArray){
				ReqParamUtil.relaxJsonStr(obj, key, paramMap); 
			}else{
				paramMap.put(key, String.valueOf(obj));
			}
		}
		 
	}
	
	/**
	 * 递归解析Json对象
	 * @param sourceJson
	 * @param prefix
	 * @param param
	 */
	private static void relaxJsonStr(Object sourceJson,String prefix,HashMap<String,String> param){
		if(sourceJson instanceof JSONObject){
			JSONObject jsonObject = (JSONObject)sourceJson;
			Set<String> it = jsonObject.keySet();
			for(String key : it){
				Object obj = jsonObject.get(key);
				//首字母大写处理
				key = key.substring(0, 1).toUpperCase() + key.substring(1);
				if(obj instanceof JSONObject || obj instanceof JSONArray){
					
					relaxJsonStr(obj,prefix+"."+key,param);
				}else{
					param.put(prefix+"."+key, String.valueOf(obj));
				}
			}
		}else{
			JSONArray jsonArray = (JSONArray) sourceJson;
			int i=0;
			for(Object jsonObject : jsonArray){
				if(jsonObject instanceof JSONObject || jsonObject instanceof JSONArray){
					relaxJsonStr(jsonObject,prefix+"."+i,param);
				}else{
					param.put(prefix+"."+i,String.valueOf(jsonObject));
				}
				++i;
			}
		}
	}
}
