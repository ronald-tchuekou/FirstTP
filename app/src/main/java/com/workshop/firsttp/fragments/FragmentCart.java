package com.workshop.firsttp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.workshop.firsttp.R;
import com.workshop.firsttp.activities.CheckoutActivity;
import com.workshop.firsttp.adapters.CartAdapter;
import com.workshop.firsttp.databinding.FragmentCartBinding;
import com.workshop.firsttp.models.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCart extends FragmentHome {

    public static final String EXTRA_CART_CONTENT = "extra_cart_content";

    FragmentCartBinding frag_ui;
    RecyclerView cart_rec;

    public FragmentCart() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static FragmentCart newInstance() {
        FragmentCart fragment = new FragmentCart();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frag_ui = FragmentCartBinding.bind(inflater.inflate(R.layout.fragment_cart, container,
                false));
        initViews();
        return frag_ui.getRoot();
    }

    public void initViews(){
        cart_rec = frag_ui.cartRecycler;
        cart_rec.setOnClickListener(v -> Toast.makeText(getContext(), "Clicked on recycler",
                Toast.LENGTH_LONG).show());
        setContent();
        CartAdapter adapter = new CartAdapter(products);
        cart_rec.setAdapter(adapter);
        cart_rec.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.setOnHomeItemClickListener((position, v) -> {
            Log.i("Fragment Cart", "initViews");
            // TODO
        });

        cart_rec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollListener.onScroll(dy);
            }
        });

        frag_ui.backId.setOnClickListener(v -> scrollListener.onBack());
        frag_ui.checkoutBtnId.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CheckoutActivity.class);
            intent.putExtra(EXTRA_CART_CONTENT, new Product[]{new Product()});
            startActivity(intent);
        });
    }

    public void setContent(){
        products.add(new Product("Seashell Necklace", "Special Design",
                "$15", R.drawable.image1));
        products.add(new Product("Tote Bag", "Special Design",
                "$36", R.drawable.image2));
        products.add(new Product("Scarf", "Special Design",
                "$16", R.drawable.image3));
    }
}