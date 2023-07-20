package com.yao.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yao.backend.mapper.UserMapper;
import com.yao.backend.pojo.User;
import com.yao.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, String> login(String username, String password) {
        Map<String ,String> map = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);

        User user = userMapper.selectOne(queryWrapper) ;
        if(user == null){
            return map;
        }

        map.put("userId" , user.getId() + "");
        map.put("error_message" , "success");

        return map;
    }
}
