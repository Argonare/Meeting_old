package com.meeting.service;

import com.meeting.bean.*;
import com.meeting.dao.MeetingInfoMapper;
import com.meeting.dao.MeetingRoomMapper;
import com.meeting.dao.MeetingSigninMapper;
import com.meeting.dao.UserInfoMapper;
import com.meeting.utils.DateCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MeetingInfoService {
    @Autowired
    MeetingInfoMapper meetingInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    MeetingSigninMapper meetingSigninMapper;

    @Autowired
    MeetingRoomMapper meetingRoomMapper;


    public List<MeetingInfo> findAllMeetingInfo() {
        return meetingInfoMapper.findAllMeetingInfo();
    }

    public boolean updateMeetingInfo(MeetingInfo meetingInfo, MeetingInfoExample example) {

        if (meetingInfoMapper.updateByExampleSelective(meetingInfo, example)==1)
            return true;
        else
            return false;
    }
    public List<Integer> selectMeetingInfoSelected(int meetingId) {
        return meetingInfoMapper.selectMeetingInfoSelected(meetingId);
    }
    public Long selectMeetingInfo(MeetingInfo meetingInfo) {
        return meetingInfoMapper.selectMeetingInfo(meetingInfo);
    }
    public long insertMeetingInfo(MeetingInfo meetingInfo) {
        return meetingInfoMapper.insert(meetingInfo);
    }
    public MeetingInfo selectMeetingInfoById(Integer id) {
        return meetingInfoMapper.selectByPrimaryKey(id);
    }

    public int deleteByExampleMeetingInfo(List<Integer> del_ids) {
        MeetingInfoExample example = new MeetingInfoExample();
        MeetingInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(del_ids);
        MeetingInfo meetingInfo = new MeetingInfo();
        meetingInfo.setDeleteFlag(true);
        return meetingInfoMapper.updateByExampleSelective(meetingInfo, example);
    }

    public List<wxMeetingInfo> getAfterTimeMeetingInfoByUser(String username,String type,String date, String date2){
        List<Integer> myMeetingInfoIds = new ArrayList<>();

        int userid = userInfoMapper.getUidByUsername(username);//查询用户id
        List<Integer> meetingIdsByUserIds1 = meetingSigninMapper.getMeetingIdsByUserId(userid);//查询明确指定需要签到的会议
        myMeetingInfoIds.addAll(meetingIdsByUserIds1);

        int userDeptid = userInfoMapper.getDeptidByUsername(username);//查询用户的部门id
        List<Integer> meetingIdsByUserIds2 = meetingInfoMapper.getMeetingIdByUserDeptidAndMeetingType(userDeptid);//查询讲座形式需要签到的会议
        myMeetingInfoIds.addAll(meetingIdsByUserIds2);

        MeetingInfoExample example = new MeetingInfoExample();
        MeetingInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(false);
        criteria.andIdIn(myMeetingInfoIds);
        if("after".equals(type.toLowerCase()))
            //未开始过的会议
            criteria.andStartTimeGreaterThanOrEqualTo(new Date().getTime());
        else if("before".equals(type.toLowerCase()))
            //以开始过的会议
            criteria.andStartTimeLessThan(new Date().getTime());
        else if("single".equals(type.toLowerCase())){
            //查询单个月会议
            Long startTime = DateCalculation.getMonthBegin(date);
            Long endTime = DateCalculation.getMonthEnd(date);
            criteria.andStartTimeBetween(startTime,endTime);
        }else if("between".equals(type.toLowerCase())){
            //查询某两个时间段之间的会议
        }else
            throw new RuntimeException("type的参数错误,应该为after,before,single,between");
        List<MeetingInfo> meetingInfoRetruns = meetingInfoMapper.selectByExample(example);

        //查询会议室，并做成map
        MeetingRoomExample roomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria roomCriteria = roomExample.createCriteria();
        roomCriteria.andDeleteFlagEqualTo(false);
        List<MeetingRoom> meetingRooms = meetingRoomMapper.selectByExample(roomExample);
        Map<Integer,String> roomMap = new HashMap<>();
        for (MeetingRoom m: meetingRooms)
            roomMap.put(m.getId(),m.getAddress());

        //处理成需要的数据
        List<wxMeetingInfo> wxMeetingInfos = new ArrayList<>();
        SimpleDateFormat format1 = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
        for (MeetingInfo m:meetingInfoRetruns){
            wxMeetingInfo wxMeetingInfo = new wxMeetingInfo();
            wxMeetingInfo.setName(m.getName());//会议名称
            wxMeetingInfo.setAddr(roomMap.get(m.getRoomId()));//会议室
            wxMeetingInfo.setDate(format1.format(m.getStartTime()));//会议日期
            wxMeetingInfo.setTime(format2.format(m.getStartTime()));//会议时间

            Boolean isSignin = meetingSigninMapper.getIsSignin(m.getId(),userid);
            //应为讲座形式会议在签到前signin表中示无数据的所以需要判断是否为null，为null时表示未签到返回false，否则返回isSignin原来的值
            wxMeetingInfo.setIsSignin(isSignin==null?false:isSignin);

            wxMeetingInfos.add(wxMeetingInfo);
        }
        return wxMeetingInfos;
    }

}
