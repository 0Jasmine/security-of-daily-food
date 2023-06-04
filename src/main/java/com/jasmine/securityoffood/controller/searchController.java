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
import com.jasmine.securityoffood.entities.Food;
import com.jasmine.securityoffood.mapper.FoodMapper;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class searchController {
    @Autowired
    private FoodMapper foodMapper;

    @GetMapping("/search")
    public ModelAndView get(HttpServletRequest httpServletRequest)
    {
        String food = httpServletRequest.getParameter("content");
        QueryWrapper<Food>queryWrapper =new QueryWrapper<Food>();
        queryWrapper.eq("foodName",food);
        Food target =  foodMapper.selectOne(queryWrapper);
        Map<String,String> map = new HashMap<>();
        map.put("name", food);
        map.put("energy", Double.toString(target.getEnergy()));
        map.put("protein", Double.toString(target.getProtein()));
        map.put("carbohydrate", Double.toString(target.getCarbohydrate()));
        map.put("fat", Double.toString(target.getFat()));
        map.put("type", target.getType());
        return new ModelAndView("search", map);
    }
    @PostMapping("/search")
    public ModelAndView search(HttpServletRequest httpServletRequest)
    {
        String food = httpServletRequest.getParameter("content");
        QueryWrapper<Food>queryWrapper =new QueryWrapper<Food>();
        queryWrapper.eq("foodName",food);
        List<Food> result =  foodMapper.selectList(queryWrapper);
        Map<String,String> map = new HashMap<>();
        System.out.println(result.isEmpty());
        if(result.isEmpty())
        {
            map.put("errorType", "食品信息缺失");
            map.put("errorReason", "数据库中尚缺少该食品信息，如果您有详细信息欢迎向我们反馈");
            map.put("returnaction", "/");
            return new ModelAndView("error", map);
        }
        Food target=result.get(0);
        map.put("name", food);
        map.put("energy", Double.toString(target.getEnergy()));
        map.put("protein", Double.toString(target.getProtein()));
        map.put("carbohydrate", Double.toString(target.getCarbohydrate()));
        map.put("fat", Double.toString(target.getFat()));
        map.put("type", target.getType());
        return new ModelAndView("search", map);
    }
}
