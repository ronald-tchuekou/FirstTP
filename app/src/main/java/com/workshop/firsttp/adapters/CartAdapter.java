package com.workshop.firsttp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.workshop.firsttp.R;
import com.workshop.firsttp.models.Product;

import java.util.ArrayList;

public class CartAdapter extends HomeAdapter{

    public CartAdapter(ArrayList<Product> products) {
        super(products);
    }

    @NonNull
    @Override
    public HomeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        setContext(parent.getContext());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cart, parent,
                false);
        return new HomeItemHolder(v, listener);
    }
}
