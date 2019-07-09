package com.meeting.controller;

import com.meeting.bean.MeetingRoomReturn;
import com.meeting.bean.Msg;
import com.meeting.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/meetingRoom")
public class MeetingRoomController {
    @Autowired
    MeetingRoomService meetingRoomService;
    @ResponseBody
    @RequestMapping(value = "/findAllMeetingRoom",method = RequestMethod.GET)
    public Msg MeetingRoom(){
        List<MeetingRoomReturn> list  = meetingRoomService.selectMeetingRoomReturn();
        return Msg.success().add("meetingroom", list);
    }
}
