package lc.oa.applayer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm.sss");
    @Value("${server.port}")
    private String port;
    @RequestMapping("/hello")
    public String hello(){
        String result=simpleDateFormat.format(new Date())+"--->hello--->端口:"+port;
        log.info("---------------------------【请求】{}-----------------------------------------------",result);
        return result;
    }
}
