package com.lecar.config;

import com.lecar.filter.ServiceFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ServiceFilter getServiceFilter(){
        return new ServiceFilter();
    }
}
