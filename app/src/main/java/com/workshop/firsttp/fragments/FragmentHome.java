package com.workshop.firsttp.fragments;

import static androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.workshop.firsttp.R;
import com.workshop.firsttp.activities.ProductActivity;
import com.workshop.firsttp.adapters.HomeAdapter;
import com.workshop.firsttp.databinding.FragmentHomeBinding;
import com.workshop.firsttp.models.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    public static final String EXTRA_PRODUCT = "extra_product";

    FragmentHomeBinding frag_ui;
    OnFragmentHomeChangeListener scrollListener;
    RecyclerView home_rec;
    ArrayList<Product> products = new ArrayList<>();

    public interface OnFragmentHomeChangeListener {
        void onScroll(int direction);
        void onBack();
    }

    public void setOnRecycleViewScrollListener(OnFragmentHomeChangeListener scrollListener){
        this.scrollListener = scrollListener;
    }
    public FragmentHome() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     **/
    public static FragmentHome newInstance() {
        return new FragmentHome();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frag_ui = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container,
                false));
        initViews();
        return frag_ui.getRoot();
    }

    public void initViews(){
        home_rec = frag_ui.homeRecycler;
        home_rec.setOnClickListener(v -> Toast.makeText(getContext(), "Clicked on recycler",
                Toast.LENGTH_LONG).show());
        setContent();
        HomeAdapter adapter = new HomeAdapter(products);
        home_rec.setAdapter(adapter);
        home_rec.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //home_rec.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));

        adapter.setOnHomeItemClickListener((position, view) -> {
            ActivityOptionsCompat activityOptions = makeSceneTransitionAnimation(requireActivity(), view,
                    requireContext().getResources().getString(R.string.transition_image_name));
            Intent intent = new Intent(getContext(), ProductActivity.class);
            intent.putExtra(EXTRA_PRODUCT, products.get(position));
            startActivity(intent, activityOptions.toBundle());
        });

        home_rec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollListener.onScroll(dy);
            }
        });
    }

    public void setContent(){
        products.add(new Product("Seashell Necklace", "Special Design",
                "1,500 XFA", R.drawable.image1));
        products.add(new Product("Tote Bag", "Special Design",
                "1,000 XFA", R.drawable.image2));
        products.add(new Product("Scarf", "Special Design",
                "1,200 XFA", R.drawable.image3));
        products.add(new Product("Metal Earrings", "Special Design",
                "3,500 XFA", R.drawable.image4));
        products.add(new Product("Glasses", "Special Design",
                "5,500 XFA", R.drawable.image2));
        products.add(new Product("Seashell Necklace", "Special Design",
                "1,000 XFA", R.drawable.image1));
        products.add(new Product("Tote Bag", "Special Design",
                "10,500 XFA", R.drawable.image3));
        products.add(new Product("Scarf", "Special Design",
                "9,500 XFA", R.drawable.image4));
        products.add(new Product("Metal Earrings", "Special Design",
                "3,000 XFA", R.drawable.image1));
        products.add(new Product("Glasses", "Special Design",
                "7,500 XFA", R.drawable.image2));
        products.add(new Product("Seashell Necklace", "Special Design",
                "1,900 XFA", R.drawable.image3));
        products.add(new Product("Tote Bag", "Special Design",
                "4,000 XFA", R.drawable.image4));
        products.add(new Product("Scarf", "Special Design",
                "5,700 XFA", R.drawable.image1));
        products.add(new Product("Metal Earrings", "Special Design",
                "12,500 XFA", R.drawable.image2));
        products.add(new Product("Glasses", "Special Design",
                "1,500 XFA", R.drawable.image3));
        products.add(new Product("Seashell Necklace", "Special Design",
                "80,500 XFA", R.drawable.image1));
        products.add(new Product("Tote Bag", "Special Design",
                "100,500 XFA", R.drawable.image2));
        products.add(new Product("Scarf", "Special Design",
                "500,000 XFA", R.drawable.image3));
    }
}