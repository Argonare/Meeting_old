package com.meeting.service;

import com.meeting.bean.MeetingInfo;
import com.meeting.bean.MeetingInfoRetrun;
import com.meeting.dao.MeetingInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingInfoService {
    @Autowired
    MeetingInfoMapper meetingInfoMapper;


    public Long selectMeetingInfo(MeetingInfo meetingInfo) {
        return meetingInfoMapper.selectMeetingInfo(meetingInfo);
    }

    public long insertMeetingInfo(MeetingInfo meetingInfo) {
        return meetingInfoMapper.insert(meetingInfo);
    }

    public List<MeetingInfo> findAllMeetingInfo() {
        return meetingInfoMapper.findAllMeetingInfo();
    }
}
