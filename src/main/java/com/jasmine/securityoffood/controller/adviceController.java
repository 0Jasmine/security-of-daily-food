/**
 * @author 谢志颖
 * @state 已完成
 */
package com.jasmine.securityoffood.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jasmine.securityoffood.entities.Note;
import com.jasmine.securityoffood.entities.User;
import com.jasmine.securityoffood.mapper.NoteMapper;
import com.jasmine.securityoffood.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class adviceController {
    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("contact")
    public String get()
    {
        return "contact";
    }
    @PostMapping("contact")
    public ModelAndView post(HttpServletRequest httpServletRequest)
    {
        Note note=new Note();
        note.setNotes_user(httpServletRequest.getParameter("name"));
        note.setUser_email(httpServletRequest.getParameter("email"));
        note.setNotes_type(httpServletRequest.getParameter("type"));
        note.setMessage(httpServletRequest.getParameter("message"));
        note.setNotes_date(new Timestamp(System.currentTimeMillis()));
        Map<String,String>map=new HashMap<>();
        try {
            noteMapper.insert(note);
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("name", httpServletRequest.getParameter("name"));
            queryWrapper.eq("email", httpServletRequest.getParameter("email"));
            Long res = userMapper.selectCount(queryWrapper);
            if(res==0)
            {
                User user = new User();
                user.setName(httpServletRequest.getParameter("name"));
                user.setEmail(httpServletRequest.getParameter("email"));
                user.setGender(httpServletRequest.getParameter(null));
                userMapper.insert(user);
            }
            String script="<script>alert(\"反馈成功，谢谢你的反馈！\")</script>";
            map.put("jscode", script);
            return new ModelAndView("index", map);
        } catch (Exception e) {
            System.out.println(note);
            map.put("errorType", "反馈失败");
            map.put("errorReason", "请正确分类您的反馈");
            map.put("returnaction", "contact");
            return new ModelAndView("error", map);
        }
    }
}
