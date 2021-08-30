package com.workshop.firsttp.activities;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.transition.MaterialFadeThrough;
import com.workshop.firsttp.R;
import com.workshop.firsttp.databinding.ActivityMainBinding;
import com.workshop.firsttp.fragments.FragmentCart;
import com.workshop.firsttp.fragments.FragmentHome;
import com.workshop.firsttp.fragments.FragmentMore;
import com.workshop.firsttp.fragments.FragmentSearch;

public class MainActivity extends AppCompatActivity {

    public static final String BOTTOM_NAV_STATE_KEY = "bottom_nav_key";
    public static final String CURRENT_BOTTOM_NAV_ITEM_KEY = "current_bottom_nav_item_key";

    ActivityMainBinding binding_ui;
    MaterialCardView bottom_nav_layout;
    BottomNavigationView bottom_nav;

    private boolean bottom_nav_state = true;
    private int current_bottom_item_id = R.id.action_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding_ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding_ui.getRoot());
        if(savedInstanceState != null)
            restoreContent(savedInstanceState);
        initViews();
    }

    /**
     * Function to set the current fragment.
     * @param fragment Current fragment.
     */
    private void replaceFragment(Fragment fragment) {
        fragment.setEnterTransition(createTransition());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    /**
     * Function that manage transition between pages.
     * @return status
     */
    private MaterialFadeThrough createTransition(){
        MaterialFadeThrough fadeThrough = new MaterialFadeThrough();
        fadeThrough.addTarget(R.id.home_fragment);
        fadeThrough.addTarget(R.id.search_fragment);
        fadeThrough.addTarget(R.id.cart_fragment);
        fadeThrough.addTarget(R.id.more_fragment);
        return fadeThrough;
    }

    /**
     * Function to initialize Item clicked in bottom navigation.
     */
    private void setBottomNavItemClick(){
        bottom_nav.setOnItemSelectedListener(item -> {
            setCurrentFragment(item.getItemId());
            return true;
        });
    }

    /**
     * Function that set the current fragment to view.
     * @param id Fragment id.
     */
    private void setCurrentFragment(@IdRes int id){
        boolean show = true;
        FragmentHome frag;
        if(id == R.id.action_home || id == 0)
            frag = FragmentHome.newInstance();
        else if(id == R.id.action_search || id == 1)
            frag = FragmentSearch.newInstance();
        else if(id == R.id.action_cart  || id == 2) {
            frag = FragmentCart.newInstance();
            show = false;
        }else
            frag = FragmentMore.newInstance();

        current_bottom_item_id = id;

        replaceFragment(frag);
        frag.setOnRecycleViewScrollListener(new FragmentHome.OnFragmentHomeChangeListener() {
            @Override
            public void onScroll(int direction) {
                if(direction < 0){
                    showBottomNav();
                }
                if(direction > 0)
                    hideBottomNav();
            }

            @Override
            public void onBack() {
                bottom_nav.setSelectedItemId(R.id.action_home);
                setCurrentFragment(R.id.action_home);
            }
        });

        if(show){
            showBottomNav();
        }else{
            hideBottomNav();
        }

    }

    @Override
    public void onBackPressed() {
        if(current_bottom_item_id == R.id.action_home)
            finish();
        else{
            bottom_nav.setSelectedItemId(R.id.action_home);
            setCurrentFragment(R.id.action_home);
        }
    }

    /**
     * Function to initialize views content.
     */
    private void initViews(){
        bottom_nav_layout = binding_ui.bottomNavLayout;
        bottom_nav = binding_ui.bottomNav;

        // Set current fragment as default.
        setCurrentFragment(current_bottom_item_id);
        bottom_nav.setSelectedItemId(current_bottom_item_id);
        setBottomNavItemClick();
    }

    /**
     * Function to show bottom nav layout.
     */
    public void showBottomNav(){
        if(!bottom_nav_state){
            bottom_nav_layout.setVisibility(View.VISIBLE);
            Animation show = AnimationUtils.loadAnimation(this, R.anim.show_bottom_nav_layout);
            bottom_nav_layout.startAnimation(show);
            bottom_nav_state = true;
        }
    }

    /**
     * Function to hide bottom nav layout.
     */
    public void hideBottomNav(){
        if(bottom_nav_state){
            Animation hide = AnimationUtils.loadAnimation(this, R.anim.hide_bottom_nav_layout);
            bottom_nav_layout.startAnimation(hide);
            bottom_nav_layout.setVisibility(View.INVISIBLE);
            bottom_nav_state = false;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(BOTTOM_NAV_STATE_KEY, bottom_nav_state);
        outState.putInt(CURRENT_BOTTOM_NAV_ITEM_KEY, current_bottom_item_id);
    }

    /**
     * To restore properties.
     * @param savedInstanceState state.
     */
    private void restoreContent(@NonNull Bundle savedInstanceState) {
        bottom_nav_state = savedInstanceState.getBoolean(BOTTOM_NAV_STATE_KEY);
        current_bottom_item_id = savedInstanceState.getInt(CURRENT_BOTTOM_NAV_ITEM_KEY);
    }
}