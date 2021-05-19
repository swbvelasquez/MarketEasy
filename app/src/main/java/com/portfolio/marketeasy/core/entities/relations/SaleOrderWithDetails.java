package com.portfolio.marketeasy.core.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;

import java.util.List;

public class SaleOrderWithDetails {
    @Embedded
    private SaleOrderEntity saleOrder;
    @Relation(
            parentColumn = "saleOrderId",
            entityColumn = "saleOrderId",
            entity = SaleOrderDetailEntity.class
    )
    private List<SaleOrderDetailEntity> saleOrderDetailList;

    public SaleOrderWithDetails() {
    }

    public SaleOrderEntity getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrderEntity saleOrder) {
        this.saleOrder = saleOrder;
    }

    public List<SaleOrderDetailEntity> getSaleOrderDetailList() {
        return saleOrderDetailList;
    }

    public void setSaleOrderDetailList(List<SaleOrderDetailEntity> saleOrderDetailList) {
        this.saleOrderDetailList = saleOrderDetailList;
    }
}
