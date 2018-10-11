package com.dhcc.citic.cloud.req;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dhcc.citic.cloud.config.QcloudConfig;
import com.tencentcloudapi.common.Credential;

/**
 * 腾讯云api通用请求参数类
 * 文件名称:     BaseReq.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年10月8日下午3:54:18 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年10月8日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   东华云计算有限公司
 */
public class BaseReq {
	
	public static final Map<String, String> HOSTS = new HashMap<String,String>();

	//地址集
    static {
        HOSTS.put("apigateway", "apigateway.api.qcloud.com");
        HOSTS.put("account", "account.api.qcloud.com");
        HOSTS.put("batch", "batch.api.qcloud.com");
        HOSTS.put("bgpip", "bgpip.api.qcloud.com");
        HOSTS.put("bill", "bill.api.qcloud.com");
        HOSTS.put("bm", "bm.api.qcloud.com");
        HOSTS.put("bmeip", "bmeip.api.qcloud.com");
        HOSTS.put("bmlb", "bmlb.api.qcloud.com");
        HOSTS.put("bmvpc", "bmvpc.api.qcloud.com");//batch
        HOSTS.put("cam", "cam.api.qcloud.com");
        HOSTS.put("cbs", "cbs.api.qcloud.com");
        HOSTS.put("cbs2", "cbs.tencentcloudapi.com");
        HOSTS.put("cdb", "cdb.api.qcloud.com");
        HOSTS.put("cdb2", "cdb.tencentcloudapi.com");
        HOSTS.put("cdn", "cdn.api.qcloud.com");
        HOSTS.put("cfs", "cfs.api.qcloud.com");
        HOSTS.put("cloudaudit", "cloudaudit.api.qcloud.com");
        HOSTS.put("cls", "cls.api.qcloud.com");
        HOSTS.put("clsl", "{$region}.cls.myqcloud.com");//日志集管理
        HOSTS.put("cmem", "cmem.api.qcloud.com");
        HOSTS.put("cns", "cns.api.qcloud.com");
        HOSTS.put("cvm", "cvm.api.qcloud.com");
        HOSTS.put("cvm2", "cvm.tencentcloudapi.com");
        HOSTS.put("csec", "csec.api.qcloud.com");
        HOSTS.put("cdnn", "cdn.api.qcloud.com");// 海外加速
        HOSTS.put("ccr", "ccr.api.qcloud.com");// 镜像仓库
        HOSTS.put("cam", "cam.api.qcloud.com");
        HOSTS.put("ckafka", "ckafka.api.qcloud.com");
        HOSTS.put("cmqt", "cmq-topic-{$region}.api.qcloud.com");//主题模型
        HOSTS.put("cmqq", "cmq-queue-{$region}.api.qcloud.com");//队列模型
        HOSTS.put("ckafka","ckafka.api.qcloud.com");//消息队列
        HOSTS.put("ccs", "ccs.api.qcloud.com");//容器服务
        HOSTS.put("dc", "dc.api.qcloud.com");
        HOSTS.put("dcdb", "dcdb.tencentcloudapi.com");
        HOSTS.put("dfw", "dfw.api.qcloud.com");
        HOSTS.put("dsa","dsa.api.qcloud.com");//动态加速
        HOSTS.put("dts","dts.tencentcloudapi.com");//DTS(数据传输)
        HOSTS.put("dc2", "dc.tencentcloudapi.com");//专线通道V3版本接口
        HOSTS.put("eip", "eip.api.qcloud.com");
        HOSTS.put("emr", "emr.api.qcloud.com");//EMR(弹性Map)
        HOSTS.put("feecenter", "feecenter.api.qcloud.com");
        HOSTS.put("fcgi", "fcgi.video.qcloud.com");//直播，操作类直播    
        HOSTS.put("gaap", "gaap.tencentcloudapi.com");//全球海外加速V3
        HOSTS.put("image", "image.api.qcloud.com");
        HOSTS.put("kms","kms-{$region}.api.qcloud.com");//外网密钥
        HOSTS.put("lb", "lb.api.qcloud.com");
        HOSTS.put("live", "live.api.qcloud.com");//直播 
        HOSTS.put("live2", "live.tencentcloudapi.com");//直播V3版接口
        HOSTS.put("market", "market.api.qcloud.com");
        HOSTS.put("market2", "market.tencentcloudapi.com");//云市场V3版
        HOSTS.put("monitor", "monitor.api.qcloud.com");
        HOSTS.put("mongodb", "mongodb.api.qcloud.com");
        HOSTS.put("scaling", "scaling.api.qcloud.com");
        HOSTS.put("open", "open.api.qcloud.com");
        HOSTS.put("opc", "opc.api.qcloud.com");
        HOSTS.put("partners", "partners.api.qcloud.com");
        HOSTS.put("postgres", "postgres.tencentcloudapi.com");//数据库
        HOSTS.put("postgres2", "postgres.tencentcloudapi.com");// 数据库 PostgreSQL
        HOSTS.put("pfsec", "pfsec.tencentcloudapi.com");//25端口解封配额
        HOSTS.put("redis", "redis.api.qcloud.com");
        HOSTS.put("scf", "scf.api.qcloud.com");
        HOSTS.put("sec", "csec.api.qcloud.com");
        HOSTS.put("scaling", "scaling.api.qcloud.com");
        HOSTS.put("snapshot", "snapshot.api.qcloud.com");
        HOSTS.put("sts", "sts.api.qcloud.com");
        HOSTS.put("sqlserver", "sqlserver.api.qcloud.com");//sqlserver
        HOSTS.put("sqlserver2", "sqlserver.tencentcloudapi.com");//sqlserver  V3版本
        HOSTS.put("tag", "tag.api.qcloud.com");
        HOSTS.put("tdsql", "tdsql.api.qcloud.com");
        HOSTS.put("ticket", "ticket.api.qcloud.com");
        HOSTS.put("tmt", "tmt.api.qcloud.com");
        HOSTS.put("trade", "trade.api.qcloud.com");
        HOSTS.put("vod", "vod.api.qcloud.com");
        HOSTS.put("vpc", "vpc.api.qcloud.com");
        HOSTS.put("vpc2", "vpc.tencentcloudapi.com");//检查账户属性，确认是否支持选择基础网络
        HOSTS.put("vod", "vod.api.qcloud.com");//点播
        HOSTS.put("waf", "waf.api.qcloud.com");
        HOSTS.put("wenzhi", "wenzhi.api.qcloud.com");
        HOSTS.put("wss", "wss.api.qcloud.com");
        HOSTS.put("yunjing", "yunjing.api.qcloud.com");
        HOSTS.put("yunsou", "yunsou.api.qcloud.com");
        HOSTS.put("open", "open.api.qcloud.com");
    }
    
