package com.workshop.firsttp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.workshop.firsttp.R;
import com.workshop.firsttp.databinding.LayoutItemHomeBinding;
import com.workshop.firsttp.models.Product;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeItemHolder> {

    OnHomeItemClickListener listener;
    private final ArrayList<Product> products;
    private Context context;

    public void setContext(Context context){
        this.context = context;
    }
    public HomeAdapter(ArrayList<Product> products){
        this.products = products;
    }

    public void setOnHomeItemClickListener(OnHomeItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_home, parent,
                false);
        return new HomeItemHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemHolder holder, int position) {
        Product product = products.get(position);
        holder.product_image.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),
                product.getImage(), null));
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice());
        holder.description.setText(product.getDescription());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface OnHomeItemClickListener{
        void onClick(int position, View view);
    }

    static class HomeItemHolder extends RecyclerView.ViewHolder{

        LayoutItemHomeBinding item_ui;
        ImageView product_image;
        TextView name, description, price;

        public HomeItemHolder(@NonNull View itemView, OnHomeItemClickListener listener) {
            super(itemView);
            item_ui = LayoutItemHomeBinding.bind(itemView);

            product_image = item_ui.productImgId;
            name = item_ui.productNameId;
            description = item_ui.productDescriptionId;
            price = item_ui.productPriceId;

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    listener.onClick(position, v);
                }
            });
        }
    }
}
