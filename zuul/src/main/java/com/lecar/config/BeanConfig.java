package com.lecar.config;

import com.lecar.filter.ErrorFilter;
import com.lecar.filter.PostFilter;
import com.lecar.filter.PreFilter;
import com.lecar.filter.RouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public PreFilter getPreFilter(){
        return new PreFilter();
    }
    @Bean
    public RouteFilter getRouteFilter(){
        return new RouteFilter();
    }
    @Bean
    public PostFilter getPostFilter(){
        return new PostFilter();
    }
    @Bean
    public ErrorFilter getErrFilter(){
        return new ErrorFilter();
    }
}
