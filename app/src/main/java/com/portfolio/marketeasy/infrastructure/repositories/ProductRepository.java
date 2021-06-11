package com.portfolio.marketeasy.infrastructure.repositories;

import android.app.Application;
import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.global.Constants;
import com.portfolio.marketeasy.core.interfaces.IProductRepository;
import com.portfolio.marketeasy.core.interfaces.ProductDAO;
import com.portfolio.marketeasy.infrastructure.data.AppDataBase;
import com.portfolio.marketeasy.infrastructure.threads.AppExecutor;

import java.util.List;

public class ProductRepository implements IProductRepository {
    private ProductDAO productDAO;
    private AppExecutor appExecutor;
    private LiveData<List<ProductEntity>> mutableProductList;
    private MutableLiveData<Boolean> taskState;

    public ProductRepository(Application application) {
        AppDataBase appDataBase = AppDataBase.getInstance(application);
        productDAO = appDataBase.productDAO();
        appExecutor = AppExecutor.getInstance();
        taskState = new MutableLiveData<>();
        mutableProductList=productDAO.getAllLiveData(); //el live data de room lo trabaja en automatico en background
    }

    @Override
    public LiveData<Boolean> getTaskState() {
        return taskState;
    }

    @Override
    public LiveData<List<ProductEntity>> getAll() {
        return mutableProductList;
    }

    @Override
    public void insert(ProductEntity entity) {
        try{
            taskState.postValue(true);
            appExecutor.getExecutorService().execute(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(5000);
                    //productDAO.insert(entity);
                    taskState.postValue(false);
                }
            });
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(ProductEntity entity) {
        try{
            appExecutor.getExecutorService().execute(() -> productDAO.update(entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(ProductEntity entity) {
        try{
            appExecutor.getExecutorService().execute(() -> productDAO.delete(entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
