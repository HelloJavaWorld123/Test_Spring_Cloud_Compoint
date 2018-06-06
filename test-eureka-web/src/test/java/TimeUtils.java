import org.omg.DynamicAny._DynAnyFactoryStub;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/27  10:31
 * Version: V1.0
 * Description: 时间测试
 * ======================
 */
public class TimeUtils {

    public static void main(String[] args){

       /* String weekOfYear = getWeekOfYear(new Date());
        System.out.println("当前日期属于第"+weekOfYear+"周");

        String monthOfYear = getMonthOfYear(new Date());
        System.out.println("当前日期属于第"+monthOfYear+"月");

        String dayBegin = getDayBegin(new Date());
        System.out.println("当天开始时间："+dayBegin);

        String dayEnd = getDayEnd(new Date());
        System.out.println("当天结束时间："+dayEnd);

        String yesterDayBegin = getYesterDayBegin(new Date());
        System.out.println("昨天开始时间："+yesterDayBegin);

        String yesterdayEnd = getYesterdayEnd(new Date());
        System.out.println("昨天结束时间："+yesterdayEnd);

        String lastMonthBegin = getLastMonthBegin(new Date());
        System.out.println("上一个月的开始时间："+lastMonthBegin);

        String lastMonthEnd = getLastMonthEnd(new Date());
        System.out.println("上一个月的结束时间："+lastMonthEnd);
*/

//       System.out.println(Calendar.getInstance().getTime());
       System.out.println(BigDecimal.valueOf(10600.22).divide(BigDecimal.valueOf(100),BigDecimal.ROUND_HALF_UP).intValue());


    }


    /**
     * 获取 当前日期 所属 一年的 所属 第几周
     */
    public static String getWeekOfYear(Date date){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        //先将周的第一天设置成 周一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(1);

        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int weakOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

        return year +"-"+ week + "--"+ weakOfMonth;
    }


    /**
     * 获取当前日期 所属 当前年的 第几个月
     * @param date ： 当前日期
     * @return
     */
    public static String getMonthOfYear(Date date){

        Calendar instance = Calendar.getInstance();

        instance.setTime(date);

        int year = instance.get(Calendar.YEAR);

        int month = instance.get(Calendar.MONTH)+1;

        return year + "-" + month ;
    }

    /**
     * 获取给定日期的开始时间
     * @param date ：当前日期
     * @return ：00:00:00
     */
    public static String getDayBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,calendar.getActualMinimum(Calendar.SECOND) );
        calendar.set(Calendar.MILLISECOND,calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime().getTime()+"";
    }

    /**
     * 获取当前时间的结束时间
     * @param date ：当前日期
     * @return ：字符串时间戳
     */
    public static String getDayEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,calendar.getActualMaximum(Calendar.HOUR_OF_DAY) );
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,calendar.getActualMaximum(Calendar.SECOND) );
        calendar.set(Calendar.MILLISECOND,calendar.getActualMaximum(Calendar.MILLISECOND) );
        return calendar.getTime().getTime() + "";
    }

    /**
     * 获取前一天的 开始时间
     * @param date ：当前日期
     * @return ：字符串开始时间
     */
    public static String getYesterDayBegin(Date date){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return getDayBegin(calendar.getTime());
    }

    /**
     * 获取昨天的结束时间
     * @param date：当前日期
     * @return ：结束时间时间戳
     */
    public static String getYesterdayEnd(Date date){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR,-1 );
        return getDayEnd(calendar.getTime());
    }

    /**
     * 上一个月的开始时间
     * @param date ：当前日期
     * @return ：13位时间戳
     */
    public static String getLastMonthBegin(Date date){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-1 );
        calendar.set(Calendar.DAY_OF_MONTH,1 );
        return getDayBegin(calendar.getTime());
    }

    /**
     * 获取上个月最后一天的结束时间
     * @param date ：当前日期
     * @return ：13位时间戳
     */
    public static String getLastMonthEnd(Date date){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-1 );
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH) );
        return getDayEnd(calendar.getTime());
    }



}
