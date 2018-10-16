===============citic-cloud-adapter(中信云适配器)======================
====当前版本：1.0.1
====该工程的基础框架为springboot+mybatis，日志采用log4j2，连接池采用阿里的druid，数据库支持mysql，依赖包通过maven管理。
====建议开发环境：(eclipse4.6.2)、(jdk1.8)、(tomcat9.0开发环境可不用)、(maven3.3.9)
====目录结构说明
-src/main/java				主代码目录
  -com.dhcc.citic.cloud
    -api					存放对方提供访问入口的接口类
    -cache					存放缓存配置(目前做了redis的配置)		
    -common					存放公共引用的模板类
    -config					存放配置相关类
    -handler				统一异常处理
    -mapper					数据库操作类
    -model					实体类
    -req					渠道侧api公共请求封装
    -service				业务类
    -utils					工具类
-src/main/resources			配置信息目录
  -generator
    -generatorConfig.xml	根据数据库表自动生成实体类、mapper接口、mapper.xml配置的配置类
  -mapping					mapper相关的mybatis配置
  -static					存放web工程静态资源(此工程应该用不到)
  -templates				存放web工程模前端页面或模板(此工程应该用不到)
-src/test/java				测试目录
