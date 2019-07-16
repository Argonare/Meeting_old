package com.meeting.service;

import com.meeting.bean.MeetingTeam;
import com.meeting.bean.MeetingTeamExample;
import com.meeting.bean.MeetingTeamReturn;
import com.meeting.dao.MeetingTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingTeamService {

    @Autowired
    MeetingTeamMapper meetingTeamMapper;

    public List<MeetingTeamReturn> selectTeamReturn() {
        return meetingTeamMapper.selectTeamReturn();
    }


    public boolean updateMeetingTeam(MeetingTeam meetingTeam, MeetingTeamExample meetingTeamExample) {
        if (meetingTeamMapper.updateByExampleSelective(meetingTeam, meetingTeamExample)==1)
            return true;
        else
            return false;
    }

    public boolean insertMeetingTeam(MeetingTeam meetingTeam) {
        if (meetingTeamMapper.insert(meetingTeam)==1)
            return true;
        else
            return false;
    }

    public String selectMeetingTeamids(Integer teamId) {
        return meetingTeamMapper.selectMeetingTeamids(teamId);
    }

    public boolean checkAddMeetingTeam(String name) {
        MeetingTeamExample meetingTeamExample = new MeetingTeamExample();
        MeetingTeamExample.Criteria criteria = meetingTeamExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andDeleteFlagEqualTo(false);
        long count = meetingTeamMapper.countByExample(meetingTeamExample);
        return count==0;

    }


    public boolean checkUpdateMeetingTeamName(String name, Integer id) {
        MeetingTeamExample meetingTeamExample = new MeetingTeamExample();
        MeetingTeamExample.Criteria criteria = meetingTeamExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<MeetingTeam> meetingTeams = meetingTeamMapper.selectByExample(meetingTeamExample);
        if (meetingTeams.size()==0)
            return true;
        for (MeetingTeam lis : meetingTeams){
            if (lis.getId()==id)
                return true;
        }
        return false;
    }
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
