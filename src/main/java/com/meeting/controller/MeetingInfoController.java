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

    @ResponseBody
    @RequestMapping("/wx_newMeeting")
    public Msg wx_newmeeting(HttpServletRequest request){
        String username = request.getParameter("username");
        String type = request.getParameter("type");

        List<wxMeetingInfo> wxMeetingInfos = meetingInfoService.getAfterTimeMeetingInfoByUser(username,type);
        System.out.println(wxMeetingInfos.toString());
        return Msg.success().add("wxMeetingInfo",wxMeetingInfos).add("count",wxMeetingInfos.size());
    }

    @ResponseBody
    @RequestMapping(value = "/findAllMeetingInfo",method = RequestMethod.GET)
    public Msg MeetingInfo(@RequestParam(value="page",defaultValue="1")Integer pn){
        PageHelper.startPage(pn,15);
        List<MeetingInfo> meetingInfo  = meetingInfoService.findAllMeetingInfo();
        PageInfo page = new PageInfo(meetingInfo,1);
        List<MeetingInfoRetrun>list=new ArrayList<MeetingInfoRetrun>();
        System.out.println(meetingInfo.size());
        for (MeetingInfo lis:meetingInfo){
            String place="";
            MeetingInfoRetrun meetingInfoRetrun=new MeetingInfoRetrun();
            meetingInfoRetrun.setLateTime(lis.getLateTime());
            for (String id:lis.getDepartIds().split(","))
                place+=departmentService.selectByPrimaryKey(Integer.parseInt(id)).getName()+",";
            String address=meetingRoomService.selectByPrimaryKey(lis.getRoomId()).getAddress();
            meetingInfoRetrun.setAddress(address);
            meetingInfoRetrun.setId(lis.getId());
            meetingInfoRetrun.setEndTime(lis.getEndTime());
            meetingInfoRetrun.setStartTime(lis.getStartTime());
            meetingInfoRetrun.setDeptName(place.substring(0,place.length()-1));
            meetingInfoRetrun.setName(lis.getName());
            meetingInfoRetrun.setQcode(lis.getRefreshQcode());
            meetingInfoRetrun.setMeetingType(lis.getType());
            list.add(meetingInfoRetrun);
        }
        page.setList(list);
        return Msg.success().add("meetinginfo",page);
    }


    /**
     * ????????????????????
     * @param request
     * @param session
     * @param meetingInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateMeetingInfoAndSignin")
    public Msg updateMeetingInfoAndSignin(HttpServletRequest request, HttpSession session, MeetingInfo meetingInfo,String departNames){
        String deptIds="";
        for (String dep:departNames.split(",")) {
            deptIds+=departmentService.getDepartId(dep).toString()+',';
        }
        meetingInfo.setDepartIds(deptIds.substring(0,deptIds.length()-1));
        meetingInfo.setInsertUsername(session.getAttribute("username").toString());
        System.out.println(meetingInfo);

        MeetingInfoExample example = new MeetingInfoExample();
        MeetingInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(meetingInfo.getId());
        meetingInfoService.updateMeetingInfo(meetingInfo,example);

        //更新签到人员信息
        Integer maxId = userInfoService.selectMaxId();
        boolean newFlag[] = new boolean[maxId+1];
        boolean oldFlag[] = new boolean[maxId+1];
        String[] ids_str = request.getParameterValues("ids[]");
        List<Integer> newIds = new ArrayList<Integer>();
        for(int i=0 ;ids_str != null && i<ids_str.length ;i++){
            Integer id= Integer.parseInt(ids_str[i]);
            newIds.add(id);
            newFlag[id]=true;
        }
        List<Integer> oldIds = meetingInfoService.selectMeetingInfoSelected(meetingInfo.getId());
        for(int i=0 ;i<oldIds.size() ;i++){
            oldFlag[oldIds.get(i)]=true;
        }
        List<Integer> addIds = new ArrayList<Integer>();
        List<Integer> delIds = new ArrayList<Integer>();
        for(int i=0 ; i<newIds.size() ;i++){
            int id= newIds.get(i);
            if(newFlag[id]==true && oldFlag[id]==false)
                addIds.add(id);
            else if(newFlag[id]==false && oldFlag[id]==true)
                delIds.add(id);
        }

        for(int i=0 ;i<oldIds.size(); i++){
            int id = oldIds.get(i);
            if(newFlag[id]==true && oldFlag[id]==false)
                addIds.add(id);
            else if(newFlag[id]==false && oldFlag[id]==true)
                delIds.add(id);
        }
        MeetingSignin addMeetingSignin = new MeetingSignin();
        addMeetingSignin.setMeetingId(meetingInfo.getId());
        addMeetingSignin.setSigninFlag(false);
        addMeetingSignin.setLeaveFlag(false);
        addMeetingSignin.setLateFlag(false);
        addMeetingSignin.setDeleteFlag(false);
        for(int i=0 ;i<addIds.size(); i++){
            addMeetingSignin.setUserId(addIds.get(i));
            meetingSigninService.insertMeetingSignin(addMeetingSignin);
        }

        MeetingSignin delMeetingSignin = new MeetingSignin();
        delMeetingSignin.setMeetingId(meetingInfo.getId());
        delMeetingSignin.setDeleteFlag(true);
        for(int i=0 ;i<delIds.size(); i++){
            delMeetingSignin.setUserId(delIds.get(i));
            meetingSigninService.deleteByUidMeetingid(delMeetingSignin);
        }
        return  Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfoReturnRight")
    public Msg updateMeetingInfo(@RequestParam("meetingId")Integer meetingId){
        List<Integer> list = meetingInfoService.selectMeetingInfoSelected(meetingId);//???ID
        List<UserInfoReturn> userInfo = userInfoService.findAllByExample("","","");//??????????
        List<UserInfoReturn> userInfoReturnRight = new ArrayList<UserInfoReturn>();
        for(Integer i:list){
            for(UserInfoReturn u:userInfo){
                if(i.equals(u.getId())){
                    userInfoReturnRight.add(u);
                    break;
                }
            }
        }
        return Msg.success().add("userInfoReturnRight",userInfoReturnRight);
    }
    @ResponseBody
    @RequestMapping(value = "/insertMeetingInfo")
    public Msg insertMeetingInfo(HttpServletRequest request, HttpSession session,MeetingInfo meetingInfo,String departName){
        String deptIds="";
        for (String dep:departName.split(",")) {
            deptIds+=departmentService.getDepartId(dep).toString()+',';
        }
        String username = (String) session.getAttribute("username");
        meetingInfo.setInsertUsername(username);
        meetingInfo.setDepartIds(deptIds.substring(0,deptIds.length()-1));
        meetingInfo.setDeleteFlag(false);
        String[] ids = request.getParameterValues("ids[]");
        MeetingSignin meetingSignin = new MeetingSignin();
        meetingInfoService.insertMeetingInfo(meetingInfo);
        int meeting_id = Integer.parseInt(meetingInfoService.selectMeetingInfo(meetingInfo).toString());
        meetingSignin.setMeetingId(meeting_id);
        meetingSignin.setSigninFlag(false);
        meetingSignin.setLeaveFlag(false);
        meetingSignin.setLateFlag(false);
        meetingSignin.setDeleteFlag(false);
        for(int i =0;ids != null && i<ids.length;i++){
            meetingSignin.setUserId(Integer.parseInt(ids[i]));
            meetingSigninService.insertMeetingSignin(meetingSignin);
        }
        return  Msg.fail();
    }
}
