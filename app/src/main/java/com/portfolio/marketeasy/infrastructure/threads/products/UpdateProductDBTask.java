package com.portfolio.marketeasy.infrastructure.threads.products;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.interfaces.ProductDAO;

public class UpdateProductDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final ProductDAO productDAO;
    private final ProductEntity productEntity;

    public UpdateProductDBTask(MutableLiveData<Boolean> taskStatus, ProductDAO productDAO, ProductEntity productEntity) {
        this.taskStatus = taskStatus;
        this.productDAO = productDAO;
        this.productEntity = productEntity;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        productDAO.update(productEntity);
        taskStatus.postValue(false);
    }
}
