package com.qpp.utils.operation;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author qipengpai
 * @Title: TextFormatUtil
 * @ProjectName bound
 * @Description: TODO
 * @date 16:03 2018/10/11
 */
public class TextFormatUtil {
    public static final String NUMERAL = ",##0.00";

    public static final String NUMERAL_CAPITAL = "¤,##0.00";

    public static final String NUMERAL_PERCENT = "#0.00%";

    public static final String DATE = "yyyy-MM-dd";

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    //接口传值使用
    public static final String YYYYMMDD = "yyyyMMdd";


    /**
     * @Author qipengpai
     * @Description //TODO 数字格式转换
     * @Date 16:05 2018/10/11
     * @Param [num, format, rm]
     * @param num 被转换数据对象，类型包括 Long、Integer、Short、Byte、AtomicInteger、AtomicLong、BigInteger、BigDecimal、BigInteger、Number、Double、Float
     * @param format 转换文本格式
     * @param rm 小数舍入方式
     * @Throws
     * @return java.lang.String
     **/
    public static String numberFormat(Object num, String format, RoundingMode rm) {
        if(num != null) {
            DecimalFormat df = new DecimalFormat(format, DecimalFormatSymbols.getInstance(Locale.CHINA));
            df.setRoundingMode(rm);
            return df.format(num);
        } else {
            return "";
        }
    }


    /**
     * @Author qipengpai
     * @Description //TODO 转换成三位一段格式，例如21,100.00
     * @Date 16:07 2018/10/11
     * @Param [d]
     * @Throws
     * @return java.lang.String
     **/
    public static String numberFormat(Object d) {
        return numberFormat(d, NUMERAL, RoundingMode.HALF_UP);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 转换成金融货币格式，例如￥21,100.00
     * @Date 16:08 2018/10/11
     * @Param [d]
     * @Throws
     * @return java.lang.String
     **/
    public static String getCAPITALText(Object d) {
        return numberFormat(d, NUMERAL_CAPITAL, RoundingMode.HALF_UP);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 转换成百分比格式，例如0.23，转换后成23.00%
     * @Date 16:08 2018/10/11
     * @Param [d]
     * @Throws
     * @return java.lang.String
     **/
    public static String getPERCENTText(Object d) {
        return numberFormat(d, NUMERAL_PERCENT, RoundingMode.HALF_UP);
    }

    /**
     * 转换时间格式
     *
     * @serialData 2015-7-15
     *
     * @param d 被转换时间对象
     * @param format 时间格式
     *
     * @return 转换后文本
     *
     * */
    public static String dateFormat(Date d, String format) {
        if(d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(d);
        } else {
            return "";
        }
    }

    /**
     * 转换时间格式为 yyyy-MM-dd HH:mm:ss
     *
     * @serialData 2015-7-15
     *
     * @param d 被转换时间对象
     *
     * @return 转换后文本
     *
     * */
    public static String timeFormatText(Date d) {
        return dateFormat(d, DATE_TIME);
    }

    /**
     * 转换时间格式为 yyyy-MM-dd
     *
     * @serialData 2015-7-15
     *
     * @param d 被转换时间对象
     *
     * @return 转换后文本
     *
     * */
    public static String dateFormatText(Date d) {
        return dateFormat(d, DATE);
    }

    /**
     * 转换时间格式为 yyyyMMdd
     *
     * @serialData 2015-7-15
     *
     * @param d 被转换时间对象
     *
     * @return 转换后文本
     *
     * */
    public static String ymdFormatText(Date d) {
        return dateFormat(d, YYYYMMDD);
    }


    /**
     * @Author qipengpai
     * @Description //TODO 银行卡显示格式
     * @Date 16:09 2018/10/11
     * @Param [bankCardStr] bankCardStr 原始卡号
     * @Throws
     * @return java.lang.String
     **/
    public static String bankCardText(String bankCardStr) {
        if(bankCardStr != null) {
            StringBuffer bcs = new StringBuffer(bankCardStr);
            if(bankCardStr.length() == 16) {
                bcs.insert(12, " ");
                bcs.insert(8, " ");
                bcs.insert(4, " ");
            } else if(bankCardStr.length() == 19) {
                bcs.insert(6, " ");
            }
            return bcs.toString();
        } else {
            return "";
        }
    }

}
