package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.interfaces.ProductDAO;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

public class InsertSaleOrderDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final MutableLiveData<String> taskMessage;
    private final SaleOrderDAO saleOrderDAO;
    private final SaleOrderEntity saleOrderEntity;

    public InsertSaleOrderDBTask(MutableLiveData<Boolean> taskStatus, MutableLiveData<String> taskMessage, SaleOrderDAO saleOrderDAO, SaleOrderEntity saleOrderEntity) {
        this.taskStatus = taskStatus;
        this.taskMessage = taskMessage;
        this.saleOrderDAO = saleOrderDAO;
        this.saleOrderEntity = saleOrderEntity;
    }

    @Override
    public void run() {
        long id;
        taskStatus.postValue(true);
        id = saleOrderDAO.insert(saleOrderEntity);
        taskStatus.postValue(false);
        taskMessage.postValue(String.valueOf(id));
    }
}
