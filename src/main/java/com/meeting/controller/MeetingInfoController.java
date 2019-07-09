package com.meeting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.*;
import com.meeting.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/meetingInfo")
public class MeetingInfoController {
    @Autowired
    MeetingInfoService meetingInfoService;
    @Autowired
    MeetingRoomService meetingRoomService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    MeetingSigninService meetingSigninService;

//    @ResponseBody
//    @RequestMapping(value = "/insertMeetingInfo")
//    public Msg insertMeetingInfo(HttpServletRequest request, HttpSession session, MeetingInfo meetingInfo){
//        System.out.println("insertMeetingInfo");
//        String username = (String) session.getAttribute("username");
//        Integer uid = userInfoService.getUidByUsername(username);
//        meetingInfo.setDeleteFlag(false);
//        meetingInfo.setType(1);
//
//        meetingInfoService.insertMeetingInfo(meetingInfo);
//        long a = meetingInfoService.selectMeetingInfo(meetingInfo);
//
//        String[] ids = request.getParameterValues("ids[]");
//        MeetingSignin meetingSignin = new MeetingSignin();
//        meetingSignin.setMeetingId((int)a);
//        meetingSignin.setSigninFlag(false);
//        meetingSignin.setLeaveFlag(false);
//        meetingSignin.setLateFlag(false);
//        meetingSignin.setDeleteFlag(false);
//        for(int i =0;i<ids.length;i++){
//            meetingSignin.setUserId(Integer.parseInt(ids[i]));
//            meetingSigninService.insertMeetingSignin(meetingSignin);
//        }
//        return  Msg.success();
//    }

    @ResponseBody
    @RequestMapping(value = "/findAllMeetingInfo",method = RequestMethod.GET)
    public Msg MeetingInfo(@RequestParam(value="page",defaultValue="1")Integer pn){
        PageHelper.startPage(pn,15);
        List<MeetingInfoRetrun>list=new ArrayList<>();
        List<MeetingInfo> meetingInfo  = meetingInfoService.findAllMeetingInfo();
        for (MeetingInfo lis:meetingInfo){
            String place="";
            MeetingInfoRetrun meetingInfoRetrun=new MeetingInfoRetrun();
            for (String id:lis.getDepartIds().split(","))
                place+=departmentService.selectByPrimaryKey(Integer.parseInt(id)).getName()+",";
            String address=meetingRoomService.selectByPrimaryKey(lis.getRoomId()).getAddress();
            meetingInfoRetrun.setAddress(address);
            meetingInfoRetrun.setEndTime(lis.getEndTime());
            meetingInfoRetrun.setStartTime(lis.getStartTime());
            meetingInfoRetrun.setDeptName(place.substring(0,place.length()-1));
            meetingInfoRetrun.setName(lis.getName());
            list.add(meetingInfoRetrun);
        }
        PageInfo page = new PageInfo(list,1);
        return Msg.success().add("meetinginfo",page);
    }
}
