package com.ihesen.think.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: ihesen on 2016/4/22 14:47
 * email: hesen@ichsy.com
 */
public class PatternUtil {
    // 判断手机格式是否正确
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    // 判断email格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    // 判断email格式是否正确
    public static boolean isPassword(String password) {
        String str = "^[0-9A-Za-z]{6,}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(password);

        return m.matches();
    }
}
