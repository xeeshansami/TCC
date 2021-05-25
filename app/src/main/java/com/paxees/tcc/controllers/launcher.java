package com.paxees.tcc.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.WindowManager;

import com.paxees.tcc.R;
import com.paxees.tcc.network.networkmodels.response.models.Brand;
import com.paxees.tcc.utils.Constants;
import com.paxees.tcc.utils.GlobalClass;
import com.facebook.FacebookSdk;

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