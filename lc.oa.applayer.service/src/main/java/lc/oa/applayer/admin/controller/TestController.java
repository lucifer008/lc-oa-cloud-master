package lc.oa.applayer.admin.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController("admintest")
@RequestMapping("/admin/test")
public class TestController {
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm.sss");
    @RequestMapping("/hello")
    public String hello(){
        return simpleDateFormat.format(new Date())+"--->hello";
    }
}
