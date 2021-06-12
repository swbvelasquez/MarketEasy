package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

import java.util.List;

public class InsertSaleOrderDetailListDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final SaleOrderDAO saleOrderDAO;
    private final List<SaleOrderDetailEntity> detailEntityList;

    public InsertSaleOrderDetailListDBTask(MutableLiveData<Boolean> taskStatus, SaleOrderDAO saleOrderDAO, List<SaleOrderDetailEntity> detailEntityList) {
        this.taskStatus = taskStatus;
        this.saleOrderDAO = saleOrderDAO;
        this.detailEntityList = detailEntityList;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        saleOrderDAO.insertDetailList(detailEntityList);
        taskStatus.postValue(false);
    }
}
