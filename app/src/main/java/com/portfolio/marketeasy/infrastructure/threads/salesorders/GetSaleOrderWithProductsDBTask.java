package com.portfolio.marketeasy.infrastructure.threads.salesorders;

import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProducts;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

public class GetSaleOrderWithProductsDBTask implements Runnable{

    private final MutableLiveData<Boolean> taskStatus;
    private final MutableLiveData<SaleOrderWithProducts> saleOrderWithProducts;
    private final SaleOrderDAO saleOrderDAO;
    private final int id;

    public GetSaleOrderWithProductsDBTask(MutableLiveData<Boolean> taskStatus, MutableLiveData<SaleOrderWithProducts> saleOrderWithProducts, SaleOrderDAO saleOrderDAO, int id) {
        this.taskStatus = taskStatus;
        this.saleOrderWithProducts = saleOrderWithProducts;
        this.saleOrderDAO = saleOrderDAO;
        this.id = id;
    }

    @Override
    public void run() {
        taskStatus.postValue(true);
        SaleOrderWithProducts orderWithProducts = saleOrderDAO.getSaleOrderWithProducts(id);
        saleOrderWithProducts.postValue(orderWithProducts);
        taskStatus.postValue(false);
    }
}
