package com.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.domain.ResponseResult;
import com.myblog.domain.entity.Category;
import com.myblog.domain.vo.CategoryVo;
import com.myblog.domain.vo.PageVo;

import java.util.List;

public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();


}

