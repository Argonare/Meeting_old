package com.meeting.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.MeetingInfo;
import com.meeting.bean.MeetingTeam;
import com.meeting.bean.UserInfoReturn;
import com.meeting.dao.MeetingInfoMapper;
import com.meeting.dao.MeetingTeamMapper;
import com.meeting.dao.UserInfoMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
    //springMVC的ioc
    @Autowired
    WebApplicationContext context;

    @Autowired
    MeetingTeamMapper meetingTeamMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    MeetingInfoMapper meetingInfoMapper;
    //虚拟mvc请求，获取到处理结果
    MockMvc mockMvc;

    @Before
    public void initMkcMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getDateGetTime(){
        System.out.println(new Date().getTime());
    }

    @Test
    public void test1(){

        List<MeetingInfo> allMeetingInfo = meetingInfoMapper.findAllMeetingInfo();
        System.out.println(allMeetingInfo.size());
        PageHelper.startPage(2,15);
        PageInfo page = new PageInfo(allMeetingInfo,1);
        System.out.println(page.getList().size());
    }

//    @Test
//    public void getMyMeetingTeamsByUsername(){
////        List<MeetingTeam> myMeetingTeamsByUsername = meetingTeamMapper.getMyMeetingTeamsByUsername("1");
////        System.out.println(myMeetingTeamsByUsername);
//        System.out.println(meetingTeamMapper.getMyMeetingTeamsNameByUsername("1").toString());
//    }

    @Test
    public void getUserInfosByTeamId() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/meetingTeam/getUserInfosByTeamId")
                .param("teamId","1")).andReturn();
    }

    @Test
    public void getMyMeetingTeamsNameByUsername(){
        List<UserInfoReturn> temp = userInfoMapper.findAllByExample("1716143223","","");
        System.out.println(temp.toString());

    }

    @Test
    public void getUserTypeByUsername(){
        Integer userType = userInfoMapper.getUserTypeByUsername("0");
        System.out.println(userType);
    }

    //findAllByExample
//    @Test
//    public void findAllByExample() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/findAllByExample")
//                .param("name","p").param("departName","")).andReturn();
//        MockHttpServletRequest request = result.getRequest();
//    }
//
}
