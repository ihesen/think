package com.ihesen.think.utils;

import android.os.SystemClock;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: ihesen on 2016/4/22 14:49
 * email: hesen@ichsy.com
 */
public class ThreadPool {
    LinkedList<Runnable> list = new LinkedList<Runnable>();
    int maxCount = 5;
    AtomicInteger currentCount = new AtomicInteger(0);

    public void excute(Runnable runnable) {
        synchronized (list) {
            list.add(runnable);
        }
        if (currentCount.getAndIncrement() < maxCount) {
            createThread();
        }
    }

    public void createThread() {
        new Thread() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    Runnable first = null;
                    synchronized (list) {
                        first = list.getFirst();
                    }
                    if (first != null) {
                        first.run();
                        count = 0;
                    } else {
                        SystemClock.sleep(10);
                        count++;
                    }
                    if (count > 100) {
                        break;
                    }
                }
                currentCount.getAndDecrement();
            }
        }.start();
    }
}
