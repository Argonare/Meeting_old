package com.meeting.service;

import com.meeting.bean.MeetingSignin;
import com.meeting.bean.MeetingSigninExample;
import com.meeting.dao.MeetingSigninMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingSigninService {
    @Autowired
    MeetingSigninMapper meetingSigninMapper;
    public boolean insertMeetingSignin(MeetingSignin meetingSignin) {
        if (meetingSigninMapper.insert(meetingSignin)==1)
            return true;
        else
            return false;
    }
    public int deleteByUidMeetingid(MeetingSignin delMeetingSignin) {
        return meetingSigninMapper.deleteByUidMeetingid(delMeetingSignin);
    }
    public List<MeetingSignin> selectMeetingSigninByMeetingId(Integer meetId){
        MeetingSigninExample meetingSigninExample = new MeetingSigninExample();
        MeetingSigninExample.Criteria criteria = meetingSigninExample.createCriteria();
        criteria.andMeetingIdEqualTo(meetId);
        return meetingSigninMapper.selectByExample(meetingSigninExample);
    }
}
