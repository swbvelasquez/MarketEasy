package com.portfolio.marketeasy.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.entities.relations.SaleOrderWithProducts;
import com.portfolio.marketeasy.core.interfaces.IProductRepository;
import com.portfolio.marketeasy.core.interfaces.ISaleOrderRepository;
import com.portfolio.marketeasy.infrastructure.repositories.ProductRepository;
import com.portfolio.marketeasy.infrastructure.repositories.SaleOrderRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CatalogueViewModel extends AndroidViewModel {
    private IProductRepository productRepository;
    private ISaleOrderRepository saleOrderRepository;
    private LiveData<List<ProductEntity>> productList;
    private LiveData<Boolean> productTaskState;
    private LiveData<Boolean> saleOrderTaskState;
    private LiveData<String> saleOrderTaskMessage;
    private LiveData<SaleOrderWithProducts> saleOrderWithProducts;

    public CatalogueViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        saleOrderRepository = new SaleOrderRepository(application);
        productList = productRepository.getAll();
        productTaskState = productRepository.getTaskState();
        saleOrderTaskState = saleOrderRepository.getTaskState();
        saleOrderTaskMessage = saleOrderRepository.getTaskMessage();
        saleOrderWithProducts = saleOrderRepository.getSaleOrderWithProductsLiveData();
    }

    public void addProductToCar(ProductEntity productEntity, int id){
        try {
            if(id==0){
                createSaleOrder(productEntity);
            }else{
                addProductToSaleOrder(productEntity,id);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void addProductToSaleOrder(ProductEntity productEntity, int id){
        SaleOrderDetailEntity saleOrderDetail = new SaleOrderDetailEntity();
        saleOrderDetail.setSaleOrderId(id);
        saleOrderDetail.setProductId(productEntity.getProductId());
        saleOrderDetail.setPrice(productEntity.getPrice());
        saleOrderDetail.setQuantity(1);

        saleOrderRepository.insertDetail(saleOrderDetail);
    }

    private void createSaleOrder(ProductEntity productEntity){
        SaleOrderWithProducts saleOrder = new SaleOrderWithProducts();

        SaleOrderEntity saleOrderEntity = new SaleOrderEntity();
        saleOrderEntity.setUserId(1);
        saleOrderEntity.setActive(true);
        saleOrderEntity.setRegisterDate(Calendar.getInstance().getTime());

        saleOrder.setSaleOrder(saleOrderEntity);
        saleOrder.setSaleOrderProductList(new ArrayList<>());
        saleOrder.getSaleOrderProductList().add(productEntity);

        saleOrderRepository.insertSaleOrderWithDetails(saleOrder);
    }

    public void refreshSaleOrder(int id){
        saleOrderRepository.getOrderWithProducts(id);
    }

    public LiveData<Boolean> getProductTaskState(){
        return productTaskState;
    }

    public LiveData<Boolean> getSaleOrderTaskState(){
        return saleOrderTaskState;
    }

    public LiveData<String> getSaleOrderTaskMessage(){
        return saleOrderTaskMessage;
    }

    public LiveData<List<ProductEntity>> getAllProducts(){
        return productList;
    }
}
