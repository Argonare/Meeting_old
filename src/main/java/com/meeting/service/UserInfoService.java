package com.meeting.service;

import com.meeting.bean.UserInfo;
import com.meeting.bean.UserInfoExample;
import com.meeting.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    public boolean checkUser(String userid, String password) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria  criteria = userInfoExample.createCriteria();
        criteria.andUsernameEqualTo(userid);
        criteria.andPasswordEqualTo(password);
        if(userInfoMapper.countByExample(userInfoExample) == 1){
            return true;
        }else{
            return false;
        }
    }
}
