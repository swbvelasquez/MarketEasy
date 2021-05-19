package com.portfolio.marketeasy.core.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Product")
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    private long productId;
    private String name;
    private String description;
    private int stock;
    private double price;
    private Date expirationDate;
    private long saleOrderDetailId;

    public ProductEntity() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getSaleOrderDetailId() {
        return saleOrderDetailId;
    }

    public void setSaleOrderDetailId(long saleOrderDetailId) {
        this.saleOrderDetailId = saleOrderDetailId;
    }
}
