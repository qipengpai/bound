package com.qpp.utils.operation;

/**
 * @author qipengpai
 * @Title: HexUtil
 * @ProjectName bound
 * @Description: TODO
 * @date 11:50 2018/10/11
 */
public class HexUtil {

    private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * @Author qipengpai
     * @Description //TODO bytes数组转十六进制字符串
     * @Date 11:56 2018/10/11
     * @Param [bytes]
     * @Throws
     * @return char[]
     **/
    public static char[] encode(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];

        int j = 0;
        for (int i = 0; i < nBytes; i++) {
            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
        }

        return result;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 十六进制字符串转bytes数组
     * @Date 11:56 2018/10/11
     * @Param [s]
     * @Throws
     * @return byte[]
     **/
    public static byte[] decode(CharSequence s) {
        int nChars = s.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException(
                    "Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];

        for (int i = 0; i < nChars; i += 2) {
            int msb = Character.digit(s.charAt(i), 16);
            int lsb = Character.digit(s.charAt(i + 1), 16);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException(
                        "Non-hex character in input: " + s);
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;
    }

    /**
     * 1.字符串转Byte数组
     * “字符串”.getBytes(“编码格式”)；
     *
     * 2.Byte数组转字符
     * String s=new String(byte数组，“编码格式”)；
     *
     * 3.String类的toCharArray()方法，将字符串转为字符(char)数组
     *     String ss=”abc”;
     *     char[] cc;
     *     cc=ss.toCharArray();
     *     这时cc={‘a’,’b’,’c’}；
     *
     * 4.String类的valueOf()方法，将字符(char)数组转换为字符串
     *     char[] cc={‘a’,’b’,’c’};
     *     ss=String.valueOf(cc);
     *     这时ss=“abc”；
     **/

}
