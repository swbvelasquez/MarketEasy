package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithDetails;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

public class InsertSaleOrderWithDetailsDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final SaleOrderDAO saleOrderDAO;
    private final SaleOrderWithDetails saleOrderWithDetails;

    public InsertSaleOrderWithDetailsDBTask(MutableLiveData<Boolean> taskStatus, SaleOrderDAO saleOrderDAO, SaleOrderWithDetails saleOrderWithDetails) {
        this.taskStatus = taskStatus;
        this.saleOrderDAO = saleOrderDAO;
        this.saleOrderWithDetails = saleOrderWithDetails;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        saleOrderDAO.insertSaleOrderWithDetails(saleOrderWithDetails);
        taskStatus.postValue(false);
    }
}
