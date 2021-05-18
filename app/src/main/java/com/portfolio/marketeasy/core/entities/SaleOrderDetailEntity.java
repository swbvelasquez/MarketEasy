package com.portfolio.marketeasy.core.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "SaleOrderDetail")
public class SaleOrderDetailEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    private long saleOrderDetailId;
    private long saleOrderId;
    private long productId;
    private int quantity;
    private double price;
    private Date registerDate;

    public SaleOrderDetailEntity() {
    }

    public long getSaleOrderDetailId() {
        return saleOrderDetailId;
    }

    public void setSaleOrderDetailId(long saleOrderDetailId) {
        this.saleOrderDetailId = saleOrderDetailId;
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
