package com.qpp.utils.operation;

import java.util.UUID;

/**
 * @author qipengpai
 * @Title: UuidUtil
 * @ProjectName bound
 * @Description: TODO 获取UUID
 * @date 17:35 2018/10/11
 */
public class UuidUtil {
    // public static void main(String[] args) {
    // for (int i = 0; i < 10; i++) {
    // String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    // System.out.println(uuid);
    // }
    // }
    public static void main(String[] args) {
        for (int i = 0; i <5; i++) {
            String uuid = getUUID();
            System.out.println(uuid);
        }

    }

    /**
     * @Author qipengpai
     * @Description //TODO 获得指定数目的UUID
     * @Date 17:36 2018/10/11
     * @Param [number]
     * @Throws
     * @return java.lang.String[]
     **/
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 获得一个UUID
     * @Date 17:36 2018/10/11
     * @Param []
     * @Throws
     * @return java.lang.String
     **/
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        // 去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
