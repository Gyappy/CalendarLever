package com.mogujie.lever.calendarlever.core.impl;

import android.app.Activity;
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
        mCtx = obj.getContext();
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.query(obj, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        NonBlockCalendar.super.delete(obj, callBack);
                    }

                    @Override
                    public void onFailed() {
                        NonBlockCalendar.super.insert(obj, callBack);
                    }
                });
            }
        }).start();
    }

    @Override
    public void insert(final CalendarBuilder obj, final ICallBack callBack) {
        mCtx = obj.getContext();
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.insert(obj, callBack);
            }
        }).start();
    }

    @Override
    public boolean query(final CalendarBuilder obj, final ICallBack callBack) {
        mCtx = obj.getContext();
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
        mCtx = obj.getContext();
        new Thread(new Runnable() {
            @Override
            public void run() {
                NonBlockCalendar.super.delete(obj, callBack);
            }
        }).start();
    }

    @Override
    public void callBackSuccess(@NonNull final ICallBack callback) {
        if (mCtx instanceof Activity) {
            ((Activity) mCtx).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NonBlockCalendar.super.callBackSuccess(callback);
                }
            });
        }
    }

    @Override
    public void callBackFailed(@NonNull final ICallBack callback) {
        if (mCtx instanceof Activity) {
            ((Activity) mCtx).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NonBlockCalendar.super.callBackFailed(callback);
                }
            });
        }
    }
}
