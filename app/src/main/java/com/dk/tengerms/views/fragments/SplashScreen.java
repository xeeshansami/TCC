package com.dk.tengerms.views.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.tengerms.R;
import com.dk.tengerms.utils.Constants;
import com.dk.tengerms.utils.SessionManager;
import com.dk.tengerms.utils.ToastUtils;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class SplashScreen extends Fragment {
    SessionManager sessionManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}; // List of permissions required

    public void askPermission()
    {
        for (String permission : PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS, PERMISSION_ALL);
                return;
            }else{
                doWork();
            }

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //Do your work.
                    doWork();
                } else {
                    ToastUtils.showToastWith(getActivity(), "Until you grant the permission, we cannot proceed further", "");
                }
                return;
            }
        }
    }

    private void doWork() {
        if (!sessionManager.isSliders()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    NavHostFragment.findNavController(SplashScreen.this).navigate(R.id.splash_to_slider);
                }
            }, Constants.SPLASH_DURATION);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    NavHostFragment.findNavController(SplashScreen.this).navigate(R.id.splash_to_dashboard);
                }
            }, Constants.SPLASH_DURATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionManager = new SessionManager(getActivity());
        askPermission();
    }
}