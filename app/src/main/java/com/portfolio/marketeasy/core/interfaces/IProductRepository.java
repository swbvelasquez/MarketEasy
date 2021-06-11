package com.portfolio.marketeasy.core.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.portfolio.marketeasy.core.entities.ProductEntity;

import java.util.List;

public interface IProductRepository {
    LiveData<Boolean> getTaskState();
    LiveData<List<ProductEntity>> getAll();
    void insert(ProductEntity entity);
    void update(ProductEntity entity);
    void delete(ProductEntity entity);
}
