package lc.oa.applayer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AppLayerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppLayerApplication.class).web(true).run(args);
    }

}
