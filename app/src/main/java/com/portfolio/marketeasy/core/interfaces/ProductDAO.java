package com.portfolio.marketeasy.core.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.portfolio.marketeasy.core.entities.ProductEntity;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("select * from Product")
    List<ProductEntity> getAll();

    @Query("select * from Product")
    LiveData<List<ProductEntity>> getAllLiveData();

    @Query("select * from Product where productId=:id")
    ProductEntity getById(int id);

    @Insert
    long insert(ProductEntity entity);

    @Update
    int update(ProductEntity entity);

    @Delete
    int delete(ProductEntity entity);

}
