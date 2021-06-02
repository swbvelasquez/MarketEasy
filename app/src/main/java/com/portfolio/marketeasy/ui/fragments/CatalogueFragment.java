package com.portfolio.marketeasy.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portfolio.marketeasy.R;
import com.portfolio.marketeasy.databinding.FragmentCatalogueBinding;
import com.portfolio.marketeasy.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogueFragment extends Fragment {

    private FragmentCatalogueBinding binding;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCatalogueBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}