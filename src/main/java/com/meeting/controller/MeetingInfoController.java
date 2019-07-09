package com.meeting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.MeetingInfo;
import com.meeting.bean.MeetingInfoRetrun;
import com.meeting.bean.Msg;
import com.meeting.service.DepartmentService;
import com.meeting.service.MeetingInfoService;
import com.meeting.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
