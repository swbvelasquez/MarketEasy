package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithDetails;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

import java.util.List;

public class ListSaleOrderWithDetailsDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final MutableLiveData<List<SaleOrderWithDetails>> saleOrderWithDetailsList;
    private final SaleOrderDAO saleOrderDAO;
    private final int id;

    public ListSaleOrderWithDetailsDBTask(MutableLiveData<Boolean> taskStatus, MutableLiveData<List<SaleOrderWithDetails>> saleOrderWithDetailsList, SaleOrderDAO saleOrderDAO, int id) {
        this.taskStatus = taskStatus;
        this.saleOrderWithDetailsList = saleOrderWithDetailsList;
        this.saleOrderDAO = saleOrderDAO;
        this.id = id;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        List<SaleOrderWithDetails> detailsEntityList = saleOrderDAO.getAllSaleOrderWithDetails(id);
        saleOrderWithDetailsList.postValue(detailsEntityList);
        taskStatus.postValue(false);
    }
}
