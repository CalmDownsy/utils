package dateutils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhangsy
 * @date 2017/12/28
 */
public class DateUtils {

    /**
     * 日期加减
     *
     * @param date  指定日期
     * @param days  天数
     * @param hours 小时数
     * @param mins  分钟数
     * @param secs  描述
     * @return 计算后时间
     */
    public static Date calculateDate(Date date, int days, int hours, int mins, int secs) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        calendar.add(Calendar.HOUR, hours);
        calendar.add(Calendar.MINUTE, mins);
        calendar.add(Calendar.SECOND, secs);
        return calendar.getTime();
    }

    /**
     * 按天数日期加减
     *
     * @param date 指定日期
     * @param days 天数
     * @return 计算后时间
     */
    public static Date calculateByDays(Date date, int days) {
        return calculateDate(date, days, 0, 0, 0);
    }

    /**
     * 按小时数日期加减
     *
     * @param date  指定日期
     * @param hours 小时数
     * @return 计算后时间
     */
    public static Date calculateByHours(Date date, int hours) {
        return calculateDate(date, 0, hours, 0, 0);
    }

    /**
     * 按分钟数日期加减
     *
     * @param date 指定日期
     * @param mins 分钟数
     * @return 计算后时间
     */
    public static Date calculateByMins(Date date, int mins) {
        return calculateDate(date, 0, 0, mins, 0);
    }

    /**
     * 按秒数日期加减
     *
     * @param date 指定日期
     * @param secs 秒数
     * @return 计算后时间
     */
    public static Date calculateBySecs(Date date, int secs) {
        return calculateDate(date, 0, 0, 0, secs);
    }

    /**
     * 计算两日期相聚天数
     *
     * @param dateFir firstDay
     * @param dateSec secondDay
     * @return *days*hours*mins*secs
     */
    public static String calculateTwoDistances(Date dateFir, Date dateSec) {
        //获取日志时间戳
        long firTime = dateFir.getTime();
        long secTime = dateSec.getTime();
        //计算差值 毫秒
        long diff = firTime >= secTime ? firTime - secTime : secTime - firTime;
        //差距天数
        long days = diff / (1000 * 60 * 60 * 24);
        //差距小时
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        //差距分钟
        long mins = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        long secs = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - mins * (1000 * 60)) / (1000 * 60);
        return "[" + days + "]days[" + hours + "]hours[" + mins + "]mins[" + secs + "]secs";
    }
}
