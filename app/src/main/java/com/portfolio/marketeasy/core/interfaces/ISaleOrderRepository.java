package com.portfolio.marketeasy.core.interfaces;

import androidx.lifecycle.LiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithDetails;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProductDetails;

import java.util.List;

public interface ISaleOrderRepository {
    LiveData<Boolean> getTaskState();
    LiveData<List<SaleOrderEntity>> getAllLiveData();
    LiveData<List<SaleOrderWithDetails>> getAllSaleOrderWithDetailsLiveData();
    LiveData<List<SaleOrderWithProductDetails>> getAllSaleOrderWithProductDetailsLiveData();
    void getAllOrderWithDetails(int id);
    void getAllOrderWithProductDetails(int id);
    void insert(SaleOrderEntity entity);
    void insertDetail(SaleOrderDetailEntity entity);
    void insertDetailList(List<SaleOrderDetailEntity> entityList);
    void update(SaleOrderEntity entity);
    void delete(SaleOrderEntity entity);
    void insertSaleOrderWithDetails(SaleOrderWithDetails entity);
}
