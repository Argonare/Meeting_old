package com.meeting.controller;

import com.meeting.bean.MeetingTeam;
import com.meeting.bean.Msg;
import com.meeting.bean.UserInfoReturn;
import com.meeting.service.MeetingTeamService;
import com.meeting.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/meetingTeam")
public class MeetingTeamController {
    @Autowired
    MeetingTeamService meetingTeamService;

    @Autowired
    UserInfoService userInfoService;
    @ResponseBody
    @RequestMapping("/getUserInfosByTeamId")
    public Msg getUserInfosByTeamId(HttpServletRequest request){
        String teamId = request.getParameter("teamId");
        String str_ids = meetingTeamService.getIdsById(teamId);
        if(str_ids.length() ==0){
            return Msg.fail().add("msg","小组内无成员");
        }
        String[] usernames = str_ids.split(",");
        List<UserInfoReturn> userInfoReturns = userInfoService.findUserInfoReturnByUsernamesInMeetingTeam(usernames);
        return Msg.success().add("userInfoReturns",userInfoReturns);
    }

    @ResponseBody
    @RequestMapping("/getMyMeetingTeamsNameByUsername")
    public Msg getMyMeetingTeamsByUsername(HttpSession session){
        String username = (String) session.getAttribute("username");//获取当前登录用户的工号
        List<MeetingTeam> meetingTeamsNameList = meetingTeamService.getMyMeetingTeamsNameByUsername(username);
        return Msg.success().add("meetingTeamsNameList",meetingTeamsNameList);
    }
}
