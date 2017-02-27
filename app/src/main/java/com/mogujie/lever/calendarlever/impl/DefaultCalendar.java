package com.mogujie.lever.calendarlever.impl;

/**
 * Created by chenhuigu on 17/2/27.
 */

public class DefaultCalendar extends BaseCalendar {
    private volatile static DefaultCalendar mInstance;

    private DefaultCalendar() {

    }

    public static DefaultCalendar getInstance() {
        if (mInstance == null) {
            synchronized (DefaultCalendar.class) {
                if (mInstance == null) {
                    mInstance = new DefaultCalendar();
                }
            }
        }
        return mInstance;
    }
}
