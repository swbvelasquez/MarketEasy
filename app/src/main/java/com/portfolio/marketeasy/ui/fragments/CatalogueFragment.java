package com.portfolio.marketeasy.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.portfolio.marketeasy.R;
import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.interfaces.OnClickListener;
import com.portfolio.marketeasy.databinding.FragmentCatalogueBinding;
import com.portfolio.marketeasy.databinding.FragmentHomeBinding;
import com.portfolio.marketeasy.ui.adapters.CatalogueRecyclerAdapter;
import com.portfolio.marketeasy.ui.viewmodels.CatalogueViewModel;

import java.util.List;


public class CatalogueFragment extends Fragment {

    private FragmentCatalogueBinding binding;
    private CatalogueViewModel catalogueViewModel;
    private CatalogueRecyclerAdapter catalogueRecyclerAdapter;
    private int idOrder=0;

    public CatalogueFragment() {

    }

    public static CatalogueFragment newInstance(Bundle bundle) {
        CatalogueFragment fragment = new CatalogueFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCatalogueBinding.inflate(inflater,container,false);

        //Configurar el recycler
        catalogueRecyclerAdapter = new CatalogueRecyclerAdapter();
        catalogueRecyclerAdapter.setOnClickListener(new OnClickListener<ProductEntity>() {
            @Override
            public void onClick(ProductEntity entity) {
                catalogueViewModel.addProductToCar(entity,idOrder);
            }
        });

        binding.recyclerProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerProducts.setHasFixedSize(true);
        binding.recyclerProducts.setAdapter(catalogueRecyclerAdapter);

        //Configurar los observers
        catalogueViewModel = new ViewModelProvider(this).get(CatalogueViewModel.class);

        catalogueViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(List<ProductEntity> productEntities) {
                //update recycler
            }
        });

        catalogueViewModel.getSaleOrderTaskState().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //show or hide progress bar
                if(aBoolean) {
                    Toast.makeText(getActivity(), "Processing Order Thread", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Terminated Order Thread", Toast.LENGTH_SHORT).show();
                }
            }
        });

        catalogueViewModel.getSaleOrderTaskMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null && !s.equals("")) {
                    int requestCode = Integer.parseInt(s.split("-")[0]);
                    int resultProcess = Integer.parseInt(s.split("-")[1]);
                    int result = Integer.parseInt(s.split("-")[2]);
                    String message = s.split("-")[3];
                    if(resultProcess==1) {
                        if (requestCode == 100) { // insert order with products first time
                            idOrder = result;
                            Log.d("onSOMessage", "onChanged: Order Register With Id " + idOrder);
                        } else {
                            if (requestCode == 101) { // add to order a product
                                Log.d("onSOMessage", "onChanged: Product Added With Id " + result);
                            }
                        }
                        catalogueViewModel.refreshSaleOrder(idOrder);
                    }
                }
            }
        });

        return binding.getRoot();
    }
}