package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

public class InsertSaleOrderDetailDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final MutableLiveData<String> taskMessage;
    private final SaleOrderDAO saleOrderDAO;
    private final SaleOrderDetailEntity saleOrderDetailEntity;

    public InsertSaleOrderDetailDBTask(MutableLiveData<Boolean> taskStatus, MutableLiveData<String> taskMessage, SaleOrderDAO saleOrderDAO, SaleOrderDetailEntity saleOrderDetailEntity) {
        this.taskStatus = taskStatus;
        this.taskMessage = taskMessage;
        this.saleOrderDAO = saleOrderDAO;
        this.saleOrderDetailEntity = saleOrderDetailEntity;
    }

    @Override
    public void run() {
        long id;
        taskStatus.postValue(true);
        id=saleOrderDAO.insertDetail(saleOrderDetailEntity);
        taskStatus.postValue(false);
        taskMessage.postValue("101-1-"+id+"-OK"); //request code-status-id-message
    }
}
