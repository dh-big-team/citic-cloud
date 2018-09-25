package com.dhcc.citic.cloud.cache;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * redis核心设置类
 * 文件名称:     RedisCacheConfig.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年6月27日下午1:55:05 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年6月27日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Configuration
@EnableCaching //开启缓存支持
public class RedisCacheConfig extends CachingConfigurerSupport{
//     @Bean
//     public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//          RedisCacheManager redisCacheManager = RedisCacheManager.create(connectionFactory);
//          return  redisCacheManager;
//     }
	
	//缓存管理器  
    @Bean  
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {  
        //自定义名称为user的缓存
        RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
        		//设置过期时间为10秒
        		.entryTtl(Duration.ofSeconds(60))
        			//禁止缓存Null对象
        			.disableCachingNullValues()
        				//识别key的前缀
        				.prefixKeysWith("user");  
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<String, RedisCacheConfiguration>();
        redisCacheConfigurationMap.put("user", userCacheConfiguration);  
        //初始化一个RedisCacheWriter  
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);  
  
  
//        设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现  
//        ClassLoader loader = this.getClass().getClassLoader();  
//        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);  
//        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);  
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);  
        //默认缓存
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig(); 
        //设置默认超过期时间是30秒  
        defaultCacheConfig.entryTtl(Duration.ofSeconds(60));  
        //初始化RedisCacheManager  
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);  
        return cacheManager;  
    }  
}
