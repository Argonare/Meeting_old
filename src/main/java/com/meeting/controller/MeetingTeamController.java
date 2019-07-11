package com.meeting.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.*;
import com.meeting.bean.MeetingTeam;
import com.meeting.bean.Msg;
import com.meeting.bean.UserInfoReturn;
import com.meeting.service.MeetingTeamService;
import com.meeting.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/meetingTeam")
public class MeetingTeamController {

    @Autowired
    MeetingTeamService meetingTeamService;

    @Autowired
    UserInfoService userInfoService;
    /**
     * 分页数据
     * @param pn
     * @return
     */
    @ResponseBody
    @RequestMapping("/findTeamPage")
    public Msg findTeamPage(@RequestParam(value="page",defaultValue="1")Integer pn){
        PageHelper.startPage(pn,15);
        List<MeetingTeamReturn> list  = meetingTeamService.selectTeamReturn();
        PageInfo page = new PageInfo(list,1);
        return Msg.success().add("depts", page);
    }

    /**
     * 删除
     * @param meetingTeam
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteMeetingTeam")
    public Msg deleteMeetingTeam(MeetingTeam meetingTeam){
        MeetingTeamExample meetingTeamExample = new MeetingTeamExample();
        MeetingTeamExample.Criteria criteria = meetingTeamExample.createCriteria();
        criteria.andIdEqualTo(meetingTeam.getId());
        meetingTeam.setDeleteFlag(true);
        if (meetingTeamService.updateMeetingTeam(meetingTeam,meetingTeamExample))
            return Msg.success().add("msg", "删除成功");
        else
            return Msg.fail().add("msg", "删除失败");
    }


    @ResponseBody
    @RequestMapping("/insertMeetingTeam")
    public Msg insertMeetingTeam(HttpSession session, MeetingTeam meetingTeam, HttpServletRequest request){
        String username = (String) session.getAttribute("username");
        meetingTeam.setUsername(username);
        meetingTeam.setDeleteFlag(false);
        if (meetingTeamService.insertMeetingTeam(meetingTeam))
            return Msg.success().add("msg","添加成功" );
        else
            return Msg.fail().add("msg", "添加失败");
    }

    @ResponseBody
    @RequestMapping("/checkAddMeetingTeam")
    public Msg checkAddMeetingTeam(@RequestParam("name")String name){
        boolean MeetingTeamname = meetingTeamService.checkAddMeetingTeam(name);
        if (MeetingTeamname)
            return Msg.success();
        else
            return Msg.fail();
    }

    @ResponseBody
    @RequestMapping("/updateMeetingTeam")
    public Msg updateMeetingTeam(HttpSession session,MeetingTeam meetingTeam){
        System.out.println(meetingTeam.toString());
        MeetingTeamExample meetingTeamExample = new MeetingTeamExample();
        MeetingTeamExample.Criteria criteria = meetingTeamExample.createCriteria();
        criteria.andIdEqualTo(meetingTeam.getId());
        boolean b = meetingTeamService.updateMeetingTeam(meetingTeam, meetingTeamExample);
        if (b)
            return Msg.success();
        else
            return Msg.fail();
    }

    @ResponseBody
    @RequestMapping("/checkUpdateMeetingTeamName")
    public Msg checkUpdateMeetingTeamName(@RequestParam("name")String name,@RequestParam("id")Integer id){
        boolean MeetingTeamName =  meetingTeamService.checkUpdateMeetingTeamName(name,id);
        if (MeetingTeamName)
            return Msg.success();
        else
            return Msg.fail();
    }


    @ResponseBody
    @RequestMapping("/getMeetingTeamRight")
    public Msg getMeetingTeamRight(HttpServletRequest request){
        Integer teamId = Integer.valueOf(request.getParameter("teamId"));
        String member_ids = meetingTeamService.selectMeetingTeamids(teamId);
//        System.out.println(member_ids);
        List<UserInfoReturn> list = userInfoService.findUserInfoReturnByUsernamesInMeetingTeam(member_ids.split(","));
//        System.out.println(list.toString());

        return Msg.success().add("getMeetingTeamRight", list);
    }



}
