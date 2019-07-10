package com.meeting.service;


import com.meeting.bean.UserInfo;
import com.meeting.bean.UserInfoExample;
import com.meeting.bean.UserInfoReturn;
import com.meeting.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserInfoService userInfoService;
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


    public List<UserInfoReturn> selectUserReturn() {
        return userInfoMapper.selectUserReturn();
    }

    public boolean insertUser(UserInfo userInfo) {
        userInfo.setDeleteFlag(false);
        userInfo.setPassword("000000");
        if (userInfoMapper.insert(userInfo)==1)
            return true;
        else
            return false;
    }

    public boolean checkAddUserInfo(String username) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andDeleteFlagEqualTo(false);
        long count = userInfoMapper.countByExample(userInfoExample);
        return count==0;
    }

    public boolean checkAddUserinfoPhone(String phone) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        criteria.andDeleteFlagEqualTo(false);
        long count = userInfoMapper.countByExample(userInfoExample);
        return count==0;
    }

    public boolean updateByExampleSelectiveUser(UserInfo userInfo) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andIdEqualTo(userInfo.getId());
        if (userInfoMapper.updateByExampleSelective(userInfo, userInfoExample)==1)
            return true;
        else
            return false;
    }

    public boolean checkUpdatePhone(String phone, String userinfo) {
        List<UserInfo> userInfos = userInfoMapper.checkUserinfoByPhone(phone);
        if (userInfos.size()==0)
            return true;
        for (UserInfo us:userInfos){
            if (us.getId()==Integer.parseInt(userinfo)){
                System.out.println(us.getId());
                return true;
            }
        }
        return false;
    }

    public int deleteByExampleUser(List<Integer> ids) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        UserInfo temp = new UserInfo();
        temp.setDeleteFlag(true);
        return userInfoMapper.updateByExampleSelective(temp, example);
    }

    public int getUidByUsername(String username) {
        return userInfoMapper.getUidByUsername(username);
    }


    public List<UserInfoReturn> selectUserinfoByNameReturn(String name) {
        return userInfoMapper.selectUserinfoByNameReturn(name);
    }
    public List<UserInfoReturn> selectUserinfoByUsernameReturn(String username) {
        return userInfoMapper.selectUserinfoByUsernameReturn(username);
    }
    public Integer selectMaxId() {
        return userInfoMapper.selectMaxId();
    }

    public List<UserInfoReturn> findAllByExample(String username,String name, String departName) {
        return userInfoMapper.findAllByExample(username,name,departName);
    }
}
