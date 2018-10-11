package com.qpp.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qipengpai
 * @Title: DateUtils
 * @ProjectName bound
 * @Description: TODO （17年 时间操作类）
 * @date 17:50 2018/10/11
 */
public class DateUtils {

    public static final String YMD = "yyyyMMdd";
    public static final String YMD_YEAR = "yyyy";
    public static final String YMD_BREAK = "yyyy-MM-dd";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String YMDHMS_BREAK = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS_BREAK_HALF = "yyyy-MM-dd HH:mm";
    public static final String PP = "YYYY年MM月dd日";
    /**
     * 计算时间差
     */
    public static final int CAL_MINUTES = 1000 * 60;
    public static final int CAL_HOURS = 1000 * 60 * 60;
    public static final int CAL_DAYS = 1000 * 60 * 60 * 24;

    /**
     * @Author qipengpai
     * @Description //TODO 获取当前时间格式化后的值
     * @Date 17:51 2018/10/11
     * @Param [pattern]
     * @Throws
     * @return java.lang.String
     **/
    public static String getNowDateText(String pattern){
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * @Author qipengpai
     * @Description //TODO 获取日期格式化后的值
     * @Date 17:55 2018/10/11
     * @Param [date, pattern]
     * @Throws
     * @return java.lang.String
     **/
    public static String getDateText(Date date, String pattern){
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 字符串时间转换成Date格式
     * @Date 17:55 2018/10/11
     * @Param [date, pattern]
     * @Throws
     * @return java.util.Date
     **/
    public static Date getDate(String date, String pattern) throws ParseException{
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    private static SimpleDateFormat getSimpleDateFormat(String pattern){
        return new SimpleDateFormat(pattern);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 获取时间戳
     * @Date 17:51 2018/10/11
     * @Param [date]
     * @Throws
     * @return java.lang.Long
     **/
    public static Long getTime(Date date){
        return date.getTime();
    }

    /**
     * @Author qipengpai
     * @Description //TODO 计算时间差
     * @Date 17:51 2018/10/11
     * @Param [startDate, endDate, calType]
     * @param startDate
     * @param endDate
     * @param calType 计算类型,按分钟、小时、天数计算
     * @Throws
     * @return int
     **/
    public static int calDiffs(Date startDate, Date endDate, int calType){
        Long start = getTime(startDate);
        Long end = getTime(endDate);
        int diff = (int) ((end - start)/calType);
        return diff;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 计算时间差值以某种约定形式显示
     * @Date 17:55 2018/10/11
     * @Param [startDate, endDate]
     * @Throws
     * @return java.lang.String
     **/
    public static String timeDiffText(Date startDate, Date endDate){
        int calDiffs = DateUtils.calDiffs(startDate, endDate, DateUtils.CAL_MINUTES);
        if(calDiffs == 0){
            return "刚刚";
        }
        if(calDiffs < 60){
            return calDiffs + "分钟前";
        }
        calDiffs = DateUtils.calDiffs(startDate, endDate, DateUtils.CAL_HOURS);
        if(calDiffs < 24){
            return calDiffs + "小时前";
        }
        if(calDiffs < 48){
            return "昨天";
        }
        return DateUtils.getDateText(startDate, DateUtils.YMDHMS_BREAK_HALF);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 显示某种约定后的时间值,类似微信朋友圈发布说说显示的时间那种
     * @Date 17:55 2018/10/11
     * @Param [date]
     * @Throws
     * @return java.lang.String
     **/
    public static String showTimeText(Date date){
        return timeDiffText(date, new Date());
    }

    public static void main(String[] args) throws ParseException {
        Date start = getDate("2017-11-20 19:11:12", DateUtils.YMDHMS_BREAK_HALF);
        System.out.println(DateUtils.showTimeText(start));
    }
}
