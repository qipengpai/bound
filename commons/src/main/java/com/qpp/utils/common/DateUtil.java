package com.qpp.utils.common;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author qipengpai
 * @Title: DateUtil
 * @ProjectName bound
 * @Description: TODO 当前线程日期处理类（"yyyy-MM-dd HH:mm:ss"）[线程安全]
 * @date 13:50 2018/10/11
 */
public class DateUtil implements AutoCloseable,Serializable{

    private static final long serialVersionUID = 5110771010886130754L;

    public static final String DATE = "yyyy-MM-dd";

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    //接口传值使用
    public static final String YYYYMMDD = "yyyyMMdd";

    //饿汉单例
    public static DateUtil instance = new DateUtil();
    private DateUtil() {
    }

    public static DateUtil getInstance() {
        return instance;
    }
    /**
     * @Author qipengpai
     * @Description //TODO 防序列化（杜绝单例对象被反序列化时重新生成对象）
     * @Date 9:20 2018/10/11
     * @Param []
     * @return java.lang.Object
     **/
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }

    /**
     * @Author qipengpai
     * @Description //TODO SimpleDateFormat线程不安全的类，使用ThreadLocal,
     * TODO 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。
     * TODO 如果对性能要求比较高的情况下，一般推荐使用这种方法。
     * @Date 9:20 2018/10/11
     * @Param
     * @return
     **/
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DATE_TIME);
        }
    };

    /**
     * @Author qipengpai
     * @Description //TODO 格式化String转换为Date
     * @Date 16:13 2018/10/11
     * @Param [dateStr]
     * @Throws
     * @return java.util.Date
     **/
    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 将date日期转换为string
     * @Date 12:49 2018/10/11
     * @Param [date]
     * @Throws
     * @return java.lang.String
     **/
    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 格式化yyyyMMdd字符串为yyyy-MM-dd
     * @Date 16:10 2018/10/11
     * @Param [yyyyMMdd, separator]
     * @param yyyyMMdd 要转换时间文本
     * @param separator 分隔符
     * @Throws
     * @return java.lang.String
     **/
    public static String dateTextformat(String yyyyMMdd, String separator) {
        if (yyyyMMdd != null && yyyyMMdd.length() == YYYYMMDD.length()) {
            if (separator == null) {
                separator = "-";
            }
            String pattern = "{0}" + separator + "{1}" + separator + "{2}";
            return MessageFormat.format(pattern, yyyyMMdd.substring(0, 4),
                    yyyyMMdd.substring(4, 6), yyyyMMdd.substring(6, 8));
        } else {
            return yyyyMMdd;
        }
    }
    @Override
    public void close() throws Exception {
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(dateTextformat("20081010","-"));
        System.out.println(format(new Date()));
        System.out.println(format(parse("2008-10-10 16:19:43")));

    }
}
