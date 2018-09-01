package com.k.seckill.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static final String salt = "springboot";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }


    /**
     * 对应第一次前端加密（加盐）
     * @param inputPass
     * @return
     */
    public static String inputToForm(String inputPass) {
        String str = inputPass + salt;
        return md5(str);

    }

    /**
     * 第二次加密
     * @param formPass
     * @param dbSalt
     * @return
     */
    public static String formToDB(String formPass, String dbSalt) {
        String str = dbSalt + formPass;
        return md5(str);
    }


    public static String inputToDB(String inputPass, String dbSalt) {
        String formPass = inputToForm(inputPass);
        String dbPass = formToDB(formPass, dbSalt);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputToDB("123456", "alex1"));

    }





}
