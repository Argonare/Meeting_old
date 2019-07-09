package com.meeting.service;

import com.meeting.bean.MeetingRoom;
import com.meeting.bean.MeetingRoomExample;
import com.meeting.dao.MeetingRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomService {
    @Autowired
    MeetingRoomMapper meetingRoomMapper;
    public List<MeetingRoom> selectByExample(Integer id){
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andDeleteFlagEqualTo(false);
        criteria.andIdEqualTo(id);
        return meetingRoomMapper.selectByExample(meetingRoomExample);
    }
    public MeetingRoom selectByPrimaryKey(Integer id){
        return meetingRoomMapper.selectByPrimaryKey(id);
    }
}
