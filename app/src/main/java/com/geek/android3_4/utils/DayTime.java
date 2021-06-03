package com.geek.android3_4.utils;

import android.content.Context;
import android.text.format.DateUtils;

public class DayTime {
    public static String getDayTime(Context context,Long sunrise, Long sunset){
        long diff = sunrise*1000 - sunset*1000;
        return DateUtils.formatDateTime(context,diff,DateUtils.FORMAT_SHOW_TIME);
    }
    public static String getTime(Context context,Long date){
        return DateUtils.formatDateTime(context,date*1000,DateUtils.FORMAT_SHOW_TIME);
    }

    public static String getDate(Context context, Long dt) {
        return DateUtils.formatDateTime(context,dt*1000,DateUtils.FORMAT_SHOW_WEEKDAY|DateUtils.FORMAT_SHOW_TIME|DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_ABBREV_MONTH);
    }
}
