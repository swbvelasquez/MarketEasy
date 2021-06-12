package com.portfolio.marketeasy.core.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithDetails;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProductDetails;

import java.util.List;

@Dao
public abstract class SaleOrderDAO {
    @Query("select * from SaleOrder")
    public abstract List<SaleOrderEntity> getAll();

    @Query("select * from SaleOrder")
    public abstract LiveData<List<SaleOrderEntity>> getAllLiveData();

    @Query("select * from SaleOrder where saleOrderId=:id")
    public abstract SaleOrderEntity getById(int id);

    @Transaction
    @Query("select * from SaleOrder where saleOrderId=:id")
    public abstract List<SaleOrderWithDetails> getAllSaleOrderWithDetails(int id);

    @Transaction
    @Query("select * from SaleOrder where saleOrderId=:id")
    public abstract List<SaleOrderWithProductDetails> getAllSaleOrderWithProductDetails(int id);

    @Insert
    public abstract long insert(SaleOrderEntity entity);

    @Insert
    public abstract long insertDetail(SaleOrderDetailEntity entity);

    @Insert
    public abstract List<Long> insertDetailList(List<SaleOrderDetailEntity> entityList);

    @Update
    public abstract int update(SaleOrderEntity entity);

    @Delete
    public abstract int delete(SaleOrderEntity entity);

    @Transaction
    public long insertSaleOrderWithDetails(SaleOrderWithDetails saleOrderWithDetails){
        long orderId;
        List<Long> detailsIdList;

        orderId = insert(saleOrderWithDetails.getSaleOrder());

        for(SaleOrderDetailEntity detail : saleOrderWithDetails.getSaleOrderDetailList()){
            detail.setSaleOrderId(orderId);
        }

        detailsIdList = insertDetailList(saleOrderWithDetails.getSaleOrderDetailList());

        return orderId;
    }
}
