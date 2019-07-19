package com.meeting.service;

import com.meeting.bean.MeetingSignin;
import com.meeting.bean.MeetingSigninExample;
import com.meeting.bean.UserSiginDeptInfo;
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
        criteria.andDeleteFlagEqualTo(false);
        return meetingSigninMapper.selectByExample(meetingSigninExample);
    }
    public boolean updateMeetingSignin(MeetingSignin meetingSignin, MeetingSigninExample example) {
        if (meetingSigninMapper.updateByExampleSelective(meetingSignin, example)==1)
            return true;
        else
            return false;
    }
    public List<MeetingSignin> selectMeetingSigninByUsername(Integer userId){
        MeetingSigninExample meetingSigninExample = new MeetingSigninExample();
        MeetingSigninExample.Criteria criteria = meetingSigninExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return meetingSigninMapper.selectByExample(meetingSigninExample);
    }

    public List<UserSiginDeptInfo>selectDepartSiginInfo(Integer meeting_id){
        System.out.println(meetingSigninMapper.selectDepartSiginInfo(meeting_id));
        return meetingSigninMapper.selectDepartSiginInfo(meeting_id);
    }
    public List<MeetingSignin> selectMeetingSigninByMeetingIdAndUserId(Integer meetId,Integer userId){
        MeetingSigninExample meetingSigninExample = new MeetingSigninExample();
        MeetingSigninExample.Criteria criteria = meetingSigninExample.createCriteria();
        criteria.andMeetingIdEqualTo(meetId);
        criteria.andUserIdEqualTo(userId);
        criteria.andDeleteFlagEqualTo(false);
        return meetingSigninMapper.selectByExample(meetingSigninExample);
    }

}
