package com.portfolio.marketeasy.infrastructure.threads.products;

import android.os.SystemClock;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.interfaces.ProductDAO;

public class InsertProductDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final ProductDAO productDAO;
    private final ProductEntity productEntity;

    public InsertProductDBTask(MutableLiveData<Boolean> taskStatus, ProductDAO productDAO, ProductEntity productEntity) {
        this.taskStatus = taskStatus;
        this.productDAO = productDAO;
        this.productEntity = productEntity;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        productDAO.insert(productEntity);
        taskStatus.postValue(false);
    }
}
