package com.portfolio.marketeasy.core.entities;

import androidx.room.Entity;

@Entity(
        tableName = "SaleOrderDetail",
        primaryKeys = {"saleOrderId","productId"}
)
public class SaleOrderDetailEntity { //cross reference
    private long saleOrderId;
    private long productId;
    private int quantity;
    private double price;

    public SaleOrderDetailEntity() {
    }

    public long getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
