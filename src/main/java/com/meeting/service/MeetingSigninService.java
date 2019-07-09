package com.meeting.service;

import com.meeting.bean.MeetingSignin;
import com.meeting.dao.MeetingSigninMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
