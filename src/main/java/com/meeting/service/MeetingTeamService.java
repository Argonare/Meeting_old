package com.meeting.service;

import com.meeting.bean.MeetingTeam;
import com.meeting.bean.MeetingTeamExample;
import com.meeting.dao.MeetingTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingTeamService {
    @Autowired
    MeetingTeamMapper meetingTeamMapper;

    public List<MeetingTeam> getMyMeetingTeamsNameByUsername(String username) {
        return meetingTeamMapper.getMyMeetingTeamsNameByUsername(username);
    }

    public String getIdsById(String teamId) {
        MeetingTeamExample example = new MeetingTeamExample();
        MeetingTeamExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(Integer.valueOf(teamId));
        criteria.andDeleteFlagEqualTo(false);
        List<MeetingTeam> meetingTeamList = meetingTeamMapper.selectByExample(example);
        return meetingTeamList.get(0).getMemberIds();
    }
}
