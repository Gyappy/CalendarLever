package com.mogujie.lever.calendarlever.impl;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mogujie.android.dispatchqueue.DispatchUtil;
import com.mogujie.android.dispatchqueue.queue.GlobalQueuePriority;
import com.mogujie.lever.calendarlever.ICallBack;
import com.mogujie.lever.calendarlever.core.CalendarBuilder;

/**
 * Created by chenhuigu on 17/2/27.
 */

public class MGNonBlockCalendar extends BaseCalendar {
    private volatile static MGNonBlockCalendar mInstance;

    private MGNonBlockCalendar() {

    }

    public static MGNonBlockCalendar getInstance() {
        if (mInstance == null) {
            synchronized (NonBlockCalendar.class) {
                if (mInstance == null) {
                    mInstance = new MGNonBlockCalendar();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void reverse(final CalendarBuilder obj, final ICallBack callBack) {
        DispatchUtil.getGlobalQueue(GlobalQueuePriority.DEFAULT).async(new Runnable() {
            @Override
            public void run() {
                MGNonBlockCalendar.super.reverse(obj, callBack);
            }
        });
    }

    @Override
    public void insert(final CalendarBuilder obj, final ICallBack callBack) {
        DispatchUtil.getGlobalQueue(GlobalQueuePriority.DEFAULT).async(new Runnable() {
            @Override
            public void run() {
                MGNonBlockCalendar.super.insert(obj, callBack);
            }
        });
    }

    @Override
    public boolean query(final CalendarBuilder obj, final ICallBack callBack) {
        DispatchUtil.getGlobalQueue(GlobalQueuePriority.DEFAULT).async(new Runnable() {
            @Override
            public void run() {
                MGNonBlockCalendar.super.query(obj, callBack);

            }
        });
        return true;
    }

    @Override
    public void delete(final CalendarBuilder obj, final ICallBack callBack) {
        DispatchUtil.getGlobalQueue(GlobalQueuePriority.DEFAULT).async(new Runnable() {
            @Override
            public void run() {
                MGNonBlockCalendar.super.delete(obj, callBack);
            }
        });
    }

    @Override
    public void callBackSuccess(@NonNull final Context context, @NonNull final ICallBack callback) {
        DispatchUtil.getMainQueue().async(new Runnable() {
            @Override
            public void run() {
                MGNonBlockCalendar.super.callBackSuccess(context, callback);
            }
        });
    }

    @Override
    public void callBackFailed(@NonNull final Context context, @NonNull final ICallBack callback) {
        DispatchUtil.getMainQueue().async(new Runnable() {
            @Override
            public void run() {
                MGNonBlockCalendar.super.callBackFailed(context, callback);
            }
        });
    }
}
