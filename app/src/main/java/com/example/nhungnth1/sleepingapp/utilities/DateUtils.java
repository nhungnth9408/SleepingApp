package com.example.nhungnth1.sleepingapp.utilities;

import com.example.nhungnth1.sleepingapp.common.AppConst;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {
    private static DateFormat mYearMonthFormat = new SimpleDateFormat("yyyy/MM", Locale.getDefault());
    private static DateFormat mYearMonthDayFormat = new SimpleDateFormat(AppConst.FORMAT_YY_MM_DD, Locale.getDefault());
    public static DateFormat mHourFormat = new SimpleDateFormat(AppConst.FORMAT_HH_MM, Locale.getDefault());
    private static DateFormat mTime = new SimpleDateFormat(AppConst.FORMAT_TIME_FULL, Locale.getDefault());
}
