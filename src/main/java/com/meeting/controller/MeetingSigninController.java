package com.meeting.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meeting.bean.*;
import com.meeting.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    MeetingInfoService meetingInfoService;
    @Autowired
    MeetingRoomService meetingRoomService;
    @Autowired
    DepartmentService departmentService;
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
    @ResponseBody
    @RequestMapping(value = "/updateMeetingSigninByMySelf",method = RequestMethod.POST)
    public Msg updateMeetingSigninByMySelf(MeetingSignin meetingSignin){
        MeetingSigninExample example = new MeetingSigninExample();
        MeetingSigninExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(meetingSignin.getUserId());
        criteria.andMeetingIdEqualTo(meetingSignin.getMeetingId());
        MeetingInfo meetingSigninCheck = meetingInfoService.selectMeetingInfoById(meetingSignin.getMeetingId());

        Long checkTime=meetingSignin.getSigninTime()-meetingSigninCheck.getStartTime();
        if (checkTime<meetingSigninCheck.getLateTime()*6000+150000){
            if (meetingSigninService.updateMeetingSignin(meetingSignin, example))
                return Msg.success().add("msg", "success");
            else
                return Msg.fail().add("msg", "服务器异常");
        }else if(meetingSigninCheck.getEndTime()-meetingSignin.getSigninTime()>0){
            meetingSignin.setLateFlag(true);
            if (meetingSigninService.updateMeetingSignin(meetingSignin, example))
                return Msg.success().add("msg", "success");
            else
                return Msg.fail().add("msg", "服务器异常");
        }else{
            return Msg.fail().add("msg", "非法请求");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/updateSignByUserAndMeeting")
    public Msg updateSignByUserAndMeeting(HttpServletRequest request) {
        String strjson=request.getParameter("ids");
        JSONArray json= JSON.parseArray(strjson);
        int flag=0;
        for(int i=0;i<json.size();i++){
            MeetingSigninExample example = new MeetingSigninExample();
            MeetingSigninExample.Criteria criteria = example.createCriteria();
            JSONObject jsonObject=json.getJSONObject(i);
            int userId=Integer.parseInt(jsonObject.getString("userId"));
            int meetingid=Integer.parseInt(jsonObject.getString("meetingid"));
            String sign_status=jsonObject.getString("status").toString();
            MeetingSignin meetingSignin=new MeetingSignin();
            if (sign_status.equals("signin")){
                meetingSignin.setSigninFlag(true);
                meetingSignin.setLateFlag(false);
                meetingSignin.setLeaveFlag(false);
            }else if(sign_status.equals("notsignin")){
                meetingSignin.setSigninFlag(false);
                meetingSignin.setLateFlag(false);
                meetingSignin.setLeaveFlag(false);
            }else if(sign_status.equals("late")){
                meetingSignin.setSigninFlag(true);
                meetingSignin.setLateFlag(true);
                meetingSignin.setLeaveFlag(false);
            }
            else if(sign_status.equals("leave")){
                meetingSignin.setSigninFlag(false);
                meetingSignin.setLateFlag(false);
                meetingSignin.setLeaveFlag(true);
            }
            meetingSignin.setMeetingId(meetingid);
            meetingSignin.setUserId(userId);
            criteria.andUserIdEqualTo(meetingSignin.getUserId());
            criteria.andMeetingIdEqualTo(meetingSignin.getMeetingId());
            if (!meetingSigninService.updateMeetingSignin(meetingSignin, example))
                flag=1;
        }
        return flag==0?Msg.success():Msg.fail();
    }
    @ResponseBody
    @RequestMapping(value = "/selectMeetingByUsername.do")
    public Msg selectMeetingByUsername(Integer userId){
        List<UserMeetingInfo> meetInfo=new ArrayList<UserMeetingInfo>();
        List<MeetingSignin>meetingSignin = meetingSigninService.selectMeetingSigninByUsername(userId);
        for (MeetingSignin lis:meetingSignin){
            UserMeetingInfo userMeetingInfo=new UserMeetingInfo();
            MeetingInfo meetingInfo=meetingInfoService.selectMeetingInfoById(lis.getMeetingId());
            MeetingRoom meetingRoom=meetingRoomService.selectMeetingRoom(meetingInfo.getRoomId());
            String deptName="";
            for (String id:meetingInfo.getDepartIds().split(",")) {
                deptName+=departmentService.selectDepartment(Integer.parseInt(id))+",";
            }
            userMeetingInfo.setRoom(meetingRoom.getAddress());
            userMeetingInfo.setStartTime(meetingInfo.getStartTime());
            userMeetingInfo.setMeetingName(meetingInfo.getName());
            userMeetingInfo.setDepartment(deptName.substring(0,deptName.length()-1));
            meetInfo.add(userMeetingInfo);
        }
        return Msg.success().add("meetInfo",meetInfo);
    }
}
