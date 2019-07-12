package com.meeting.controller;

import com.meeting.bean.MeetingSignin;
import com.meeting.bean.Msg;
import com.meeting.bean.UserInfo;
import com.meeting.bean.UserSignInInfo;
import com.meeting.service.MeetingSigninService;
import com.meeting.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/meetingSignin")
public class MeetingSigninController {
    @Autowired
    MeetingSigninService meetingSigninService;
    @Autowired
    UserInfoService userInfoService;
    @ResponseBody
    @RequestMapping(value = "/findMeetingSignInfo")
    public Msg findMeetingSignInfo(int meetingId) throws UnsupportedEncodingException {
        List<UserSignInInfo> list=new ArrayList<UserSignInInfo>();
        List<MeetingSignin>meetingSignin = meetingSigninService.selectMeetingSigninByMeetingId(meetingId);
        int id=0;
        for (MeetingSignin lis:meetingSignin) {
            UserSignInInfo userSignInInfo=new UserSignInInfo();
            userSignInInfo.setMeetingid(meetingId);
            userSignInInfo.setId(id);
            int userId=lis.getUserId();
            UserInfo userInfo = userInfoService.selectUser(userId);
            userSignInInfo.setUsername(userInfo.getUsername());
            userSignInInfo.setName(userInfo.getName());
            userSignInInfo.setUserId(userId);
            String status="";
            if (lis.getLeaveFlag()==true)
                status=new String("请假".getBytes(),"gbk");
            else if (lis.getLateFlag()==true)
                status=new String("迟到".getBytes(),"gbk");
            else if (lis.getSigninFlag()==false)
                status=new String("已到".getBytes(),"gbk");
            else status=new String("未到".getBytes(),"gbk");
            userSignInInfo.setStatus(status);
            id++;
            list.add(userSignInInfo);
        }
        return Msg.success().add("signinInfo",list);
    }
}
