package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

public class InsertSaleOrderDetailDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final SaleOrderDAO saleOrderDAO;
    private final SaleOrderDetailEntity saleOrderDetailEntity;

    public InsertSaleOrderDetailDBTask(MutableLiveData<Boolean> taskStatus, SaleOrderDAO saleOrderDAO, SaleOrderDetailEntity saleOrderDetailEntity) {
        this.taskStatus = taskStatus;
        this.saleOrderDAO = saleOrderDAO;
        this.saleOrderDetailEntity = saleOrderDetailEntity;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        saleOrderDAO.insertDetail(saleOrderDetailEntity);
        taskStatus.postValue(false);
    }
}
