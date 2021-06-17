package com.portfolio.marketeasy.core.interfaces;

import androidx.lifecycle.LiveData;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProductDetails;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProducts;

import java.util.List;

public interface ISaleOrderRepository {
    LiveData<Boolean> getTaskState();
    LiveData<String> getTaskMessage();
    LiveData<List<SaleOrderEntity>> getAllLiveData();
    LiveData<List<SaleOrderWithProducts>> getAllSaleOrderWithProductsLiveData();
    LiveData<List<SaleOrderWithProductDetails>> getSaleOrderWithProductDetailsLiveData();
    LiveData<SaleOrderWithProducts> getSaleOrderWithProductsLiveData();
    void getOrderWithProducts(int id);
    void getOrderWithProductDetails(int id);
    void insert(SaleOrderEntity entity);
    void insertDetail(SaleOrderDetailEntity entity);
    void insertDetailList(List<SaleOrderDetailEntity> entityList);
    void update(SaleOrderEntity entity);
    void delete(SaleOrderEntity entity);
    void insertSaleOrderWithDetails(SaleOrderWithProducts entity);
}
