package com.meeting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.*;
import com.meeting.service.MeetingRoomService;
import com.meeting.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meetingRoom")
public class MeetingRoomController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    MeetingRoomService meetingRoomService;
    /**
     * 会议室分页
     * @param pn
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectMeetingRoomPage",method = RequestMethod.GET)
    public Msg selectFullMeetingRoom(@RequestParam(value="page",defaultValue="1")Integer pn){
        PageHelper.startPage(pn,15);
        List<MeetingRoomReturn> list  = meetingRoomService.selectFullMeetingRoom();
        PageInfo page = new PageInfo(list,1);
        return Msg.success().add("meetingroom", page);
    }

    /**
     * 查询会议室全部信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAllMeetingRoom",method = RequestMethod.GET)
    public Msg MeetingRoom(){
        List<MeetingRoomReturn> list  = meetingRoomService.selectMeetingRoomReturn();
        return Msg.success().add("meetingroom", list);
    }

    /**
     * 添加数据到会议室
     * @param meetingRoom
     * @param userInfo
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertMeetingRoom")
    public Msg insertMeetingRoom(MeetingRoom meetingRoom, UserInfo userInfo, HttpSession session){
        Integer a = meetingRoomService.selectByNameByID(userInfo);//获取ID
//        System.out.println(a);
        meetingRoom.setUserId(a);
//        meetingRoom.setInsertUid();
        String username = (String) session.getAttribute("username");
        int uid = userInfoService.getUidByUsername(username);
//        meetingRoom.setInsertUid(uid);
//        meetingRoom.setInsetTime(new Date().getTime());
        meetingRoom.setDeleteFlag(false);
//
        int  a1 = meetingRoomService.insertMeetingRoom(meetingRoom);//添加数据条数
//        System.out.println(a1);
        return Msg.success();
    }

    /**
     * 根据条件修改会议室数据
     * @param meetingRoom
     * @param admin_name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateMeetingRoom")
    public Msg updateMeetingRoom(MeetingRoom meetingRoom,String admin_name){
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andIdEqualTo(meetingRoom.getId());
        Integer userId=userInfoService.selectUserinfoByNameReturn(admin_name).get(0).getId();
        meetingRoom.setUserId(userId);
        meetingRoomService.updateMeetingRoom(meetingRoom,meetingRoomExample);
        return Msg.success();
    }



    /**
     * 根据条件删除会议室数据
     * @param meetingRoom
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMeetingRoom",method = RequestMethod.POST)
    public Msg deleteMeetingRoom(MeetingRoom meetingRoom){
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andIdEqualTo(meetingRoom.getId());
        MeetingRoom temp = new MeetingRoom();
        temp.setDeleteFlag(true);
        if (meetingRoomService.updateMeetingRoom(temp, meetingRoomExample))
            return Msg.success().add("msg","删除成功" );
        else
            return Msg.fail().add("msg", "删除失败");
    }

    /**
     * 删除多条数据
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMeetingRoomByIds/{ids}",method = RequestMethod.POST)
    public Msg updateByExampleSelectiveRoom(@PathVariable("ids")String ids){
        List<Integer> del_ids = new ArrayList<Integer>();
        String[] str_ids= ids.split(",");
        for (String string: str_ids){
            del_ids.add(Integer.parseInt(string));
        }
        meetingRoomService.deleteMeetingRoom(del_ids);
        return Msg.success();
    }



    //校验修改时会议地点是否重复
    @ResponseBody
    @RequestMapping("/checkUpdateMeetingRoomAddress")
    public Msg checkUpdateMeetingRoomAddress(@RequestParam("address")String address,@RequestParam("id")Integer id){
        boolean MeetingRoomAddress = meetingRoomService.checkUpdateMeetingRoomAddress(address,id);

        if (MeetingRoomAddress)
            return Msg.success();
        else
            return Msg.fail();
    }

    //校验添加时会议地点是否重复
    @ResponseBody
    @RequestMapping("/checkAddMeetingRoomaddress")
    public Msg checkAddMeetingRoomaddress(@RequestParam("address")String address){
        boolean MeetingRoomaddress = meetingRoomService.checkAddMeetingRoomaddress(address);
        if (MeetingRoomaddress)
            return Msg.success();
        else
            return Msg.fail();
    }
    //返回二维码的网址
    @ResponseBody
    @RequestMapping(value = "/getPrcode",method = RequestMethod.GET)
    public Msg getPrcode(HttpServletRequest request){
        return Msg.success().add("url", request.getLocalAddr()+":"+request.getLocalPort()+"/qrcode_success");//传入目标网址的url
    }

    @ResponseBody
    @RequestMapping(value = "/refresh_qrcode")
    public Msg refresh_qrcode(HttpSession session, String meeting_id){
        try {
            Map guestList= (Map) session.getServletContext().getAttribute("guestList");
            return Msg.success().add("meeting",guestList.get(meeting_id));
        }catch (NullPointerException e){
            return Msg.fail().add("meeting","还没有人签到");
        }
    }
}
