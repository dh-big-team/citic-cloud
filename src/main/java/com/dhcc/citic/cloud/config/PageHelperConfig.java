package com.dhcc.citic.cloud.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageInterceptor;

/**
 * 分页配置
 * 文件名称:     PageHelperConfig.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月21日上午10:05:43 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月21日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Configuration
public class PageHelperConfig {
	
	@Value("${pagehelper.helperDialect}")
    private String helperDialect;
	
	@Value("${pagehelper.reasonable}")
	private String reasonable;
	
	@Value("${pagehelper.rowBoundsWithCount}")
	private String rowBoundsWithCount;
	
	@Value("${pagehelper.offsetAsPageNum}")
	private String offsetAsPageNum;


    @Bean
    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", helperDialect);
        //分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询
        properties.setProperty("reasonable", reasonable);
        //默认值为false，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为true时，使用 RowBounds 分页会进行 count 查询
        properties.setProperty("rowBoundsWithCount", rowBoundsWithCount);
        properties.setProperty("offsetAsPageNum", offsetAsPageNum);
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
