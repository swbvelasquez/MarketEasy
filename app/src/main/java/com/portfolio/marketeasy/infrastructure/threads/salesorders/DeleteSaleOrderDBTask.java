package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

public class DeleteSaleOrderDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final SaleOrderDAO saleOrderDAO;
    private final SaleOrderEntity saleOrderEntity;

    public DeleteSaleOrderDBTask(MutableLiveData<Boolean> taskStatus, SaleOrderDAO saleOrderDAO, SaleOrderEntity saleOrderEntity) {
        this.taskStatus = taskStatus;
        this.saleOrderDAO = saleOrderDAO;
        this.saleOrderEntity = saleOrderEntity;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        saleOrderDAO.delete(saleOrderEntity);
        taskStatus.postValue(false);
    }
}
