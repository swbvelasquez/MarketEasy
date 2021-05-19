package com.portfolio.marketeasy.core.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;

import java.util.List;

public class SaleOrderWithProductDetails {
    @Embedded
    private SaleOrderEntity saleOrder;
    @Relation(
            parentColumn = "saleOrderId",
            entityColumn = "saleOrderId",
            entity = SaleOrderDetailEntity.class
    )
    private List<SaleOrderDetailAndProduct> detailAndProductList;

    public SaleOrderWithProductDetails() {
    }

    public SaleOrderEntity getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrderEntity saleOrder) {
        this.saleOrder = saleOrder;
    }

    public List<SaleOrderDetailAndProduct> getDetailAndProductList() {
        return detailAndProductList;
    }

    public void setDetailAndProductList(List<SaleOrderDetailAndProduct> detailAndProductList) {
        this.detailAndProductList = detailAndProductList;
    }
}
