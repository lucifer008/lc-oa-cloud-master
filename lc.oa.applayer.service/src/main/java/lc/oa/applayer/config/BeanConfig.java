package lc.oa.applayer.config;

import lc.oa.applayer.filter.ServiceFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ServiceFilter getServiceFilter(){
        return new ServiceFilter();
    }
}
