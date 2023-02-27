package com.myblog.controller;

import com.myblog.domain.ResponseResult;
import com.myblog.domain.entity.User;
import com.myblog.enums.AppHttpCodeEnum;
import com.myblog.service.BlogLoginService;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {

        return blogLoginService.login(user);
    }
}