package com.jasmine.securityoffood.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasmine.securityoffood.entities.Note;

@Mapper
public interface NoteMapper extends BaseMapper<Note>{
}
