package com.portfolio.marketeasy.core.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderDetails;

import java.util.List;

@Dao
public abstract class SaleOrderDAO {
    @Query("select * from Product")
    public abstract List<SaleOrderEntity> getAll();

    @Query("select * from Product where productId=:id")
    public abstract SaleOrderEntity getById(int id);

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
    public long insertSaleOrderDetails(SaleOrderDetails saleOrderDetails){
        long orderId;
        List<Long> detailsIdList;

        orderId = insert(saleOrderDetails.getSaleOrder());

        for(SaleOrderDetailEntity detail : saleOrderDetails.getSaleOrderDetailList()){
            detail.setSaleOrderId(orderId);
        }

        detailsIdList = insertDetailList(saleOrderDetails.getSaleOrderDetailList());

        return orderId;
    }
}
