package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