    //云平台账号信息
    @Autowired
	private QcloudConfig qcloudConfig;
    //请求域名
    private String endpoint= "cvm.tencentcloudapi.com";
    //区域(默认广州)
	private String region= "ap-guangzhou";
	//二级路径
	private String path="/v2/index.php";
	//api版本
    private String apiVersion="2017-03-12";
    //认证相关信息类
    private Credential cred;
	
    /**
     * 初始化请求参数
     * @param reqCode
     */
    public BaseReq(String reqCode){
    	this.endpoint = HOSTS.get(reqCode);
    	if ("dcdb".equals(reqCode) || "cvm2".equals(reqCode) || "cbs2".equals(reqCode) || "cdb2".equals(reqCode)|| "vpc2".equals(reqCode)
                || "postgres2".equals(reqCode)|| "market2".equals(reqCode)|| "sqlserver2".equals(reqCode)|| "dts".equals(reqCode)|| "pfsec".equals(reqCode)|| "gaap".equals(reqCode)|| "dc2".equals(reqCode)|| "live2".equals(reqCode)) {
            this.path = "/";
        }
    	cred = new Credential(qcloudConfig.getSecretId(), qcloudConfig.getSecretKey());
    }
	
    /**
     * 构造腾讯云api请求参数
     * @return
     */
    public ApiRequest createApiRequest(){
		return new ApiRequest(endpoint,apiVersion,path,cred,region);
    }

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
    
    
}
