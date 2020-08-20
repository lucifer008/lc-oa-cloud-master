package com.lecar.config;

import com.lecar.filter.RequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public RequestFilter getServiceFilter(){
        return new RequestFilter();
    }
}
