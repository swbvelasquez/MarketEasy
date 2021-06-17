package com.portfolio.marketeasy.core.entities.relations;

import androidx.room.Embedded;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;

public class SaleOrderWithProductDetails {
    @Embedded
    private SaleOrderEntity saleOrder;
    @Embedded
    private ProductEntity productEntity;
    private int quantity;

    public SaleOrderWithProductDetails() {
    }

    public SaleOrderEntity getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrderEntity saleOrder) {
        this.saleOrder = saleOrder;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
