/**
 * @author 谢志颖
 * @state 已完成
 */
package com.jasmine.securityoffood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
}
