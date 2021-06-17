package com.portfolio.marketeasy.core.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProductDetails;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProducts;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class SaleOrderDAO {
    @Query("select * from SaleOrder")
    public abstract List<SaleOrderEntity> getAll();

    @Query("select * from SaleOrder")
    public abstract LiveData<List<SaleOrderEntity>> getAllLiveData();

    @Transaction
    @Query("select * from SaleOrder")
    public abstract LiveData<List<SaleOrderWithProducts>> getAllSaleOrderWithProducts();

    @Query("select sor.saleOrderId, sor.userId, sor.active, sor.registerDate, " +
            "prd.productId, prd.name, prd.brand, prd.description, prd.stock, " +
            "sod.price, prd.expirationDate, prd.urlImage, sod.quantity " +
            "from SaleOrder as sor " +
            "inner join SaleOrderDetail as sod on sor.saleOrderId=sod.saleOrderId " +
            "inner join Product prd on sod.productId=prd.productId " +
            "where sor.saleOrderId=:id"
    )
    public abstract List<SaleOrderWithProductDetails> getSaleOrderWithProductDetails(int id);

    @Query("select * from SaleOrder where saleOrderId=:id")
    public abstract SaleOrderEntity getById(int id);

    @Transaction
    @Query("select * from SaleOrder where saleOrderId=:id")
    public abstract SaleOrderWithProducts getSaleOrderWithProducts(int id);

    @Insert
    public abstract long insertDetail(SaleOrderDetailEntity entity);

    @Update
    public abstract void updateDetail(SaleOrderDetailEntity entity);

    @Delete
    public abstract void deleteDetail(SaleOrderDetailEntity entity);

    @Insert
    public abstract List<Long> insertDetailList(List<SaleOrderDetailEntity> entityList);

    @Insert
    public abstract long insert(SaleOrderEntity entity);

    @Update
    public abstract int update(SaleOrderEntity entity);

    @Delete
    public abstract int delete(SaleOrderEntity entity);

    @Transaction
    public long insertSaleOrderWithDetails(SaleOrderWithProducts saleOrderWithProducts){
        long orderId;
        List<Long> detailsIdList;

        orderId = insert(saleOrderWithProducts.getSaleOrder());

        if(saleOrderWithProducts.getSaleOrderProductList()!=null && saleOrderWithProducts.getSaleOrderProductList().size()>0) {

            List<SaleOrderDetailEntity> saleOrderDetailList = new ArrayList<>();
            SaleOrderDetailEntity saleOrderDetail;

            for (ProductEntity product : saleOrderWithProducts.getSaleOrderProductList()) {
                saleOrderDetail = new SaleOrderDetailEntity();
                saleOrderDetail.setProductId(product.getProductId());
                saleOrderDetail.setSaleOrderId(orderId);
                saleOrderDetail.setQuantity(1);
                saleOrderDetail.setPrice(product.getPrice());
            }

            detailsIdList = insertDetailList(saleOrderDetailList);
        }

        return orderId;
    }
}
