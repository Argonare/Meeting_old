package com.meeting.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.meeting.bean.MeetingInfo;
import com.meeting.bean.MeetingTeam;
import com.meeting.bean.Msg;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    //声明request变量
    private MockHttpServletRequest request;

    //声明request变量
    private MockHttpServletResponse response;

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
        Boolean flag = meetingSigninMapper.getIsSignin(1,2);
        System.out.println(flag==null?false:flag);
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
    public void test1() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/QRcode.do")).andReturn();
    }

    @Test
    public void QRcode(){
        int width=300;
        int height=300;

        String format="png";
        //这里如果你想自动跳转的话，需要加上https://
        String content="https://github.com/hbbliyong/QRCode.git";

        HashMap hits=new HashMap();
        hits.put(EncodeHintType.CHARACTER_SET, "utf-8");//编码
        //纠错等级，纠错等级越高存储信息越少
        hits.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //边距
        hits.put(EncodeHintType.MARGIN, 2);

        ServletOutputStream stream = null;
        stream = response.getOutputStream();
        try {
            BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hits);
            //如果做网页版输出可以用输出到流

            MatrixToImageWriter.writeToStream(bitMatrix, format,stream);
//            Path path=new File("F:/zxingQRCode.png").toPath();
//            System.out.println(bitMatrix);
//            MatrixToImageWriter.writeToPath(bitMatrix, format, path);


//            ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
//            ImageIO.write(, "png", baos);//写入流中
//            byte[] bytes = baos.toByteArray();//转换成字节
//            BASE64Encoder encoder = new BASE64Encoder();
//            String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
//            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
//            System.out.println(png_base64);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(stream);


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
    @Test
    public void getDepartSiginInfo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/meetingSignin/getDepartSiginInfo")
                .param("meetingId","2")).andReturn();
    }
}
