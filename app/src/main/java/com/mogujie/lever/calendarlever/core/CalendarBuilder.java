package com.mogujie.lever.calendarlever.core;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by chenhuigu on 17/2/22.
 */

public class CalendarBuilder {
    private Context context;
    private long stratTime;
    private long endTime;
    private String title = "";
    private String description = "";
    //用于标识唯一性
    private String iid;
    private long eventId;

    private CalendarBuilder(Builder builder) {
        context = builder.context;
        stratTime = builder.stratTime;
        endTime = builder.endTime;
        title = builder.title;
        description = builder.description;
        iid = builder.iid;
        eventId = builder.eventId;
    }

    public static class Builder implements IBuilder<CalendarBuilder> {
        private Context context;
        private long stratTime;
        private long endTime;
        private String title = "";
        private String description = "";
        //用于标识唯一性
        private String iid;
        //被成功插入后分配的ID
        private long eventId = Long.MIN_VALUE;

        public Builder(@NonNull Context val,@NonNull String id, long st, long et) {
            context = val;
            iid = id;
            stratTime = st;
            endTime = et;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder eventid(long val) {
            eventId = val;
            return this;
        }

        @Override
        public CalendarBuilder build() {
            return new CalendarBuilder(this);
        }
    }

    @NonNull
    public Context getContext() {
        return context;
    }

    public long getStratTime() {
        return stratTime;
    }

    public long getEndTime() {
        return endTime;
    }

    @NonNull
    public String getTitle() {
        return title == null ? title = "" : title;
    }

    @NonNull
    public String getDescription() {
        return description == null ? description = "" : description;
    }

    @NonNull
    public String getIid() {
        return iid == null ? iid = "" : iid;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getEventId() {
        return eventId;
    }
}
