package com.meeting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.*;
import com.meeting.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
    @RequestMapping("/wx_newMeeting.do")
    public Msg wx_newmeeting(HttpServletRequest request){
        String username = request.getParameter("username");
        String type = request.getParameter("type");
        String date = request.getParameter("date");
        String date2 = request.getParameter("date2");

        List<wxMeetingInfo> wxMeetingInfos = meetingInfoService.getAfterTimeMeetingInfoByUser(username,type,date,date2);

        return Msg.success().add("wxMeetingInfo",wxMeetingInfos).add("count",wxMeetingInfos.size());
    }

    @ResponseBody
    @RequestMapping(value = "/findAllMeetingInfo",method = RequestMethod.GET)
    public Msg MeetingInfo(@RequestParam(value="page",defaultValue="1")Integer pn){
        PageHelper.startPage(pn,15);
        List<MeetingInfo> meetingInfo  = meetingInfoService.findAllMeetingInfo();
        PageInfo page = new PageInfo(meetingInfo,1);
        List<MeetingInfoRetrun>list=new ArrayList<MeetingInfoRetrun>();
        for (MeetingInfo lis:meetingInfo){
            String place=",";
            MeetingInfoRetrun meetingInfoRetrun=new MeetingInfoRetrun();
            meetingInfoRetrun.setLateTime(lis.getLateTime());
            for (String id:lis.getDepartIds().substring(1,lis.getDepartIds().length()-1).split(","))
                place+=departmentService.selectByPrimaryKey(Integer.parseInt(id)).getName()+",";
            String address=meetingRoomService.selectByPrimaryKey(lis.getRoomId()).getAddress();
            meetingInfoRetrun.setAddress(address);
            meetingInfoRetrun.setId(lis.getId());
            meetingInfoRetrun.setEndTime(lis.getEndTime());
            meetingInfoRetrun.setStartTime(lis.getStartTime());
            meetingInfoRetrun.setDeptName(place.substring(1,place.length()-1));
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
        String deptIds=",";
        boolean flag = false;
        for (String dep:departNames.split(",")) {
            deptIds+=departmentService.getDepartId(dep).toString()+',';
            if(flag)deptIds+=',';
            flag = true;
            deptIds+=departmentService.getDepartId(dep).toString();
        }
        deptIds+=',';
        meetingInfo.setDepartIds(deptIds);
        meetingInfo.setInsertUsername(session.getAttribute("username").toString());

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
        String deptIds=",";
        boolean flag = false;
        for (String dep:departName.split(",")) {
            if(flag)deptIds+=',';
            flag = true;
            deptIds+=departmentService.getDepartId(dep).toString();
        }
        String username = (String) session.getAttribute("username");
        meetingInfo.setInsertUsername(username);
        meetingInfo.setDepartIds(deptIds+',');
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
        return  Msg.success();
    }
    @ResponseBody
    @RequestMapping(value = "/getQcodeType")
    public Msg getQcodeType(int meetingId){
        Boolean status=meetingInfoService.selectMeetingInfoById(meetingId).getRefreshQcode();
        return Msg.success().add("type",status?1:0);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteByExampleSelectiveMeetingInfo/{ids}",method = RequestMethod.POST)
    public Msg updateByExampleSelectiveUser(@PathVariable("ids")String ids){
        List<Integer> del_ids = new ArrayList<Integer>();
        String str_ids[] = ids.split(",");
        for (String string: str_ids){
            del_ids.add(Integer.parseInt(string));
        }
        meetingInfoService.deleteByExampleMeetingInfo(del_ids);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMeetingInfo",method = RequestMethod.POST)
    public Msg deleteMeetingInfo(MeetingInfo meetingInfo){
        MeetingInfoExample example = new MeetingInfoExample();
        MeetingInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(meetingInfo.getId());
        meetingInfo.setDeleteFlag(true);
        if (meetingInfoService.updateMeetingInfo(meetingInfo, example))
            return Msg.success().add("msg", "删除成功");
        else
            return Msg.fail().add("msg", "删除失败");
    }
    @ResponseBody
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public Msg fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpSession session){
        String path = "E:/" + new Date().getTime() + file.getOriginalFilename();
        String filename = file.getOriginalFilename();
        System.out.println(path);
        System.out.println(filename);
//        userDao.insertUpload(name, path, filename);
        File newFile = new File(path);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Msg.success();
    }

}
