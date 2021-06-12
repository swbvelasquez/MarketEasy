package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithDetails;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProductDetails;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

import java.util.List;

public class ListSaleOrderWithProductDetailsDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final MutableLiveData<List<SaleOrderWithProductDetails>> saleOrderWithProductDetailsList;
    private final SaleOrderDAO saleOrderDAO;
    private final int id;

    public ListSaleOrderWithProductDetailsDBTask(MutableLiveData<Boolean> taskStatus, MutableLiveData<List<SaleOrderWithProductDetails>> saleOrderWithProductDetailsList, SaleOrderDAO saleOrderDAO, int id) {
        this.taskStatus = taskStatus;
        this.saleOrderWithProductDetailsList = saleOrderWithProductDetailsList;
        this.saleOrderDAO = saleOrderDAO;
        this.id = id;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        List<SaleOrderWithProductDetails> detailsEntityList = saleOrderDAO.getAllSaleOrderWithProductDetails(id);
        saleOrderWithProductDetailsList.postValue(detailsEntityList);
        taskStatus.postValue(false);
    }
}
