package com.myblog.filter;

import com.alibaba.fastjson.JSON;
import com.myblog.domain.ResponseResult;
import com.myblog.domain.entity.LoginUser;
import com.myblog.enums.AppHttpCodeEnum;
import com.myblog.utils.JwtUtil;
import com.myblog.utils.RedisCache;
import com.myblog.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Autowired
	private RedisCache redisCache;
	@Override
	protected void doFilterInternal(HttpServletRequest Request, HttpServletResponse Response, FilterChain filterChain) throws ServletException, IOException {
		String token = Request.getHeader("token");
		if (!StringUtils.hasText(token)) {
		   filterChain.doFilter(Request,Response);
		   return;
		}
		Claims claims = null;
		try {
           claims = JwtUtil.parseJWT(token);
		}catch (Exception e){
			e.printStackTrace();
			//token超时 token非法
			ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
			WebUtils.renderString(Response, JSON.toJSONString(result));
			return;
		}
		String userId = claims.getSubject();
		LoginUser loginUser = redisCache.getCacheObject("bloglogin" + userId);

		UsernamePasswordAuthenticationToken authenticationToken
				= new UsernamePasswordAuthenticationToken(loginUser,null,null);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(Request,Response);
	}
}
