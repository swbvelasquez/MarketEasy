package com.portfolio.marketeasy.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.portfolio.marketeasy.R;
import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.databinding.FragmentCatalogueBinding;
import com.portfolio.marketeasy.databinding.FragmentHomeBinding;
import com.portfolio.marketeasy.ui.viewmodels.CatalogueViewModel;

import java.util.List;


public class CatalogueFragment extends Fragment {

    private FragmentCatalogueBinding binding;
    private CatalogueViewModel catalogueViewModel;

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

        catalogueViewModel = new ViewModelProvider(this).get(CatalogueViewModel.class);

        catalogueViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(List<ProductEntity> productEntities) {
                //update recycler
            }
        });

        catalogueViewModel.getProductTaskState().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //show or hide progress bar
                if(aBoolean) {
                    Toast.makeText(getActivity(), "Processing Thread", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Terminated Thread", Toast.LENGTH_SHORT).show();
                }
                Log.d("progress bar task state", "onChanged: " + aBoolean);
            }
        });

        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogueViewModel.addProductToCar(new ProductEntity());
            }
        });

        return binding.getRoot();
    }
}