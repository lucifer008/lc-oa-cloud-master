package lc.oa.applayer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("/hello")
    public String hello(){
        return simpleDateFormat.format(new Date())+"--->hello";
    }
}
