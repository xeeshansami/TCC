package com.dk.tengerms.controllers;

import android.os.Bundle;
import android.view.WindowManager;

import com.dk.tengerms.R;
import com.dk.tengerms.network.networkmodels.response.models.Brand;
import com.dk.tengerms.utils.Constants;
import com.dk.tengerms.utils.GlobalClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class Dashboard extends AppCompatActivity {
    public GlobalClass globalClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        globalClass=new GlobalClass();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        if(getIntent().hasExtra(Constants.BRAND)){
            switchFragment(R.id.navigation_brands,getIntent().getExtras().getParcelable(Constants.BRAND));
        }
    }
    private void switchFragment(int startDestId, Brand brand) {
        Bundle bundle=new Bundle();
        bundle.putParcelable(Constants.BRAND,brand);
        NavController navController = Navigation.findNavController(Dashboard.this, R.id.nav_host_fragment);
        navController.navigateUp();
        navController.navigate(startDestId,bundle);
    }
}