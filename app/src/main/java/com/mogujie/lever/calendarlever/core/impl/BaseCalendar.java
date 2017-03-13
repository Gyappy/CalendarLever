package com.mogujie.lever.calendarlever.core.impl;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;

import com.mogujie.lever.calendarlever.core.ICalendar;
import com.mogujie.lever.calendarlever.core.ICallBack;
import com.mogujie.lever.calendarlever.core.other.CalendarBuilder;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by guchenhui on 17/2/25.
 */

public abstract class BaseCalendar implements ICalendar<CalendarBuilder> {
    private static final int PRE_NOTICE_TIME = 5;
    protected Context mCtx;

    /**
     * 插入事件
     *
     * @param obj
     * @param callBack
     */
    @Override
    public void insert(CalendarBuilder obj, ICallBack callBack) {
        try {
            Thread.sleep(2000);
            // 随意取一个日历
            String calId = getCalendarId(obj.getContext());
            // 为日历添加一个事件
            long eventId = insertEvent(obj.getContext(), obj.getTitle(), obj.getDescription(), calId, obj.getStratTime(), obj.getEndTime(), obj.getIid());
            if (eventId == Long.MIN_VALUE || eventId <= 0) {
                callBackFailed(callBack);
                return;
            } else {
                //存下这个ID
                obj.setEventId(eventId);
            }
            // 为事件添加一个提醒 提前 @PRE_NOTICE_TIME 分钟有提醒
            insertReminder(obj.getContext(), eventId, PRE_NOTICE_TIME);
            callBackSuccess(callBack);
        } catch (Exception ignore) {
            callBackFailed(callBack);
        }
    }

    /**
     * 查询事件是否已经存在
     *
     * @param obj
     * @param callBack
     * @return
     */
    @Override
    public boolean query(CalendarBuilder obj, ICallBack callBack) {
        Cursor cursor = null;
        try {
            Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
            ContentUris.appendId(builder, obj.getStratTime());
            ContentUris.appendId(builder, obj.getEndTime());

            String selection = CalendarContract.Events.ORGANIZER + " = ?";
            String[] selectionArgs = new String[]{obj.getIid()};
            cursor = obj.getContext().getContentResolver().query(builder.build(), new String[]{CalendarContract.Reminders.EVENT_ID}, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToNext()) {
                obj.setEventId(cursor.getLong(0));
                callBackSuccess(callBack);
                return true;
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        callBackFailed(callBack);
        return false;
    }


    /**
     * 删除事件
     *
     * @param obj
     * @param callBack
     */
    @Override
    public void delete(final CalendarBuilder obj, final ICallBack callBack) {
        if (obj.getEventId() == Long.MIN_VALUE) {
            throw new RuntimeException("you must to set the value of CalendarBuilder$eventId");
        }
        try {
            Uri deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, obj.getEventId());
            obj.getContext().getContentResolver().delete(deleteUri, null, null);
            query(obj, new ICallBack() {
                @Override
                public void onSuccess() {
                    callBackSuccess(callBack);
                }

                @Override
                public void onFailed() {
                    //置回初始值
                    obj.setEventId(Long.MIN_VALUE);
                    callBackSuccess(callBack);
                }
            });
        } catch (Exception ignore) {
            callBackFailed(callBack);
        }
    }

    /**
     * 将当前事件置反-同步
     *
     * @param obj
     * @param callBack
     */
    @Override
    public void reverse(final CalendarBuilder obj, final ICallBack callBack) {
        query(obj, new ICallBack() {
            @Override
            public void onSuccess() {
                delete(obj, callBack);
            }

            @Override
            public void onFailed() {
                insert(obj, callBack);
            }
        });
    }

    private String getCalendarId(Context context) throws Exception {
        Cursor cursor = null;
        try {
            String calId = "";
            cursor = context.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, null,
                    null, null, null);
            if (null != cursor && cursor.moveToFirst()) {
                calId = cursor.getString(cursor.getColumnIndex(BaseColumns._ID));
            }
            return calId;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private long insertEvent(Context context, String title, String description, String calId, long stime, long etime, String identifier) throws Exception {
        ContentValues event = new ContentValues();
        //标题
        event.put(CalendarContract.Events.TITLE, title);
        //内容
        event.put(CalendarContract.Events.DESCRIPTION, description);
        //插入到账户
        event.put(CalendarContract.Events.CALENDAR_ID, calId);

        //起始时间
        event.put(CalendarContract.Events.DTSTART, stime);
        //截止时间
        event.put(CalendarContract.Events.DTEND, etime);
        //控制是否事件触发报警，提醒如下
        event.put(CalendarContract.Events.HAS_ALARM, 1);
        //设置时区,否则会报错
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        //把itemId插入表中 把item与表关联
        event.put(CalendarContract.Events.ORGANIZER, identifier);
        //插入事件
        Uri newEvent = context.getContentResolver().insert(CalendarContract.Events.CONTENT_URI, event);
        if (newEvent != null) {
            return Long.parseLong(newEvent.getLastPathSegment());
        }
        return Long.MIN_VALUE;
    }

    private Uri insertReminder(Context context, long eventId, int minutes) throws Exception {
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, minutes);
        values.put(CalendarContract.Reminders.EVENT_ID, eventId);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        return context.getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values);
    }

    public void callBackSuccess(ICallBack callback) {
        if (callback != null) {
            callback.onSuccess();
        }
    }

    public void callBackFailed(ICallBack callback) {
        if (callback != null) {
            callback.onFailed();
        }
    }
}
