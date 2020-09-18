package com.lecar.controller;

import com.lecar.result.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FallbackController {
    @GetMapping("/fallbackA")
    public Response fallbackA() {
        Response response = new Response();
        response.setCode("100");
        response.setMessage("服务暂时不可用");
        log.info("-------------【熔断】服务暂时不可用---------------------------------");
        return response;
    }
}
