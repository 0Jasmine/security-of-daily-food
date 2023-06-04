/**
 * @author 谢志颖
 * @state 已完成
 */
package com.jasmine.securityoffood.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jasmine.securityoffood.entities.Manufacturer;
import com.jasmine.securityoffood.mapper.ManufacturerViewMapper;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class moreController {
    @Autowired
    private ManufacturerViewMapper manufacturerViewMapper;

    @PostMapping("more")
    /**
     * 将视图结果返回给用户；
     * @param area
     * @return
     */
    public ModelAndView get(HttpServletRequest httpServletRequest)
    {
        String food = httpServletRequest.getParameter("food");
        QueryWrapper<Manufacturer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("food", food);
        Manufacturer manufacturer = manufacturerViewMapper.selectList(queryWrapper).get(0);
        Map<String,String>map=new HashMap<>();
        if(manufacturer==null)
        {
            map.put("errorType", "缺少厂商信息");
            map.put("errorReason", "数据库暂无生产该食品的厂商信息");
            map.put("returnaction", "/");
            return new ModelAndView("error", map);
        }
        System.out.println(manufacturer);
        map.put("manufacturer", manufacturer.getFactu());
        map.put("area", manufacturer.getArea());
        map.put("name", manufacturer.getFood());
        map.put("sign", manufacturer.getPrincipal());
        if(manufacturer.getContact()==null)
            map.put("contact", "暂无");
        else map.put("contact", manufacturer.getContact());
        return new ModelAndView("more",map);
    }
}
