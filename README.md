![image](https://img.shields.io/badge/Spring%20Cloud-%E2%98%85%E2%98%85%E2%98%85-green.svg)
![image](https://img.shields.io/badge/Netflix-%E2%98%85%E2%98%85%E2%98%85-red.svg)

spring-cloud 微服务组件demo
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

</tbody></table>

环境：JDK1.8
组件依赖版本：Camden.SR5

```
 <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <java.version>1.8</java.version>
  </properties>
```

demo 访问：

http://localhost:7073/lc.applayerservice/test/hello
