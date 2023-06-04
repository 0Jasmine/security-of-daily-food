/**
 * @author 谢志颖
 * @state 已完成
 */
package com.jasmine.securityoffood.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jasmine.securityoffood.entities.User;
import com.jasmine.securityoffood.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class loginController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String index(HttpServletRequest httpServletRequest)
    {        
        return "login";
    }
    @PostMapping("/login")
    public ModelAndView login(HttpServletRequest httpServletRequest)
    {        
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name", httpServletRequest.getParameter("name"));
        queryWrapper.eq("email", httpServletRequest.getParameter("email"));
        Long result = userMapper.selectCount(queryWrapper);
        Map<String,String>map=new HashMap<>();
        if(httpServletRequest.getParameter("login")!=null)
        {
            if(result>=1){
                ModelAndView modelAndView = new ModelAndView("index", map);
                return modelAndView;
            }else{
                map.put("errorType", "未注册用户");
                map.put("errorReason", "若已注册请检查登录信息无误，未注册请先注册用户再登录。");
                map.put("returnaction", "login");
                ModelAndView modelAndView = new ModelAndView("error", map);
                return modelAndView;
            }
        }else if(httpServletRequest.getParameter("sign")!=null){
            if(result>=1){
            map.put("errorType", "已注册用户");
            map.put("errorReason", "该用户已注册，请直接登录");
            map.put("returnaction", "login");
            ModelAndView modelAndView = new ModelAndView("error", map);
            return modelAndView;
            }
            else{
            ModelAndView modelAndView = new ModelAndView("index", map);
            User user = new User();
            user.setName(httpServletRequest.getParameter("name"));
            user.setEmail(httpServletRequest.getParameter("email"));
            user.setGender(httpServletRequest.getParameter("gender"));
            userMapper.insert(user);
            return modelAndView;
            }
        }
        map.put("errorType", "错误提交");
        map.put("errorReason", "");
        map.put("returnaction", "login");
        ModelAndView modelAndView = new ModelAndView("error", map);
        return modelAndView;
    }
}
