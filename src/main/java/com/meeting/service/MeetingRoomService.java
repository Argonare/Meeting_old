package com.meeting.service;

import com.meeting.bean.MeetingRoom;
import com.meeting.bean.MeetingRoomExample;
import com.meeting.bean.MeetingRoomReturn;
import com.meeting.bean.UserInfo;
import com.meeting.dao.MeetingRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomService {

    @Autowired
    MeetingRoomMapper meetingRoomMapper;

    public List<MeetingRoomReturn> selectFullMeetingRoom() {
        return meetingRoomMapper.selectFullMeetingRoom();
    }

    public List<MeetingRoomReturn> selectMeetingRoomReturn() {
        return meetingRoomMapper.selectMeetingRoomReturn();
    }

    public Integer selectByNameByID(UserInfo userInfo) {
        return meetingRoomMapper.selectByNameByID(userInfo);
    }

    public int insertMeetingRoom(MeetingRoom meetingRoom) {
        return meetingRoomMapper.insertSelective(meetingRoom);
    }

    public boolean checkUpdateMeetingRoomAddress(String address, Integer id) {
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andAddressEqualTo(address);
        List<MeetingRoom> meetingRooms = meetingRoomMapper.selectByExample(meetingRoomExample);
        if (meetingRooms.size()==0)
            return true;
        for( MeetingRoom lis:meetingRooms){
            if (lis.getId()==id){
                return true;
            }
        }
        return false;
    }

    public boolean checkAddMeetingRoomaddress(String address) {
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andAddressEqualTo(address);
        criteria.andDeleteFlagEqualTo(false);
        long count = meetingRoomMapper.countByExample(meetingRoomExample);
        return count==0;
    }

    public boolean updateMeetingRoom(MeetingRoom meetingRoom, MeetingRoomExample example) {
        if (meetingRoomMapper.updateByExampleSelective(meetingRoom, example)==1)
            return true;
        else
            return false;
    }

    public int deleteMeetingRoom(List<Integer> del_ids) {
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andIdIn(del_ids);
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setDeleteFlag(true);
        return meetingRoomMapper.updateByExampleSelective(meetingRoom, meetingRoomExample);
    }
}
