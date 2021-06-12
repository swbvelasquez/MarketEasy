package com.portfolio.marketeasy.infrastructure.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.interfaces.IProductRepository;
import com.portfolio.marketeasy.core.interfaces.ProductDAO;
import com.portfolio.marketeasy.infrastructure.data.AppDataBase;
import com.portfolio.marketeasy.infrastructure.threads.AppExecutor;
import com.portfolio.marketeasy.infrastructure.threads.products.DeleteProductDBTask;
import com.portfolio.marketeasy.infrastructure.threads.products.InsertProductDBTask;
import com.portfolio.marketeasy.infrastructure.threads.products.UpdateProductDBTask;

import java.util.List;

public class ProductRepository implements IProductRepository {
    private ProductDAO productDAO;
    private AppExecutor appExecutor;
    private LiveData<List<ProductEntity>> productList;
    private MutableLiveData<Boolean> taskState;

    public ProductRepository(Application application) {
        AppDataBase appDataBase = AppDataBase.getInstance(application);
        productDAO = appDataBase.productDAO();
        appExecutor = AppExecutor.getInstance();
        taskState = new MutableLiveData<>();
        productList = productDAO.getAllLiveData(); //el live data de room lo trabaja en automatico en background
    }

    @Override
    public LiveData<Boolean> getTaskState() {
        return taskState;
    }

    @Override
    public LiveData<List<ProductEntity>> getAll() {
        return productList;
    }

    @Override
    public void insert(ProductEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new InsertProductDBTask(taskState,productDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(ProductEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new UpdateProductDBTask(taskState,productDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(ProductEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new DeleteProductDBTask(taskState,productDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
