package com.meeting.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.meeting.bean.Msg;
import com.meeting.utils.QRcode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;

@Controller
public class TestController {

    @RequestMapping("/QRcode.do")
    public Msg QRcode(HttpServletResponse response, Integer meetingid, HttpServletRequest request){
        QRcode.creatRrCode("https://"+request.getLocalAddr()+":"+request.getLocalPort()+"/jumpPage/qrcode_success?"+meetingid+"&timestamp="+new Date().getTime(), 200,200,response);
        return Msg.success();
    }
}
