package com.meeting.controller;

import com.meeting.bean.Msg;
import com.meeting.bean.UserInfo;
import com.meeting.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userInfo")
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    public Msg checkUser(UserInfo userInfo,HttpSession session){
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        if(userInfoService.checkUser(username,password)){
            session.setAttribute("username",username);
            return Msg.success();
        }else{
            return Msg.fail().add("msg","工号或密码错误");
        }
    }
}
