package com.meeting.dao;

import com.meeting.bean.MeetingTeam;
import com.meeting.bean.MeetingTeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeetingTeamMapper {
    long countByExample(MeetingTeamExample example);

    int deleteByExample(MeetingTeamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MeetingTeam record);

    int insertSelective(MeetingTeam record);

    List<MeetingTeam> selectByExample(MeetingTeamExample example);

    MeetingTeam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MeetingTeam record, @Param("example") MeetingTeamExample example);

    int updateByExample(@Param("record") MeetingTeam record, @Param("example") MeetingTeamExample example);

    int updateByPrimaryKeySelective(MeetingTeam record);

    int updateByPrimaryKey(MeetingTeam record);

    List<MeetingTeam> getMyMeetingTeamsNameByUsername(String username);

}