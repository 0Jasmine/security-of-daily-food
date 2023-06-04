package com.jasmine.securityoffood.mapper;

import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Result;
// import org.apache.ibatis.annotations.Results;
// import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasmine.securityoffood.entities.Food;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
//     @Select("select * from food where foodName=#{foodName}")
//     @Results({
//     @Result(column = "foodID",property = "foodID"),
//     @Result(column = "foodName",property = "foodName"),
//     @Result(column = "energy",property = "energy"),
//     @Result(column = "protein",property = "protein"),
//     @Result(column = "carbohydrate",property = "carbohydrate"),
//     @Result(column = "name",property = "fields",many = @Many(select="com.jasmine.securityoffood.mapper.FieldsMapper")),
// })
}
