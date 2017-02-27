package com.mogujie.lever.calendarlever.core;

import com.mogujie.lever.calendarlever.ICalendar;
import com.mogujie.lever.calendarlever.ICallBack;
import com.mogujie.lever.calendarlever.impl.DefaultCalendar;
import com.mogujie.lever.calendarlever.impl.MGNonBlockCalendar;
import com.mogujie.lever.calendarlever.impl.NonBlockCalendar;

/**
 * Created by chenhuigu on 17/2/27.
 */

public class CalendarOperator {
    private volatile static CalendarOperator mInstance;
    //default
    private ICalendar CalendarUtils = DefaultCalendar.getInstance();

    private CalendarOperator() {

    }

    public static CalendarOperator getInstance() {
        if (mInstance == null) {
            synchronized (CalendarOperator.class) {
                if (mInstance == null) {
                    mInstance = new CalendarOperator();
                }
            }
        }
        return mInstance;
    }

    public CalendarOperator getBlockEngine() {
        CalendarUtils = DefaultCalendar.getInstance();
        return this;
    }

    public CalendarOperator getNonBlockEngine() {
        CalendarUtils = NonBlockCalendar.getInstance();
//        CalendarUtils = MGNonBlockCalendar.getInstance();
        return this;
    }

    public void addEvent(CalendarBuilder obj, ICallBack callBack) {
        CalendarUtils.insert(obj, callBack);
    }

    public void queryEvent(CalendarBuilder obj, ICallBack callBack) {
        CalendarUtils.query(obj, callBack);
    }

    public void deleteEvent(CalendarBuilder obj, ICallBack callBack) {
        CalendarUtils.delete(obj, callBack);
    }

    public void reverseEvent(CalendarBuilder obj, ICallBack callBack) {
        CalendarUtils.reverse(obj, callBack);
    }
}
