package com.qpp.utils.operation;

/**
 * @author qipengpai
 * @Title: RandomUtil
 * @ProjectName bound
 * @Description: TODO 随机数工具类
 * @date 15:51 2018/10/11
 */
public class RandomUtil {
    /**
     * @Fields mapTable : TODO(用于生成数字随机数)
     */
    private static final char digitTable[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9' };
    /**
     * @Fields mapTable : TODO(用于生成混合随机数)
     */
    private static final char mixTable[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * @Author qipengpai
     * @Description //TODO 获取存数字随机数
     * @Date 15:53 2018/10/11
     * @Param [digit]
     * @Throws
     * @return int
     **/
    public static int getRandomDigit(int digit) {
        String strEnsure = "";
        // 4代表4位验证码,如果要生成更多位的认证码,则加大数值
        for (int i = 0; i < digit; ++i) {
            strEnsure += digitTable[(int) (digitTable.length * Math.random())];
            // 将认证码显示到图象中
        }
        return Integer.valueOf(strEnsure);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 获取字母数字混合随机码
     * @Date 15:53 2018/10/11
     * @Param [digit]
     * @Throws
     * @return java.lang.String
     **/
    public static String getRandomMix(int digit) {
        String strEnsure = "";
        // 4代表4位验证码,如果要生成更多位的认证码,则加大数值
        for (int i = 0; i < digit; ++i) {
            strEnsure += mixTable[(int) (mixTable.length * Math.random())];
            // 将认证码显示到图象中
        }
        return strEnsure;
    }


    public static void main(String[] args) {
        System.out.println(getRandomDigit(6));
        System.out.println(getRandomMix(6));
    }
}
