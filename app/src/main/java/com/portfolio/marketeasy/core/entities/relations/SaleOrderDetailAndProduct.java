package com.portfolio.marketeasy.core.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;

public class SaleOrderDetailAndProduct {
    @Embedded
    private SaleOrderDetailEntity saleOrderDetail;

    @Relation(
            parentColumn = "saleOrderDetailId",
            entityColumn = "saleOrderDetailId",
            entity = ProductEntity.class
    )
    private ProductEntity product;

    public SaleOrderDetailAndProduct() {
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public SaleOrderDetailEntity getSaleOrderDetail() {
        return saleOrderDetail;
    }

    public void setSaleOrderDetail(SaleOrderDetailEntity saleOrderDetail) {
        this.saleOrderDetail = saleOrderDetail;
    }
}
