package com.qpp.utils.common;

/**
 * @author qipengpai
 * @Title: GenerateIdUtil
 * @ProjectName bound
 * @Description: TODO 主键生成类
 * @date 15:25 2018/10/11
 */
public class GenerateIdUtil {

    private static long[] ls = new long[3000];

    private static int li = 0;

    /**
     * @Author qipengpai
     * @Description //TODO getPK,获得数据库使用的一个long型唯一主键 16位，同一微秒内3000个不会重复
     * @Date 15:26 2018/10/11
     * @Param []
     * @Throws
     * @return java.lang.String
     **/
    public synchronized static String getPK() {
        long lo = getpk();
        for (int i = 0; i < 3000; i++)
        {
            long lt = ls[i];
            if (lt == lo)
            {
                String loStr = getPK();
                lo = Long.valueOf(loStr);
                break;
            }
        }
        ls[li] = lo;
        li++;
        if (li == 3000)
        {
            li = 0;
        }
        return String.valueOf(lo);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 系统时间 3-13位
     * @Date 15:28 2018/10/11
     * @Param []
     * @Throws
     * @return long
     **/
    private static long getpk() {
        String a = (String.valueOf(System.currentTimeMillis())).substring(3, 13);
        String d = (String.valueOf(Math.random())).substring(2, 8);
        return Long.parseLong(a + d);
    }

    public static void main(String[] args){
        String des = "2&5&3";
        String[] info = des.split("&");
        String detailDes = "";
        String thirdCode = "";
        for (int i = 0; i < info.length; i++) {
            String desInfo = info[i].replace(" ", "");;
            if(i==0){
                detailDes=desInfo;
            }else if(i==1){
                thirdCode=desInfo;
            }
        }
        System.out.println(detailDes);
        System.out.println(thirdCode);
        System.out.println(getPK());
        System.out.println(getPK());
    }

}
