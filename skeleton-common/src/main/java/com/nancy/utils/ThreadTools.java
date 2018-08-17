package com.nancy.utils;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * All rights Reserved, Designed By guangfeng.zhou
 *
 * @version V1.0
 * @Project:
 * @Package:
 * @Description: 线程工具类
 * @author: guangfeng.zhou
 * @date: 2017/11/07 11:09
 */
public class ThreadTools {
    private volatile static ExecutorService executor = null ;
    private static final int       DEFAULT_POOL_SIZE = 25 ;

    public static ExecutorService getInstance(){
        if( executor==null || executor.isShutdown() || executor.isTerminated() ){
            synchronized (ThreadTools.class){
                if(executor==null || executor.isShutdown() || executor.isTerminated()){
                    executor = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE) ;
                }
            }
        }
        return executor ;
    }
}
