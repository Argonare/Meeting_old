package com.meeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面
 */
@Controller
@RequestMapping("/jumpPage")
public class JumpPageController {

    @RequestMapping(value = "/insertMeetingRoomIframe")
    public String insertMeetingRoomIframe(){ return  "insertMeetingRoomIframe"; }

    @RequestMapping(value = "/updateMeetingRoomIframe")
    public String updateMeetingRoomIframe(){ return  "updateMeetingRoomIframe"; }

    @RequestMapping(value = "/updateMeetinginfoIframe")
    public String updateMeetinginfoIframe(){
        return "updateMeetinginfoIframe";
    }

    @RequestMapping(value = "/insertMeetinginfoIframe")
    public String insertMeetinginfoIframe(){return "insertMeetinginfoIframe"; }

    @RequestMapping(value = "/updateUserinfoIframe")
    public String updateUserinfoIframe(){
        return "updateUserinfoIframe";
    }

    @RequestMapping(value = "/insertUserinfoIframe")
    public String insertUserinfoIframe(){
        return "insertUserinfoIframe";
    }

    @RequestMapping(value ="/newhoutai")
    public String newhoutai(){
        return "newhoutai";
    }

    @RequestMapping(value ="/updateDepartmentIframe")
    public String updateDepartmentIframe(){
        return "updateDepartmentIframe";
    }

    @RequestMapping(value ="/insertDepartmentIfame")
    public String insertDepartmentIfame(){
        return "insertDepartmentIfame";
    }

    @RequestMapping(value = "/create_Qrcode")
    public String create_Qrcode(){
        return "qrcode";
    }

    @RequestMapping(value = "/qrcode_success")
    public String qrcode_success(){
        return "qrcode_success";
    }

    @RequestMapping(value = "/signinInfoIframe")
    public String signinInfoIframe(){
        return "signinInfoIframe";
    }

    @RequestMapping(value = "/insertMeetingTeamIframe")
    public String insertMeetingTeamIframe(){
        return "insertMeetingTeamIframe";
    }

    @RequestMapping(value = "/updateMeetingTeamIframe")
    public String updateMeetingTeamIframe(){
        return "updateMeetingTeamIframe";
    }

    @RequestMapping(value = "/ECharts")
    public String ECharts(){
        return "ECharts";
    }

}
