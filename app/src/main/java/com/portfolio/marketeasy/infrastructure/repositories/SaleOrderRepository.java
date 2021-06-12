package com.portfolio.marketeasy.infrastructure.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithDetails;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProductDetails;
import com.portfolio.marketeasy.core.interfaces.ISaleOrderRepository;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;
import com.portfolio.marketeasy.infrastructure.data.AppDataBase;
import com.portfolio.marketeasy.infrastructure.threads.AppExecutor;
import com.portfolio.marketeasy.infrastructure.threads.products.InsertProductDBTask;
import com.portfolio.marketeasy.infrastructure.threads.products.UpdateProductDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.DeleteSaleOrderDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.InsertSaleOrderDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.InsertSaleOrderDetailDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.InsertSaleOrderDetailListDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.InsertSaleOrderWithDetailsDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.ListSaleOrderWithDetailsDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.ListSaleOrderWithProductDetailsDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.UpdateSaleOrderDBTask;

import java.util.List;

public class SaleOrderRepository implements ISaleOrderRepository {
    private SaleOrderDAO saleOrderDAO;
    private AppExecutor appExecutor;
    private LiveData<List<SaleOrderEntity>> saleOrderList;
    private MutableLiveData<List<SaleOrderWithDetails>> saleOrderWithDetailsList;
    private MutableLiveData<List<SaleOrderWithProductDetails>> saleOrderWithProductDetailsList;
    private MutableLiveData<Boolean> taskState;

    public SaleOrderRepository(Application application) {
        AppDataBase appDataBase = AppDataBase.getInstance(application);
        saleOrderDAO = appDataBase.saleOrderDAO();
        appExecutor = AppExecutor.getInstance();
        taskState = new MutableLiveData<>();
        saleOrderWithDetailsList = new MutableLiveData<>();
        saleOrderWithProductDetailsList = new MutableLiveData<>();
        saleOrderList =saleOrderDAO.getAllLiveData();
    }

    @Override
    public LiveData<Boolean> getTaskState() {
        return taskState;
    }

    @Override
    public LiveData<List<SaleOrderEntity>> getAllLiveData() {
        return saleOrderList;
    }

    @Override
    public LiveData<List<SaleOrderWithDetails>> getAllSaleOrderWithDetailsLiveData() {
        return saleOrderWithDetailsList;
    }

    @Override
    public LiveData<List<SaleOrderWithProductDetails>> getAllSaleOrderWithProductDetailsLiveData() {
        return saleOrderWithProductDetailsList;
    }

    @Override
    public void getAllOrderWithDetails(int id) {
        try{
            appExecutor.getExecutorService().execute(new ListSaleOrderWithDetailsDBTask(taskState,saleOrderWithDetailsList,saleOrderDAO,id));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getAllOrderWithProductDetails(int id) {
        try{
            appExecutor.getExecutorService().execute(new ListSaleOrderWithProductDetailsDBTask(taskState,saleOrderWithProductDetailsList,saleOrderDAO,id));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insert(SaleOrderEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new InsertSaleOrderDBTask(taskState,saleOrderDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertDetail(SaleOrderDetailEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new InsertSaleOrderDetailDBTask(taskState,saleOrderDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertDetailList(List<SaleOrderDetailEntity> entityList) {
        try{
            appExecutor.getExecutorService().execute(new InsertSaleOrderDetailListDBTask(taskState,saleOrderDAO,entityList));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(SaleOrderEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new UpdateSaleOrderDBTask(taskState,saleOrderDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(SaleOrderEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new DeleteSaleOrderDBTask(taskState,saleOrderDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertSaleOrderWithDetails(SaleOrderWithDetails entity) {
        try{
            appExecutor.getExecutorService().execute(new InsertSaleOrderWithDetailsDBTask(taskState,saleOrderDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
