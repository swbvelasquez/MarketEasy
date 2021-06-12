package com.portfolio.marketeasy.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.interfaces.IProductRepository;
import com.portfolio.marketeasy.infrastructure.repositories.ProductRepository;

import java.util.List;

public class CatalogueViewModel extends AndroidViewModel {
    private IProductRepository productRepository;
    private LiveData<List<ProductEntity>> productLiveDataList;
    private LiveData<Boolean> productTaskState;

    public CatalogueViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        productLiveDataList = productRepository.getAll();
        productTaskState = productRepository.getTaskState();
    }

    public void addProductToCar(ProductEntity entity){
        //do something in car
        //productRepository.insert(entity);
    }

    public LiveData<Boolean> getProductTaskState(){
        return productTaskState;
    }

    public LiveData<List<ProductEntity>> getAllProducts(){
        return productLiveDataList;
    }
}
