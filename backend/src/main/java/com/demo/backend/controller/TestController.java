package com.demo.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/")
public class TestController {

    @RequestMapping("start/")
    public String test(){
        String x = "123";
        return x;
    }
}
