/**
 * @author 谢志颖
 * @state 已完成
 */
package com.jasmine.securityoffood.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jasmine.securityoffood.entities.Note;
import com.jasmine.securityoffood.entities.User;
import com.jasmine.securityoffood.mapper.NoteMapper;
import com.jasmine.securityoffood.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class userController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NoteMapper noteMapper;

    @GetMapping("user")
    public String get()
    {
        return "user";
    }

    @PostMapping("user")
    @Transactional(noRollbackFor = {SQLException.class},rollbackFor = {RuntimeException.class})
    public ModelAndView post(HttpServletRequest httpServletRequest) throws Exception
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name", httpServletRequest.getParameter("name"));
        queryWrapper.eq("email", httpServletRequest.getParameter("oldemail"));
        Long res = userMapper.selectCount(queryWrapper);
        Map<String,String>map=new HashMap<>();
        if(res==0){
            map.put("errorType", "用户信息错误");
            map.put("errorReason", "该用户不存在");
            map.put("returnaction", "user");
            return new ModelAndView("error", map);
        }else{
            if(httpServletRequest.getParameter("modify")!=null){
                try {
                    userMapper.updateEmail(httpServletRequest.getParameter("name"), httpServletRequest.getParameter("oldemail"), httpServletRequest.getParameter("newemail"));
                } catch (Exception e) {
                    map.put("errorType", "新邮箱用户已注册");
                    map.put("errorReason", "请选择注销某一用户");
                    map.put("returnaction", "user");
                    return new ModelAndView("error", map);
                }
                map.put("jscode", "<script>alert(\"修改成功\")</script>");
                return new ModelAndView("index", map);
            }else{
                QueryWrapper<Note>queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("notes_user", httpServletRequest.getParameter("name"));
                queryWrapper2.eq("user_email", httpServletRequest.getParameter("oldemail"));
                Long notes = noteMapper.selectCount(queryWrapper2);
                if(notes==0)
                {
                    userMapper.delete(queryWrapper);
                    map.put("jscode", "<script>alert(\"注销成功\")</script>");
                    return new ModelAndView("index", map);
                }else{
                        map.put("errorType", "用户存在未处理留言");
                        map.put("errorReason", "用户留言尚未被管理员处理，要注销请联系网站管理人员。");
                        map.put("returnaction", "/");
                        return new ModelAndView("error", map);
                }
            }
        }
    }
}
