package com.mogujie.lever.calendarlever.core.impl;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mogujie.lever.calendarlever.core.ICallBack;
import com.mogujie.lever.calendarlever.core.other.CalendarBuilder;

/**
 * Created by chenhuigu on 17/2/26.
 */

public class NonBlockCalendar extends BaseCalendar {

    private NonBlockCalendar() {

    }

    public static NonBlockCalendar newInstance() {
        return new NonBlockCalendar();
    }

    @Override
    public void reverse(final CalendarBuilder obj, final ICallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.reverse(obj, callBack);
            }
        }).start();
    }

    @Override
    public void insert(final CalendarBuilder obj, final ICallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.insert(obj, callBack);
            }
        }).start();
    }

    @Override
    public boolean query(final CalendarBuilder obj, final ICallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.query(obj, callBack);
            }
        }).start();
        return true;
    }

    @Override
    public void delete(final CalendarBuilder obj, final ICallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.delete(obj, callBack);
            }
        }).start();
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
