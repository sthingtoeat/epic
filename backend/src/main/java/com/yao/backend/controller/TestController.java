package com.yao.backend.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yao.backend.mapper.UserMapper;
import com.yao.backend.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserService userService;
    @RequestMapping("hh/")
    public Map<String , String> test(){
        Map<String, String> map = new HashMap<>();
        map.put("name" , "cat");
        map.put("rating" , "23333");
        return map;

    }
    @RequestMapping("/login")
    public Map<String , String> login(HttpServletRequest httpServletRequest){
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        return userService.login(username,password);
    }
}
