package com.meeting.service;


import com.meeting.bean.*;
import com.meeting.dao.DepartmentMapper;
import com.meeting.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    public boolean checkUser(String userid, String password) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria  criteria = userInfoExample.createCriteria();
        criteria.andUsernameEqualTo(userid);
        criteria.andPasswordEqualTo(password);
        criteria.andDeleteFlagEqualTo(false);
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
    public UserInfo selectUser(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public List<UserInfoReturn> findUserInfoReturnByUsernames(String[] usernames) {
        List<UserInfoReturn> userInfoReturns = new ArrayList<UserInfoReturn>();
        UserInfoReturn userInfoReturn = new UserInfoReturn();
        if(usernames == null){
            return userInfoReturns;
        }
        for(String usernmae : usernames){
            List<UserInfoReturn> temp = userInfoMapper.findAllByExample(usernmae,"","");
            if(temp.size() != 0){
                userInfoReturns.add(temp.get(0));
            }
        }
        return userInfoReturns;
    }

    public List<UserInfoReturn> findUserInfoReturnByUsernamesInMeetingTeam(String[] usernames) {
        //根据usernames查询用户的详细信息
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        List<Integer> username_integer = new ArrayList<Integer>();
        for(int i=0 ;i<usernames.length ;i++){
            try{
                Integer username = Integer.valueOf(usernames[i]);
                username_integer.add(username);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(username_integer.isEmpty())return new ArrayList<UserInfoReturn>();
        criteria.andIdIn(username_integer);
        criteria.andDeleteFlagEqualTo(false);
        List<UserInfo> userInfoList = userInfoMapper.selectByExample(example);
        //查询所有部门,并做成一个map表
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria departmentCriteria = departmentExample.createCriteria();
        departmentCriteria.andDeleteFlagEqualTo(false);
        List<Department> departmentList = departmentMapper.selectByExample(departmentExample);
        Map<Integer,String> deptMap = new HashMap<Integer,String>();
        for (Department d:departmentList)
            deptMap.put(d.getId(),d.getName());
        //根据map表处理查询到的用户详细信息
        List<UserInfoReturn> userInfoReturnList = new ArrayList<UserInfoReturn>();
        for (UserInfo u:userInfoList){
            userInfoReturnList.add(new UserInfoReturn(u.getId(),u.getUsername(),u.getName(),null,null,deptMap.get(u.getDepartId())));
        }
        return userInfoReturnList;
    }
}
