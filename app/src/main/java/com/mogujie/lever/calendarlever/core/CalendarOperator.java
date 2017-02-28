package com.mogujie.lever.calendarlever.core;


import com.mogujie.lever.calendarlever.core.impl.DefaultCalendar;
import com.mogujie.lever.calendarlever.core.impl.NonBlockCalendar;

/**
 * Created by ouyi on 17/2/27.
 */

public class CalendarOperator {
    public static ICalendar getBlockEngine() {
        return DefaultCalendar.newInstance();
    }

    public static ICalendar getNonBlockEngine() {
        return NonBlockCalendar.newInstance();
    }
}
