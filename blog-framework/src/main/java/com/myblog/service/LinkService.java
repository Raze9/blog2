package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.domain.ResponseResult;
import com.myblog.domain.entity.Link;

public interface LinkService extends IService<Link> {
	ResponseResult getAllLink();
}
