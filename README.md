![image](https://img.shields.io/badge/Spring%20Cloud-%E2%98%85%E2%98%85%E2%98%85-green.svg)
![image](https://img.shields.io/badge/Netflix-%E2%98%85%E2%98%85%E2%98%85-red.svg)

spring-cloud+springboot+zuul+eureka-server 微服务组件demo
===

![image](http://img.blog.csdn.net/20171018201759315?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcmlja2l5ZWF0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

<table>
<tbody><tr>
<td>工程名</td>  <td>描述</td>  <td>端口</td>
</tr>
<tr>
<td>eureka-server</td>  <td>服务发现与注册中心</td>  <td>7070</td>
</tr>

<tr>
<td>zuul</td>  <td>动态路由器</td>  <td>7073</td>
</tr>
<tr>
<td>lc.oa.applayer.service</td>  <td>模拟业务服务</td>  <td>6008</td>
</tr>
<tr>
<td>gateway-server</td>  <td>gateway网关，作用同zuul</td>  <td>60011</td>
</tr>
</tbody></table>
nacos配置 <br>
<table style="border-collapse: collapse" border="1">
<tbody><tr>
<td>文件名</td>  <td>配置内容</td> 
</tr>
<tr>
<td>zuul-service-dev.properties</td>  
<td>spring.application.name=zuul
    server.port=7073
    
    # routes to serviceId
    
    zuul.routes.api-b.path=/lc.applayerservice/**
    zuul.routes.api-b.serviceId=lc.applayerservice
    
    # routes to url
    zuul.routes.api-a-url.path=/api-a-url/**
    zuul.routes.api-a-url.url=http://localhost:7074/
</td>  
</tr>

<tr>
<td>	
lc.applayerservice-dev.properties</td>  <td>spring.application.name=lc.applayerservice
                                            server.port=6008
</td>  
</tr>
<tr>
<td>eureka.properties</td>  
<td>#表示是否将自己注册到Eureka Server
                                eureka.client.register-with-eureka=true
                                
                                #表示是否从Eureka Server获取注册信息
                                #eureka.client.fetch-registry=true
                                eureka.client.registryFetchIntervalSeconds=10
                                eureka.client.registerWithEureka=true
                                eureka.client.fetchRegistry=true
                                eureka.instance.preferIpAddress=true
                                #设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址。
                                # 默认是http://localhost:8761/eureka ；多个地址可使用 , 分隔
                                eureka.client.serviceUrl.defaultZone=http://localhost:7070/eureka/
                                #spring.cloud.nacos.config.server-addr=182.92.221.157:9010
</td>
</tr>
<tr>
<td>gateway-service-dev.yml</td>  <td>spring:
                                        application:
                                          name: gateway-service
                                      server:
                                        port: 60011
                                      ribbon:
                                        eager-load:
                                          enabled: true
                                        #多个服务以逗号分隔
                                        clients: lc.applayerservice
                                        </td>
</tr>
</tbody></table>
环境：JDK1.8，spring boot 2.0版本以上

```
 <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <java.version>1.8</java.version>
  </properties>
```
spring boot 2.0版本

zuul demo 访问：

http://localhost:7073/lc.applayerservice/test/hello

gateway demo 访问
1>.路径重写
http://localhost:60011/lc.applayerservice/test/hello
2>.eureka负载
http://localhost:60011/lc.applayerservice2/test/hello