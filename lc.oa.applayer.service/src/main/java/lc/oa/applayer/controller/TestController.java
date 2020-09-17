package lc.oa.applayer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm.sss");
    @Value("${server.port}")
    private String port;
    @RequestMapping("/hello")
    public String hello(){
        return simpleDateFormat.format(new Date())+"--->hello--->端口:"+port;
    }
}
