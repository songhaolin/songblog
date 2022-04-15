package com.songblog.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.util.ResourceUtils;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description:
 * @Author: shl
 * @Date: 2022/4/6
 **/
public class CommonUtils {
    /**
     * @Description: 获取图片上传路径
     * @Date: 2022/4/6
     * @Param null:
     **/
    public static String uploadFilePath = "/" + new SimpleDateFormat("yyyyddmm").format(new Date()) + "/upload/img/";

    public static String getUploadFilePath() {
        return uploadFilePath;
    }

    /**
     * @Description: 获取当前sqlServerDate
     * @Date: 2022/4/6
     **/
    public static String getSqlServerDate() {
        return new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date());
    }

    /**
     * 判断User-Agent 是不是来自于手机
     *
     * @return
     */
    public static boolean checkAgentIsMobile(HttpServletRequest servletRequest) {
        String ua = servletRequest.getHeader("User-Agent");
        final String[] agent = {"Android", "iPhone", "iPod", "NokiaN9", "Windows Phone", "MQQBrowser"};
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @Description: 判断是否为常见图片格式
     * @Date: 2022/4/6
     * @Param suffix:
     **/
    public static boolean isImageSuffix(String suffix) {
        if (StringUtils.isEmpty(suffix)) {
            return false;
        }
        String[] suffixs = {".BMP", ".GIF", ".JPEG", ".PNG", ".ICO", ".JPG"};
//        if (Arrays.asList(suffixs).contains(suffix)){
//            return true;
//        }
        if (ArrayUtil.contains(suffixs, suffix)) {
            return true;
        }
        return false;
    }
    /**
     * @Description: 根据文件路径判断当前文件是否为图片
     * @Date: 2022/4/6
     * @Param imagePath:
     **/
    public static boolean isImage(String imagePath){
        File file = new File(imagePath);
        return isImage(file);
    }
    /**
     * @Description: 判断当前文件是否为图片
     * @Date: 2022/4/6
     * @Param file:
     **/
    public static boolean isImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null || StringUtils.isEmpty(file.getName())){
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    /**
     * @Description: 判断当前文件是否为符合规定像素尺寸的图片
     * @Date: 2022/4/6
     * @Param width:
     * @Param height:
     * @Param file:
     **/
    public static boolean isImagePixel(int width, int height, File file) {
        if (file == null||!isImage(file)) {
            return false;
        }
        try {
            BufferedImage image = ImageIO.read(file);
            Raster rs = image.getRaster();
            Rectangle rc = rs.getBounds();
            if (rc.getWidth()==width&&rc.getHeight()==height){
                return true;
            }
        }catch (IOException e){
            return false;
        }
        return false;
    }
    /**
     * 获取客户端ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
    /**
     * @Description: 使用hutool的captcha库生成图片二维码
     * @Date: 2022/4/6

     **/
    public static void getCaptcha(){
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(80,40,4,1000);
        String code = captcha.getCode();
        System.out.println(code);
        writeToFile("C:/Users/song/IdeaProjects/wxk1991/src/main/java/com/wxk1991/utils/1.jpg",captcha.getImageBytes());
    }

    public static void writeToFile(String path,byte[] bytes){
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(path);
            f.write(bytes);
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description: 获取图形验证码，并将code信息放在请求链接中
     * @Date: 2022/4/7
     * @Param request:
     **/
    public static CircleCaptcha getCaptcha(HttpServletRequest request){
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(80,40,4,0);
        String ciclrCaptchaCode = captcha.getCode();
        request.getSession().setAttribute("ciclrCaptchaCode",ciclrCaptchaCode);
        return captcha;
    }
    /**
     * @Description: 文件后缀名前加一个简单的时间戳
     * @Date: 2022/4/7
     * @Param fileName:
     **/
    public static String getFileName(String fileName){
        int index = fileName.lastIndexOf('.');
        fileName = fileName.substring(0,index)+"_"+ IdUtil.simpleUUID() +fileName.substring(index);
        return fileName;
    }
    /**
     * @Description: 获取当前项目的绝对路径
     * @Date: 2022/4/7

     **/
    public static String getClassPath(){
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()){
            path = new File("");
        }
        return path.getAbsolutePath();
    }
    /**
     * @Description: 隐藏字符串中间部分，只展示首尾
     * @Date: 2022/4/7
     * @Param str:
     **/
    public static String getHiddenMiddleStr(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        String s = str.charAt(0)+"**";
        if (str.length()>1){
            s = s+str.charAt(str.length()-1);
        }
        return s;
    }
}
