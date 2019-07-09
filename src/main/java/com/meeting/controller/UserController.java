package com.meeting.controller;

import com.meeting.bean.Msg;
import com.meeting.bean.UserInfo;
import com.meeting.bean.UserInfoReturn;
import com.meeting.service.UserInfoService;
import com.meeting.utils.getGuestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    /**
     * 退出
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/log_out.do")
    public Msg log_out(HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("user_id");
        return Msg.success().add("msg", "退出成功");
    }

    /***
     * 获得微信登录用户的信息
     * ***/
    @ResponseBody
    @RequestMapping(value = "/getGuestUsername.do")
    public Msg getGuestUsername(HttpSession session){
        getGuestUser getGuestUser=new getGuestUser();
        String username=getGuestUser.getname();
        List<UserInfoReturn> list = userInfoService.selectUserinfoByUsernameReturn(username);
        return Msg.success().add("userInfo", list.get(0));
    }
    /**
     * 登录
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUsername.do")
    public Msg getUsername(HttpSession session){
        String username= (String) session.getAttribute("username");
        List<UserInfoReturn> list = userInfoService.selectUserinfoByUsernameReturn(username);
        return Msg.success().add("userInfo", list.get(0));
    }

}
