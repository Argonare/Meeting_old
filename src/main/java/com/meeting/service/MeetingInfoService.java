package com.meeting.service;

import com.meeting.bean.MeetingInfo;
import com.meeting.bean.MeetingInfoExample;
import com.meeting.bean.MeetingInfoRetrun;
import com.meeting.dao.MeetingInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingInfoService {
    @Autowired
    MeetingInfoMapper meetingInfoMapper;
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

}
