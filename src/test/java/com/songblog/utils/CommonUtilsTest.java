package com.songblog.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {


    @Test
    void getUploadFilePath() {
    }

    @Test
    void getSqlServerDate() {
    }

    @Test
    void checkAgentIsMobile() {
    }

    @Test
    void isImageSuffix() {
    }

    @Test
    void isImage() {
//        Assert.assertTrue(CommonUtils.isImage("C:/Users/song/IdeaProjects/wxk1991/src/main/java/com/wxk1991/utils/CommonUtils.java"));
        Assert.assertTrue(CommonUtils.isImage("C:/Users/song/Pictures/Camera Roll/123.png"));
    }

    @Test
    void testIsImage() {
    }

    @Test
    void isImagePixel() {
        Assert.assertTrue(CommonUtils.isImagePixel(743,323,new File("C:/Users/song/Pictures/Camera Roll/123.png")));
    }

    @Test
    void getIpAddr() {
    }

    @Test
    void getCaptcha() {
        CommonUtils.getCaptcha();
    }

    @Test
    void getFileName() {
        System.out.println(CommonUtils.getFileName("123.txt"));
    }

    @Test
    void getClassPath() {
        System.out.println(CommonUtils.getClassPath());
    }

    @Test
    void getHiddenMiddleStr() {
        System.out.println(CommonUtils.getHiddenMiddleStr("1"));
        System.out.println(CommonUtils.getHiddenMiddleStr("12"));
        System.out.println(CommonUtils.getHiddenMiddleStr("12345"));
    }
}