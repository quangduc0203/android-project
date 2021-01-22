package com.example.project.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.project.R;
import com.example.project.fragment.FragmentAccount;
import com.example.project.fragment.FragmentNews;
import com.example.project.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScreenActivity extends AppCompatActivity {
    private ImageButton phone;
    private ImageButton laptop;
    private ImageButton smartwatch;
    private ImageButton tablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        loadFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.account:
                        fragment = new FragmentAccount();
                        loadFragment(fragment);
                        return true;
                    case R.id.home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.add_product:
                        fragment = new FragmentNews();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}