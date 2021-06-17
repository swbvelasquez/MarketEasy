package com.portfolio.marketeasy.infrastructure.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProductDetails;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProducts;
import com.portfolio.marketeasy.core.interfaces.ISaleOrderRepository;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;
import com.portfolio.marketeasy.infrastructure.data.AppDataBase;
import com.portfolio.marketeasy.infrastructure.threads.AppExecutor;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.DeleteSaleOrderDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.GetSaleOrderWithProductsDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.GetSaleOrderWithProductDetailsDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.InsertSaleOrderDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.InsertSaleOrderDetailDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.InsertSaleOrderDetailListDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.InsertSaleOrderWithDetailsDBTask;
import com.portfolio.marketeasy.infrastructure.threads.salesorders.UpdateSaleOrderDBTask;

import java.util.List;

public class SaleOrderRepository implements ISaleOrderRepository {
    private SaleOrderDAO saleOrderDAO;
    private AppExecutor appExecutor;
    private LiveData<List<SaleOrderEntity>> saleOrderList;
    private LiveData<List<SaleOrderWithProducts>> saleOrderWithProductsList;
    private MutableLiveData<List<SaleOrderWithProductDetails>> saleOrderWithProductDetailsList;
    private MutableLiveData<Boolean> taskState;
    private MutableLiveData<String> taskMessage;
    private MutableLiveData<SaleOrderEntity> saleOrder;
    private MutableLiveData<SaleOrderWithProducts> saleOrderWithProducts;

    public SaleOrderRepository(Application application) {
        AppDataBase appDataBase = AppDataBase.getInstance(application);
        saleOrderDAO = appDataBase.saleOrderDAO();
        appExecutor = AppExecutor.getInstance();
        taskState = new MutableLiveData<>();
        taskMessage = new MutableLiveData<>();
        saleOrderList =saleOrderDAO.getAllLiveData(); //Son manejados por room en un thread directo al declararlos en el dao como live data
        saleOrderWithProductsList = saleOrderDAO.getAllSaleOrderWithProducts();
        saleOrderWithProductDetailsList = new MutableLiveData<>();
        saleOrder = new MutableLiveData<>();
        saleOrderWithProducts = new MutableLiveData<>();
    }

    @Override
    public LiveData<Boolean> getTaskState() {
        return taskState;
    }

    @Override
    public LiveData<String> getTaskMessage() {
        return taskMessage;
    }

    @Override
    public LiveData<List<SaleOrderEntity>> getAllLiveData() {
        return saleOrderList;
    }

    @Override
    public LiveData<List<SaleOrderWithProducts>> getAllSaleOrderWithProductsLiveData() {
        return saleOrderWithProductsList;
    }

    @Override
    public LiveData<List<SaleOrderWithProductDetails>> getSaleOrderWithProductDetailsLiveData() {
        return saleOrderWithProductDetailsList;
    }

    @Override
    public LiveData<SaleOrderWithProducts> getSaleOrderWithProductsLiveData() {
        return saleOrderWithProducts;
    }

    @Override
    public void getOrderWithProducts(int id) {
        try{
            appExecutor.getExecutorService().execute(new GetSaleOrderWithProductsDBTask(taskState, saleOrderWithProducts,saleOrderDAO,id));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getOrderWithProductDetails(int id) {
        try{
            appExecutor.getExecutorService().execute(new GetSaleOrderWithProductDetailsDBTask(taskState,saleOrderWithProductDetailsList,saleOrderDAO,id));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insert(SaleOrderEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new InsertSaleOrderDBTask(taskState,taskMessage,saleOrderDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertDetail(SaleOrderDetailEntity entity) {
        try{
            appExecutor.getExecutorService().execute(new InsertSaleOrderDetailDBTask(taskState,taskMessage,saleOrderDAO,entity));
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
    public void insertSaleOrderWithDetails(SaleOrderWithProducts entity) {
        try{
            appExecutor.getExecutorService().execute(new InsertSaleOrderWithDetailsDBTask(taskState,taskMessage,saleOrderDAO,entity));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
