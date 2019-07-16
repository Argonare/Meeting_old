package com.meeting.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.MeetingInfo;
import com.meeting.bean.MeetingTeam;
import com.meeting.bean.UserInfoReturn;
import com.meeting.dao.MeetingInfoMapper;
import com.meeting.dao.MeetingSigninMapper;
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

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
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

    @Autowired
    MeetingSigninMapper meetingSigninMapper;


    MockMvc mockMvc;

    @Before
    public void initMkcMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getDateGetTime(){
        System.out.println(new Date().getTime());
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

    @Test
    public void getAfterCurrentTimeMeetingInfo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/meetingInfo/wx_newMeeting.do")
            .param("username","1")
//            .param("type","after")
            .param("type","after")
            ).andReturn();
    }

    @Test
    public void getMeetingIdsByUserId(){
        List<Integer> meetingIdsByUserId = meetingSigninMapper.getMeetingIdsByUserId(2);
        System.out.println(meetingIdsByUserId);
    }

    @Test
    public void test1(){
        long time = new Date().getTime();
        String format = new SimpleDateFormat("MM月dd日").format(time);
        String format1 = new SimpleDateFormat("HH:mm").format(time);
        System.out.println(format);
        System.out.println(format1);
    }

//    @Test
//    public void httpTest(){
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxbe64818bb84ee91a&secret=232dd0dc137c8ffe8e84749e1df0140b&js_code=081HonW91tM3MN1NizW91F0mW91HonWu";
//        StringBuilder json = new StringBuilder();
//        try {
//            URL urlObj = new URL(url);
//            URLConnection uc = urlObj.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//            String inputLine = null;
//            while((inputLine = in.readLine()) != null){
//                json.append(inputLine);
//            }
//            in.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(json.toString());
//    }

    //findAllByExample
//    @Test
//    public void findAllByExample() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/findAllByExample")
//                .param("name","p").param("departName","")).andReturn();
//        MockHttpServletRequest request = result.getRequest();
//    }
//
}
