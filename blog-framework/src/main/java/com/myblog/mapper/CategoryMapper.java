package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myblog.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
