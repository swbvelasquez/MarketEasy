package com.portfolio.marketeasy.infrastructure.threads;

import android.os.SystemClock;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.interfaces.ProductDAO;

public class DummyDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;

    public DummyDBTask(MutableLiveData<Boolean> taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        Log.d("DummyDBTask", "start run");
        SystemClock.sleep(5000);
        Log.d("DummyDBTask", "end run");
        taskStatus.postValue(false);
    }
}
