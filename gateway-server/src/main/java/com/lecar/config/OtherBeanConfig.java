package com.lecar.config;

import com.lecar.filter.LCGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
  *
  * @Description:     全局Bean注入配置类
  * @Author:         lucifer
  * @CreateDate:     2020/9/18 11:41
  * @UpdateUser:
  * @UpdateDate:
  * @UpdateRemark:
  * @Version:        1.0
 */
@Configuration
public class OtherBeanConfig {
    @Bean
    public GlobalFilter lcGlobalFilter() {
        return new LCGlobalFilter();
    }

}
