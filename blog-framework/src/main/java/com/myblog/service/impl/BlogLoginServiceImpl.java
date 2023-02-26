package com.myblog.service.impl;

import com.myblog.domain.ResponseResult;
import com.myblog.domain.entity.LoginUser;
import com.myblog.domain.entity.User;
import com.myblog.domain.vo.BlogUserLoginVo;
import com.myblog.domain.vo.UserInfoVo;
import com.myblog.service.BlogLoginService;
import com.myblog.utils.BeanCopyUtils;
import com.myblog.utils.JwtUtil;
import com.myblog.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisCache redisCache;
	@Override
	public ResponseResult login(User user) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		//判断是否认证通过
		if(Objects.isNull(authenticate)){
			throw new RuntimeException("用户名或密码错误");
		}
		 //获取id,生成token
		LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
		String userId = loginUser.getUser().getId().toString();
		String jwt = JwtUtil.createJWT(userId);
        //存入redis
		redisCache.setCacheObject("bloglogin:"+userId,loginUser);
        //token和userinfo封装
		//转换成vo
		UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
		BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);
		return ResponseResult.okResult(vo);
	}

}
