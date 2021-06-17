package com.portfolio.marketeasy.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portfolio.marketeasy.R;
import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.interfaces.OnClickListener;
import com.portfolio.marketeasy.databinding.RowRecyclerProductBinding;

import java.util.ArrayList;
import java.util.List;

public class CatalogueRecyclerAdapter extends RecyclerView.Adapter<CatalogueRecyclerAdapter.CatalogueViewHolder> {

    private List<ProductEntity> productList;
    private OnClickListener<ProductEntity> onClickListener;

    public CatalogueRecyclerAdapter() {
        productList=new ArrayList<>();
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public OnClickListener<ProductEntity> getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener<ProductEntity> onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CatalogueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRecyclerProductBinding binding = RowRecyclerProductBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CatalogueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogueViewHolder holder, int position) {
        holder.onBindViewHolder(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class CatalogueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RowRecyclerProductBinding binding;

        public CatalogueViewHolder(RowRecyclerProductBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.buttonAdd.setOnClickListener(this);
        }

        public void onBindViewHolder(ProductEntity entity){
            String price = "S/. " + entity.getPrice();

            binding.imageViewProduct.setImageResource(R.drawable.ic_nav_header);
            binding.textViewName.setText(entity.getName());
            binding.textViewBrand.setText(entity.getBrand());
            binding.textViewPrice.setText(price);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(productList.get(getAdapterPosition()));
        }
    }
}
