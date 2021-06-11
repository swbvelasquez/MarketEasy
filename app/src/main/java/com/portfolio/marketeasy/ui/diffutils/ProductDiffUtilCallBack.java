package com.portfolio.marketeasy.ui.diffutils;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.portfolio.marketeasy.core.entities.ProductEntity;

public class ProductDiffUtilCallBack extends DiffUtil.ItemCallback<ProductEntity> {

    @Override
    public boolean areItemsTheSame(@NonNull ProductEntity oldItem, @NonNull ProductEntity newItem) {
        return oldItem.getProductId() == newItem.getProductId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull ProductEntity oldItem, @NonNull ProductEntity newItem) {
        return oldItem.getName().equals(newItem.getName())
                && oldItem.getBrand().equals(newItem.getBrand())
                && oldItem.getDescription().equals(newItem.getDescription())
                && oldItem.getPrice() == newItem.getPrice()
                && oldItem.getStock() == newItem.getStock()
                && oldItem.getExpirationDate().equals(newItem.getExpirationDate())
                && oldItem.getSaleOrderDetailId() == newItem.getSaleOrderDetailId()
                && oldItem.getUrlImage().equals(newItem.getUrlImage());
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull ProductEntity oldItem, @NonNull ProductEntity newItem) {
        return super.getChangePayload(oldItem, newItem);
    }
}
