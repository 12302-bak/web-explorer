package com.elizabeth.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileUtil {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        float price = 89.89f;
        int Num=3;
        float totalPrice=price*Num;
        float num=(float)(Math.round(totalPrice*100))/100;
        System.out.println(num);

        System.out.println((float)(5120 * 100 /1024)/100);
        System.out.println((float) (5126 * 100 )/2014);
        System.out.println((float)(Math.round((float) (5126 * 100 )/2014))/100);


        System.out.println(String.format("%.4f", 3.1));
    }



    public static String toHumanReadable(long size){
        String result;
        if(size <= 0) return "0B";
        if(0 < size && size < 1024){
            result = size + "B";
        }else if(1024 <= size && size < 1048576L){
            result = (float)(Math.round((float) (size * 100 ) / 1024) ) / 100 + "K";
        }else if(1048576L <= size && size < 1073741824L){
            result = (float)(Math.round((float) (size * 100 ) / 1024 / 1024 ) ) / 100 + "M";
        }else if(1073741824L <= size && size < 1099511627776L){
            result = (float)(Math.round((float) (size * 100 ) / 1024 / 1024 / 1024 ) ) / 100 + "G";
        }else {
            result = size + "";
        }
        return result;
    }

    public static String getModifiedTime(long lastTime){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(lastTime);
        return formatter.format(cal.getTime());
    }


}
