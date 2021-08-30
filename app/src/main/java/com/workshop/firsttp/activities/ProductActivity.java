package com.workshop.firsttp.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.workshop.firsttp.databinding.ActivityProductBinding;
import com.workshop.firsttp.fragments.FragmentHome;
import com.workshop.firsttp.models.Product;

public class ProductActivity extends AppCompatActivity {

    ActivityProductBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        Product product = getIntent().getParcelableExtra(FragmentHome.EXTRA_PRODUCT);

        ui.productName.setText(product.getName());
        ui.productDesc.setText(product.getDescription());
        ui.productPrice.setText(product.getPrice());
        ui.productImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                product.getImage(), null));
    }

    public void back(View view) {
        onBackPressed();
    }
}