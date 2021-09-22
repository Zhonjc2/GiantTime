package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Timer {
    public long aim;
    public long gap;
    public long sGap;
    public Timer(String aimDate) throws TimeOutException{
        aim= getMillis(aimDate);
        gap= aim-System.currentTimeMillis();
        sGap=gap/1000;
        System.out.println("GAP:"+gap);
        if(gap<=0)throw new TimeOutException(gap);
    }
    //通过日期字符串获得毫秒数
    public static long getMillis(String date){
//        System.out.println(date);
        Calendar aimCal=Calendar.getInstance();
        try {
            aimCal.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(date));
        }catch(ParseException e){}
        return aimCal.getTimeInMillis();
    }
    //通过毫秒数获得日期字符串（距离19700101）
    public static String getFormatDate(long millis){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(millis);
    }
    public long getGapSeconds(){
        return  sGap;
    }
    public long getGapMinutes(){
        return  sGap /60;
    }
    public long getGapHours(){
        return  sGap /3600;
    }
    public long getGapDays(){
        return sGap /(3600*24);
    }
    public static void main(String[] args) throws TimeOutException{
        /*System.out.println(getMillis("19700601010000"));
        System.out.println(getFormatDate(1631638800000L));//long 值最后必须加l*/
        Timer a=new Timer("20920815000000");
        System.out.println(a.getGapSeconds());
    }
}
