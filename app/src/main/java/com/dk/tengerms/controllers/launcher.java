package com.dk.tengerms.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.WindowManager;

import com.dk.tengerms.R;
import com.dk.tengerms.network.networkmodels.response.models.Brand;
import com.dk.tengerms.utils.Constants;
import com.dk.tengerms.utils.GlobalClass;
import com.dk.tengerms.views.fragments.Brands;
import com.facebook.FacebookSdk;

import java.util.zip.Inflater;

public class launcher extends AppCompatActivity {
    public GlobalClass globalClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       init();
    }

    private void init() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        globalClass=new GlobalClass();
        if(getIntent().hasExtra(Constants.LOGGEDIN)){
         switchFragment(R.id.login,getIntent().getExtras().getParcelable(Constants.BRAND));
        }
    }

    private void switchFragment(int startDestId, Brand brand) {
        Bundle bundle=new Bundle();
        bundle.putParcelable(Constants.BRAND,brand);
        NavController navController = Navigation.findNavController(launcher.this, R.id.nav_host_fragment);
        navController.navigateUp();
        navController.navigate(startDestId,bundle);
    }
}