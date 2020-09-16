package com.lecar.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 路由配置
 *
 * @author limengyang
 * create 2020-07-29
 **/
@Setter
@Getter
@Component
public class RouterConfig {

    @Value("${spring.cloud.nacos.server-addr}")
    private String serverAddr;

    @Value("${nacos.router.dataId:gateway-router.json}")
    private String dataId;

    @Value("${nacos.router.group:DEFAULT_GROUP}")
    private String group;

    @Value("${spring.cloud.nacos.config.namespace}")
    private String namespace;
}
