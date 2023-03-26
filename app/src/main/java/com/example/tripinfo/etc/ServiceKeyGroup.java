package com.example.tripinfo.etc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ServiceKeyGroup {
    static String enc = "UTF-8";
    static String mainKey;
    static String noticeKey;
    static String imKey;
    static String warningKey;
    static String warningAdjustKey;
    static String telKey;
    static String specialWarningKey;
    static String safetyInfoKey;
    static String safetyNoticeKey;
    static String accidentKey;

    static {
        try {
            mainKey = URLDecoder.decode("", enc);
            noticeKey = URLDecoder.decode("", enc);
            imKey = URLDecoder.decode("", enc);
            warningKey = URLDecoder.decode("", enc);
            warningAdjustKey = URLDecoder.decode("", enc);
            telKey = URLDecoder.decode("", enc);
            specialWarningKey = URLDecoder.decode("", enc);
            safetyInfoKey = URLDecoder.decode("", enc);
            safetyNoticeKey = URLDecoder.decode("", enc);
            accidentKey = URLDecoder.decode("", enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String getMainKey() { return mainKey; }
    public static String getNoticeKey() { return noticeKey; }
    public static String getImKey() { return imKey; }
    public static String getWarningKey() { return warningKey; }
    public static String getWarningAdjustKey() { return warningAdjustKey; }
    public static String getTelKey() { return telKey; }
    public static String getSpecialWarningKey() { return specialWarningKey; }
    public static String getSafetyInfoKey() { return safetyInfoKey; }
    public static String getSafetyNoticeKey() { return safetyNoticeKey; }
    public static String getAccidentKey() { return accidentKey; }
}
