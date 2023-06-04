package com.jasmine.securityoffood.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasmine.securityoffood.entities.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{
    
    @Select(value = "call editEmail(#{u_name,mode=IN},#{old_email,mode=IN},#{new_email,mode=IN})")
    @Options(statementType = StatementType.CALLABLE)
    void updateEmail(String u_name,String old_email,String new_email);
}
