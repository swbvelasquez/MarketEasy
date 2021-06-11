package com.portfolio.marketeasy.infrastructure.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutor {
    private static AppExecutor instance;
    private final ScheduledExecutorService executorService;

    public AppExecutor() {
        executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public static AppExecutor getInstance(){
        if(instance==null){
            instance = new AppExecutor();
        }
        return instance;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }
}
