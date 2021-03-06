package com.meeting.dao;

import com.meeting.bean.MeetingInfo;
import com.meeting.bean.MeetingSignin;
import com.meeting.bean.MeetingSigninExample;
import java.util.List;

import com.meeting.bean.UserSiginDeptInfo;
import org.apache.ibatis.annotations.Param;

public interface MeetingSigninMapper {
    long countByExample(MeetingSigninExample example);

    int deleteByExample(MeetingSigninExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MeetingSignin record);

    int insertSelective(MeetingSignin record);

    List<MeetingSignin> selectByExample(MeetingSigninExample example);

    MeetingSignin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MeetingSignin record, @Param("example") MeetingSigninExample example);

    int updateByExample(@Param("record") MeetingSignin record, @Param("example") MeetingSigninExample example);

    int updateByPrimaryKeySelective(MeetingSignin record);

    int updateByPrimaryKey(MeetingSignin record);

    int deleteByUidMeetingid(MeetingSignin delMeetingSignin);//????MeetingSignin???????????

    List<Integer> getMeetingIdsByUserId(int userid);

    List<UserSiginDeptInfo>selectDepartSiginInfo(Integer meeting_id);

    Boolean getIsSignin(@Param("meetingId") Integer meetingId,@Param("userId") int userId);
}