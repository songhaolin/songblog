package com.songblog.constant;

/**
 * @Description:
 * @Author: shl
 * @Date: 2022/4/6
 **/
public class RegexConstant {
    public static final String EMAIL_REGEX= "\\w+@\\w+\\.[a-z]+(\\.\\w+)?";
    public static final String MOBILE_PHONE_REGEX = "(\\+\\d+)?1[34578]\\d{9}$";
    public static final String TELE_PHONE_REGEX = "(\\+\\d+)?(\\d{3,4}\\-)?\\d{7,8}$";
    public static final String ID_CARD_REGEX = "[1-9]\\d{13,16}[A-Za-z0-9]{1}$";
    public static final String DIGIT_REGEX = "\\-?\\[1-9]\\d+";
    public static final String DECIMALS_REGEX = "\\-?\\d+(\\.\\d+)";
    public static final String BLANK_SPACE_REGEX = "\\s+";
    public static final String CHINESE_REGEX = "^[\u4E00-\u9FA5]+$";
    public static final String BIRTHDAY_REGEX = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";


}
