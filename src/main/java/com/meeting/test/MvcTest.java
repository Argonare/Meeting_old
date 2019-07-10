package com.meeting.test;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
    //springMVC的ioc
    @Autowired
    WebApplicationContext context;


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

    //findAllByExample
//    @Test
//    public void findAllByExample() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/findAllByExample")
//                .param("name","p").param("departName","")).andReturn();
//        MockHttpServletRequest request = result.getRequest();
//    }
//
}
