package com.songblog.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


/**
 * @Description:
 * @Author: shl
 * @Date: 2022/4/6
 **/
public class RegexUtilsTest {

    @Test
    void checkBirthday() {
        Assert.assertTrue(RegexUtils.checkBirthday("1995-8.13"));
    }

    @Test
    void checkEmail() {
    }

    @Test
    void checkIdCard() {
    }

    @Test
    void checkMobile() {
    }

    @Test
    void checkPhone() {
    }

    @Test
    void checkDigit() {
        Assert.assertTrue(RegexUtils.checkDigit("-23"));
    }

    @Test
    void checkDecimals() {
    }

    @Test
    void checkBlankSpace() {
    }

    @Test
    void checkChinese() {
    }

    @Test
    void testCheckBirthday() {
    }

    @Test
    void checkURL() {
    }

    @Test
    void getDomain() {
    }

    @Test
    void checkPostcode() {
    }

    @Test
    void checkIpAddress() {
    }
}
