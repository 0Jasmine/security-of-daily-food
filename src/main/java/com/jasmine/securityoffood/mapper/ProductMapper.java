package com.jasmine.securityoffood.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasmine.securityoffood.entities.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    
}
