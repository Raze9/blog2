package com.myblog.service;

import com.myblog.domain.ResponseResult;
import com.myblog.domain.entity.User;

public interface BlogLoginService {
	ResponseResult login(User user);
}
