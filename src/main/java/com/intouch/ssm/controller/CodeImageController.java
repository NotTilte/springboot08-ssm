package com.intouch.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


@Controller
@RequestMapping("/image")
public class CodeImageController {
    /* 定义验证码图像的宽度和高度*/
    private static final int WIDTH=200;
    private static final int HEIGHT=80;


    //验证码由5个字符构成
    private static final int NUM=5;
    //验证码的字符源由26个大写字母字符和0~9的数字构成,共36个字符（0-35编号）
    private static char[] seq=
            {'A','B','C','D','E','F','G',
                    'H','I','J','K','L','M','N','O','P','Q',
                    'R','S','T','U','V','W','X','Y','Z',
                    '0','1','2','3','4','5','6','7','8','9'};
    
    /* 通过Random对象，产生一个随机颜色对象
     * new Color(R,G,B)*/
    private Color randomColor(Random r){
        return new Color(r.nextInt(255),
                r.nextInt(255),r.nextInt(255));
    }

    //直接发送字节流
    @RequestMapping(value="/validatecode")
    public void showCodeImage(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        Random r=new Random();//随机数对象
        /* 内存图像*/
        BufferedImage image=new BufferedImage(
                WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        /* 通过内存图像获得绘图对象*/
        Graphics g=image.getGraphics();
        /* ①设置颜色 */
        g.setColor(randomColor(r));
        /* ②画一个实心的矩形 */
        g.fillRect(0, 0, WIDTH, HEIGHT);


        /* 用于存储产生的随机字符*/
        StringBuffer sb=new StringBuffer();
        /* ③绘制验证码 */
        for(int i=0;i<NUM;i++){//NUM=5;
            g.setColor(randomColor(r));
            int h=(int)(HEIGHT*60/100*r.nextDouble()+
                    (HEIGHT*30/100));
            //设置字体风格样式及大小
            g.setFont(new Font(null,Font.BOLD|Font.ITALIC,h));

            //随机选取验证字符
            String ch=String.valueOf(seq[r.nextInt(seq.length)]);
            //添加到StringBuffer中
            sb.append(ch);//重要
            //画出具体字符
            g.drawString(ch, i*WIDTH/NUM, h);

        }
        //将生成的验证码存入HttpSession对象中
        HttpSession session=request.getSession();
        session.setAttribute("backCode",sb.toString());

        /* ④绘制干扰线 */
        for(int i=0;i<=12;i++){
            g.setColor(randomColor(r));
            g.drawLine(r.nextInt(WIDTH),
                    r.nextInt(HEIGHT),
                    r.nextInt(WIDTH),
                    r.nextInt(HEIGHT));
        }
        /* ⑤创建输出流*/
        ServletOutputStream sos=response.getOutputStream();
        //利用ImageIO类，将缓存图像写入输出流中
        ImageIO.write(image, "JPEG", sos);
        sos.close();
    }
}
