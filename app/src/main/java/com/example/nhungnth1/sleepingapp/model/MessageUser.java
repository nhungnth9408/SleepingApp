package com.example.nhungnth1.sleepingapp.model;

import android.provider.SyncStateContract;

import com.example.nhungnth1.sleepingapp.common.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MessageUser {
    private String mText;
    private String mSender;
    private Date mDate;
    private String mDateString;

    public MessageUser(String mText, String mSender, Date mDate) {
        this.mText = mText;
        this.mSender = mSender;
        this.mDate = mDate;
    }

    public MessageUser() {

    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        this.mSender = sender;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public MessageUser(String mText, String mSender) {
        this.mText = mText;
        this.mSender = mSender;
    }


    public String getDateString() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.GMT_TIME));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat(Constants.FORMAT_TIME);
        date.setTimeZone(TimeZone.getTimeZone(Constants.GMT_TIME));
        String localTime = date.format(currentLocalTime);
        return localTime;
    }

    public void setDateString(String mDateString) {
        this.mDateString = mDateString;
    }
}
