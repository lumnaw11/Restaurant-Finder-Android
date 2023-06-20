package com.example.restaurantfinder;

import static com.example.restaurantfinder.R.*;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ResultActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment currentFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bottomNavigationView = findViewById(id.bottomNavi);

        // Set the listener for handling tab selection
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_list:
                    // Show the ShopListFragment
                    showFragment(new ShopListFragment());
                    return true;
                case R.id.action_map:
                    // Show the MapFragment
                    showFragment(new MapsFragment());
                    return true;
            }
            return false;
        });

        // Set the initial fragment to be displayed (ShopListFragment in this case)
        showFragment(new ShopListFragment());
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container, fragment);
        transaction.commit();

        currentFragment = fragment;
    }
}

