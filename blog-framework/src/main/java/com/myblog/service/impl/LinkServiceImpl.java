package com.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.constans.SystemConstants;
import com.myblog.domain.ResponseResult;
import com.myblog.domain.entity.Link;
import com.myblog.domain.vo.LinkVo;
import com.myblog.mapper.LinkMapper;
import com.myblog.service.LinkService;
import com.myblog.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>implements LinkService {
	@Override
	public ResponseResult getAllLink() {
		//查询所有
		LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
		List<Link> links =list(queryWrapper);
		//转换
		List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);

		return ResponseResult.okResult(linkVos);
	}
}
