package com.portfolio.marketeasy.core.entities.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;

import java.util.List;

public class SaleOrderWithProducts {
    @Embedded
    private SaleOrderEntity saleOrder;
    @Relation(
            parentColumn = "saleOrderId",
            entityColumn = "productId",
            associateBy = @Junction(SaleOrderDetailEntity.class) //cross ref many to many
    )
    private List<ProductEntity> saleOrderProductList;

    public SaleOrderWithProducts() {
    }

    public SaleOrderEntity getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrderEntity saleOrder) {
        this.saleOrder = saleOrder;
    }

    public List<ProductEntity> getSaleOrderProductList() {
        return saleOrderProductList;
    }

    public void setSaleOrderProductList(List<ProductEntity> saleOrderProductList) {
        this.saleOrderProductList = saleOrderProductList;
    }
}
