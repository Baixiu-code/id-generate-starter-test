package com.jd.rd.product.infrastructure.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程工具类
 */
public class ThreadFactoryUtil {

    public static Map<String, ThreadPoolExecutor> pools = new ConcurrentHashMap<>();

    /**
     * Normal
     * 所有线程池在此定义
     */
    public enum Pool {

        /**
         * 共用线程池
         */
        SINGLE(1),
        COMMON(64),
        PARALLEL(200),
        IMPORT(200),
        JMQ(40),
        DATA_INITIAL(64),
       ;

        ExecutorService executor;

        Pool(int threadNum) {
            executor = new ThreadPoolExecutor(threadNum,threadNum,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue(threadNum),new ThreadPoolExecutor.CallerRunsPolicy());
        }

        public ExecutorService getExecutor() {
            return executor;
        }
    }

}
