package com.lecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  *
  * @Description:
 * SpringCloud Gateway 是 Spring Cloud 的一个全新项目，该项目是基于 Spring 5.0，Spring Boot 2.0 和 Project Reactor 等技术开发的网关，
 * 它旨在为微服务架构提供一种简单有效的统一的 API 路由管理方式。
 * SpringCloud Gateway 作为 Spring Cloud 生态系统中的网关，目标是替代 Zuul，在Spring Cloud 2.0以上版本中，
 * 没有对新版本的Zuul 2.0以上最新高性能版本进行集成，仍然还是使用的Zuul 2.0之前的非Reactor模式的老版本。而为了提升网关的性能，
 * SpringCloud Gateway是基于WebFlux框架实现的，而WebFlux框架底层则使用了高性能的Reactor模式通信框架Netty。
 * Spring Cloud Gateway 的目标，不仅提供统一的路由方式，并且基于 Filter 链的方式提供了网关基本的功能，例如：安全，监控/指标，和限流。
 *
 * SpringCloud官方，对SpringCloud Gateway 特征介绍如下：
 *
 * （1）基于 Spring Framework 5，Project Reactor 和 Spring Boot 2.0
 *
 * （2）集成 Hystrix 断路器
 *
 * （3）集成 Spring Cloud DiscoveryClient
 *
 * （4）Predicates 和 Filters 作用于特定路由，易于编写的 Predicates 和 Filters
 *
 * （5）具备一些网关的高级功能：动态路由、限流、路径重写
  * @Author:         lucifer
  * @CreateDate:     2020/9/17 10:48
  * @UpdateUser:
  * @UpdateDate:
  * @UpdateRemark:
  * @Version:        1.0
 */
@EnableDiscoveryClient
@EnableFeignClients
@RestController
@SpringBootApplication
public class GatewayServerApplication {
    @RequestMapping("/hystrixfallback")
    public String hystrixfallback() {
        return "This is a fallback";
    }
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
}
