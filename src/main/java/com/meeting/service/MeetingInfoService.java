package com.meeting.service;

import com.meeting.bean.*;
import com.meeting.dao.MeetingInfoMapper;
import com.meeting.dao.MeetingRoomMapper;
import com.meeting.dao.MeetingSigninMapper;
import com.meeting.dao.UserInfoMapper;
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

    public List<wxMeetingInfo> getAfterTimeMeetingInfoByUser(String username,String type){
        int userid = userInfoMapper.getUidByUsername(username);
        List<Integer> myMeetingInfoIds = meetingSigninMapper.getMeetingIdsByUserId(userid);//查找和自己有关的会议

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
        else
            throw new RuntimeException("time的参数有误,应该为after或before");
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
        SimpleDateFormat date = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        for (MeetingInfo m:meetingInfoRetruns){
            wxMeetingInfo wxMeetingInfo = new wxMeetingInfo();
            wxMeetingInfo.setName(m.getName());
            wxMeetingInfo.setAddr(roomMap.get(m.getRoomId()));
            wxMeetingInfo.setDate(date.format(m.getStartTime()));
            wxMeetingInfo.setTime(time.format(m.getStartTime()));
            wxMeetingInfos.add(wxMeetingInfo);
        }
        return wxMeetingInfos;
    }
}
