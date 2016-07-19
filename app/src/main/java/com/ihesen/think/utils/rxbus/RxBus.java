package com.ihesen.think.utils.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * author: ihesen on 2016/4/18 16:43
 * email: hesen@ichsy.com
 */
public enum  RxBus {
    INSTANCE;
    // 主题
    private final Subject bus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    // 单例RxBus
//    public static RxBus getDefault() {
//        RxBus rxBus = defaultInstance;
//        if (defaultInstance == null) {
//            synchronized (RxBus.class) {
//                rxBus = defaultInstance;
//                if (defaultInstance == null) {
//                    rxBus = new RxBus();
//                    defaultInstance = rxBus;
//                }
//            }
//        }
//        return rxBus;
//    }

    // 提供了一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T extends BaseEvent> Observable<T> toObserverable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}