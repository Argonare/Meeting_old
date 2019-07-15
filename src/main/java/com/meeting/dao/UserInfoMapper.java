package com.meeting.dao;

import com.meeting.bean.UserInfo;
import com.meeting.bean.UserInfoExample;
import java.util.List;

import com.meeting.bean.UserInfoReturn;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

    Integer selectMaxId();

    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfoReturn> selectUserReturn();

    List<UserInfo> checkUserinfoByPhone(String phone);

    int getUidByUsername(@Param("username") String username);//获取用户名

    List<UserInfoReturn> selectUserinfoByNameReturn(@Param("name") String name);

    List<UserInfoReturn> selectUserinfoByUsernameReturn(@Param("username") String username);

    List<UserInfoReturn> findAllByExample(@Param("username") String username,@Param("name") String name,@Param("departName") String departName);

    Integer getUserTypeByUsername(String username);
}