package com.yao.backend.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    Map<String ,String> login(String username , String password);
}
