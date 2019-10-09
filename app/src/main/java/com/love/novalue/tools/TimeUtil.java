package com.love.novalue.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static Long getZeroTime(){
        Long currentTimestamps=System.currentTimeMillis();
        Long oneDayTimestamps= Long.valueOf(60*60*24*1000);
        return currentTimestamps-(currentTimestamps+60*60*8*1000)%oneDayTimestamps;
    }
    public static Long getTypeTime(int type){
        long typeTime=0;
        switch (type){
            case 0:
                typeTime=10*60*60*1000;
                break;
            case 1:
                typeTime=13*60*60*1000;
                break;
            case 2:
                typeTime=18*60*60*1000;
                break;
            case 3:
                typeTime=21*60*60*1000;
                break;
            case 4:
                typeTime=24*60*60*1000;
                break;
        }
        long endtime=getZeroTime()+typeTime;
        long starttime=System.currentTimeMillis();
        return endtime-starttime;
    }

}
