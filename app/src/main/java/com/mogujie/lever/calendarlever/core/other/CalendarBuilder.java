package com.mogujie.lever.calendarlever.core.other;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by ouyi on 17/2/22.
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

    private CalendarBuilder() {
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

        public Builder(@NonNull Context val, @NonNull String id, long st, long et) {
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
            CalendarBuilder result = new CalendarBuilder();
            result.context = this.context;
            result.stratTime = this.stratTime;
            result.endTime = this.endTime;
            result.title = this.title;
            result.description = this.description;
            result.iid = this.iid;
            result.eventId = this.eventId;
            return result;
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
