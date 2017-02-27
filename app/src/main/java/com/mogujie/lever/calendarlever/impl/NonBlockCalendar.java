package com.mogujie.lever.calendarlever.impl;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mogujie.lever.calendarlever.core.CalendarBuilder;
import com.mogujie.lever.calendarlever.ICallBack;

/**
 * Created by chenhuigu on 17/2/26.
 */

public class NonBlockCalendar extends BaseCalendar {

    private volatile static NonBlockCalendar mInstance;

    private NonBlockCalendar() {

    }

    public static NonBlockCalendar getInstance() {
        if (mInstance == null) {
            synchronized (NonBlockCalendar.class) {
                if (mInstance == null) {
                    mInstance = new NonBlockCalendar();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void reverse(final CalendarBuilder obj, final ICallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.reverse(obj, callBack);
            }
        }).run();
    }

    @Override
    public void insert(final CalendarBuilder obj, final ICallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.insert(obj, callBack);
            }
        }).run();
    }

    @Override
    public boolean query(final CalendarBuilder obj, final ICallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (NonBlockCalendar.super.query(obj, callBack)) {
                    callBackSuccess(obj.getContext(), callBack);
                } else {
                    callBackFailed(obj.getContext(), callBack);
                }
            }
        }).run();
        return true;
    }

    @Override
    public void delete(final CalendarBuilder obj, final ICallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.delete(obj, callBack);
            }
        }).run();
    }

    @Override
    public void callBackSuccess(@NonNull final Context context, @NonNull final ICallBack callback) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NonBlockCalendar.super.callBackSuccess(context, callback);
                }
            });
        }
    }

    @Override
    public void callBackFailed(@NonNull final Context context, @NonNull final ICallBack callback) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NonBlockCalendar.super.callBackFailed(context, callback);
                }
            });
        }
    }
}
