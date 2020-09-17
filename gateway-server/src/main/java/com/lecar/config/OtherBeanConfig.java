package com.lecar.config;

import com.lecar.filter.LCGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtherBeanConfig {
    @Bean
    public GlobalFilter lcGlobalFilter() {
        return new LCGlobalFilter();
    }

}
