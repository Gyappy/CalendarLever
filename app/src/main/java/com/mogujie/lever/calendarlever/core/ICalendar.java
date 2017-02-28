package com.mogujie.lever.calendarlever.core;

/**
 * Created by guchenhui on 17/2/22.
 */

public interface ICalendar<T> {
    void insert(T obj, ICallBack callBack);

    boolean query(T obj, ICallBack callBack);

    void delete(T obj, ICallBack callBack);

    void reverse(T obj, ICallBack callBack);
}
