package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProducts;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

public class InsertSaleOrderWithDetailsDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final MutableLiveData<String> taskMessage;
    private final SaleOrderDAO saleOrderDAO;
    private final SaleOrderWithProducts saleOrderWithProducts;

    public InsertSaleOrderWithDetailsDBTask(MutableLiveData<Boolean> taskStatus, MutableLiveData<String> taskMessage, SaleOrderDAO saleOrderDAO, SaleOrderWithProducts saleOrderWithProducts) {
        this.taskStatus = taskStatus;
        this.taskMessage = taskMessage;
        this.saleOrderDAO = saleOrderDAO;
        this.saleOrderWithProducts = saleOrderWithProducts;
    }

    @Override
    public void run() {
        long id;
        taskStatus.postValue(true);
        id = saleOrderDAO.insertSaleOrderWithDetails(saleOrderWithProducts);
        taskStatus.postValue(false);
        taskMessage.postValue("100-1-" + id + "-OK");
    }
}
