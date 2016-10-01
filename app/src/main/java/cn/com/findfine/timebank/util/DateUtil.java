package cn.com.findfine.timebank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangchen on 16/9/11.
 */
public class DateUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取格式化后的时间
     * @param millis
     * @return
     */
    public static String getTimeByMillis(long millis) {
        return sdf.format(new Date(millis));
    }

    public static long getMillisByTime(String time) throws ParseException {
        return sdf.parse(time).getTime();
    }

    /**
     * 通过毫秒获取剩余的天数
     * @param targetMillis
     * @return
     */
    public static String getTimeLeftByTargetMillis(long targetMillis) {
        long currentTime = System.currentTimeMillis();
        if (targetMillis > currentTime) {
            long time = targetMillis - currentTime;
            int hour = (int) (time / 1000 / 60 / 60);
            if (hour >= 24) {
                int day = hour / 24;
                return day + " 天";
            }

            if (hour > 0) {
                return hour + " 小时";
            }
        }
        return 0 + " 天";
    }

    /**
     * 通过毫秒获取剩余的天数
     * @param targetMillis
     * @return
     */
    public static int getDayLeftByTargetMillis(long targetMillis) {
        long currentTime = System.currentTimeMillis();
        if (targetMillis > currentTime) {
            long time = targetMillis - currentTime;
            int hour = (int) (time / 1000 / 60 / 60);
            if (hour > 24) {
                int day = hour / 24;
                return day + 1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 获取今天零点毫秒时间
     * @return
     */
    public static long getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }

}
