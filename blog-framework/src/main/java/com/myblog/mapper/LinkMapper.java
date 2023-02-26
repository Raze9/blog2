package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LinkMapper extends BaseMapper<Link> {
}
