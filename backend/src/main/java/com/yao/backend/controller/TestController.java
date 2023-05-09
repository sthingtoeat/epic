package com.yao.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/test/")
public class TestController {

    @RequestMapping("hh/")
    public String test(){
        System.out.println("1");
        return "test.html";
    }
}
