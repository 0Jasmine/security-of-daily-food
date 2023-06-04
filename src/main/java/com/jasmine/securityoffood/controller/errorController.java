/***
 * @author 谢志颖
 * @state 已完成
 */
package com.jasmine.securityoffood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class errorController {
    @GetMapping("error")
    public String get()
    {
        return "error";
    }

    @PostMapping("error")
    /***
     * 其实这个方法没用到，，
     * @return
     */
    public String post()
    {
        return "index";
    }
}
