package com.qpp.utils.operation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author qipengpai
 * @Title: MD5Util
 * @ProjectName bound
 * @Description: TODO MD5加密
 * @date 13:49 2018/10/11
 */
public class MD5Util {
    private static final String SALT = "HXWcjvQWVG1wI4FQBLZpQ3pWj48AV63d";

    /**
     * @Author qipengpai
     * @Description //TODO 加盐加密
     * @Date 14:14 2018/10/11
     * @Param [password]
     * @Throws
     * @return java.lang.String
     **/
    public static String encode(String password) {
        return processEncode(password);
    }

    public static String processEncode(String password) {
        return callDigest(mergePasswordAndSalt(password, SALT));
    }


    private static String callDigest(String saltedPass) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("md5");
            byte[] digest = messageDigest.digest(UTF8Util.encode(saltedPass));
            return new String(HexUtil.encode(digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 直接加密
     * @Date 14:15 2018/10/11
     * @Param [password]
     * @Throws
     * @return java.lang.String
     **/
    public static String encrypt32(String password) {
        return callDigest(password);
    }

    /***
     * @Author qipengpai
     * @Description //TODO 截取加密后8到24位
     * @Date 14:15 2018/10/11
     * @Param [password]
     * @Throws
     * @return java.lang.String
     **/
    public static String encrypt16(String password) {
        String mw = encrypt32(password);
        if (mw != null && mw.length() > 24) {
            return mw.substring(8, 24);
        } else {
            return mw;
        }
    }

    /**
     * @Author qipengpai
     * @Description //TODO 融合密码加盐
     * @Date 14:09 2018/10/11
     * @Param [password, salt]
     * @Throws
     * @return java.lang.String
     **/
    public static String mergePasswordAndSalt(String password, Object salt) {
        if (password == null) {
            password = "";
        }
        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encrypt16("yszyzh"));
    }

}
