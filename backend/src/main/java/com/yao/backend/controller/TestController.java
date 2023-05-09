package com.yao.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/test/")
public class TestController {

    @RequestMapping("hh/")
    public Map<String , String> test(){
        Map<String, String> map = new HashMap<>();
        map.put("name" , "cat");
        map.put("rating" , "23333");
        return map;

    }
}
