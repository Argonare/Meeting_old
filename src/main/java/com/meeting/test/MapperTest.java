//package com.meeting.test;
//
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.UUID;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//public class MapperTest {
//    //批量使用可以执行批量操作的sqlSession
//    @Autowired
//    SqlSession sqlSession;
//
//    @Test
//    public void testfindAllByExample(){
//        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
////        List<UserInfoReturn> userInfoReturn = mapper.findAllByExample("","");
////        System.out.println(userInfoReturn.size());
////        for (UserInfoReturn c:userInfoReturn)
////            System.out.println(c);
//    }
//
//    @Test
//    public void testCRUD(){
//        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
//        UserInfo userInfo = new UserInfo();
//        userInfo.setDeleteFlag(false);
//        for (int i =0;i<100;i++){
//            String uid = UUID.randomUUID().toString().substring(0,5)+i;
//            userInfo.setUsername(uid);
//            userInfo.setPassword(uid);
//            userInfo.setName(uid);
//            userInfo.setPhone(uid);
//            userInfo.setWechar(uid);
//            userInfo.setType(1);
//            long t=System.currentTimeMillis();
//            mapper.insertSelective( userInfo);
//        }
//    }
//
////    @Test
//    public void Department(){
//        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
//        Department department = new Department();
//        department.setDeleteFlag(false);
//        for (int i=0;i<50;i++){
//            department.setName(String.valueOf(i));
//            department.setInsertUid(i);
//            long t=System.currentTimeMillis();
//            department.setInsertTime(t);
//            department.setUpdateUid(i);
//            department.setUpdateTime(t);
//            department.setDeleteUid(i);
//            department.setDeleteTime(t);
//            departmentMapper.insertSelective(department);
//        }
//    }
//
//    @Test
//    public void MeetingInfo(){
//        MeetingInfoMapper meetingInfoMapper = sqlSession.getMapper(MeetingInfoMapper.class);
//        MeetingInfo meetingInfo = new MeetingInfo();
//        meetingInfo.setDeleteFlag(false);
//        for (int i=60;i<80;i++){
//            meetingInfo.setName(String.valueOf(i));
//            meetingInfo.setIntroId(i);
//            meetingInfo.setType(i);
//            long t=System.currentTimeMillis();
//            meetingInfo.setStartTime(t);
//            meetingInfo.setEndTime(t);
//            meetingInfo.setRoomId(i);
//            meetingInfo.setInsertUid(i);
//            meetingInfo.setInsertTime(t);
//            meetingInfo.setUpdateUid(i);
//            meetingInfo.setUpdateTime(t);
//            meetingInfo.setDeleteUid(i);
//            meetingInfo.setDeleteTime((int) t);
//            meetingInfoMapper.insertSelective(meetingInfo);
//        }
//    }
//
////    public void MeetingIntro(){
////        MeetingIntroMapper meetingIntroMapper = sqlSession.getMapper(MeetingIntroMapper.class);
////        MeetingIntro meetingIntro = new MeetingIntro();
////        for (int i =0;i<10;i++){
////
////            meetingIntro.getIntro();
////        }
////
////    }
//
//    @Test
//    public void MeetingRoom(){
//        MeetingRoomMapper meetingRoomMapper = sqlSession.getMapper(MeetingRoomMapper.class);
//        MeetingRoom meetingRoom = new MeetingRoom();
//        meetingRoom.setDeleteFlag(false);
//        for (int i=0;i<20;i++){
//            long t=System.currentTimeMillis();
//            meetingRoom.setAddress(i+"教学楼"+i);
//            meetingRoom.setUserId(i);
//            meetingRoom.setInsertUid(i);
//            meetingRoom.setInsetTime(t);
//            meetingRoom.setUpdateUid(i);
//            meetingRoom.setUpdateTime(t);
//            meetingRoom.setDeleteUid(i);
//            meetingRoom.setDeleteTime(t);
//            meetingRoomMapper.insertSelective(meetingRoom);
//        }
//    }
//
//    @Test
//    public void MeetingSignin(){
//        MeetingSigninMapper meetingSigninMapper = sqlSession.getMapper(MeetingSigninMapper.class);
//        MeetingSignin meetingSignin = new MeetingSignin();
//        meetingSignin.setDeleteFlag(false);
//        meetingSignin.setSigninFlag(true);
//        meetingSignin.setSignoutFlag(false);
//        meetingSignin.setLateFlag(true);
//        meetingSignin.setLeaveFlag(false);
//        for (int i=0;i<10;i++){
//            long t=System.currentTimeMillis();
//            meetingSignin.setUserId(i);
//            meetingSignin.setMeetingId(i);
//            meetingSignin.setSigninTime(t);
//            meetingSignin.setSignoutTime(t);
//            meetingSignin.setDeleteUid(i);
//            meetingSignin.setDeleteTime(t);
//            meetingSigninMapper.insertSelective(meetingSignin);
//        }
//    }
//}