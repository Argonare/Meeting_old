package com.meeting.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;

public class QRcode {
    public static void creatRrCode(String contents, int width, int height, HttpServletResponse response) {
        Hashtable hints = new Hashtable();

        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //容错级别最高
        hints.put( EncodeHintType.CHARACTER_SET, "utf-8");  //设置字符编码
        hints.put(EncodeHintType.MARGIN, 1);//二维码空白区域,最小为0也有白边,只是很小,最小是6像素左右
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints); // 1、读取文件转换为字节数组
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BufferedImage image = toBufferedImage(bitMatrix);
            //转换成png格式的IO流
            ImageIO.write(image, "png", response.getOutputStream());
//            byte[] bytes = out.toByteArray();
//            // 2、将字节数组转为二进制
//            BASE64Encoder encoder = new BASE64Encoder();
//            binary = encoder.encodeBuffer(bytes).trim();
        } catch (WriterException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
