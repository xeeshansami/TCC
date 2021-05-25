package com.dk.tengerms.views.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dk.tengerms.R;
import com.dk.tengerms.controllers.Dashboard;
import com.dk.tengerms.utils.SessionManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


public class Profile extends Fragment implements View.OnClickListener {
    TextView tvCoupons,tvChangePwd,tvMyProfile,tvReferAFriend,tvCouponsRedemption,tvLogout;
    SessionManager sessionManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void init(View view) {
        sessionManager=new SessionManager(getActivity());
        tvCoupons=view.findViewById(R.id.tvCoupons);
        tvChangePwd=view.findViewById(R.id.tvChangePwd);
        tvMyProfile=view.findViewById(R.id.tvMyProfile);
        tvReferAFriend=view.findViewById(R.id.tvReferAFriend);
        tvCouponsRedemption=view.findViewById(R.id.tvCouponsRedemption);
        tvLogout=view.findViewById(R.id.tvLogout);
        tvCoupons.setOnClickListener(this);
        tvChangePwd.setOnClickListener(this);
        tvMyProfile.setOnClickListener(this);
        tvReferAFriend.setOnClickListener(this);
        tvCouponsRedemption.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCoupons:
                openCoupons();
                break;
                case R.id.tvChangePwd:
                changePwd();
                break;
                case R.id.tvMyProfile:
                myProfile();
                break;
                case R.id.tvReferAFriend:
                referAFriend();
                break;
                case R.id.tvCouponsRedemption:
                couponRedemption();
                break;
                case R.id.tvLogout:
                logout();
                break;
        }
    }

    private void logout() {
        ((Dashboard)getActivity()).globalClass.showDialog(getActivity());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Dashboard)getActivity()).globalClass.hideLoader();
                sessionManager.setLogin(false);
                getActivity().finish();
                NavHostFragment.findNavController(Profile.this).navigate(R.id.profile_to_logout_loginscreen);
            }
        }, 1500);

    }

    private void couponRedemption() {
        ((Dashboard)getActivity()).globalClass.showDialog(getActivity());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Dashboard)getActivity()).globalClass.hideLoader();
                NavHostFragment.findNavController(Profile.this).navigate(R.id.profile_to_coupon_redemption);
            }
        }, 1500);
    }

    private void referAFriend() {
        ((Dashboard)getActivity()).globalClass.showDialog(getActivity());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Dashboard)getActivity()).globalClass.hideLoader();
                NavHostFragment.findNavController(Profile.this).navigate(R.id.profile_to_refer_a_friend);
            }
        }, 1500);
    }

    private void myProfile() {
        ((Dashboard)getActivity()).globalClass.showDialog(getActivity());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Dashboard)getActivity()).globalClass.hideLoader();
                NavHostFragment.findNavController(Profile.this).navigate(R.id.profile_to_myProfile);
            }
        }, 1500);
    }

    private void changePwd() {
            ((Dashboard)getActivity()).globalClass.showDialog(getActivity());
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((Dashboard)getActivity()).globalClass.hideLoader();
                    NavHostFragment.findNavController(Profile.this).navigate(R.id.profile_to_change_pwd);
                }
            }, 1500);
    }

    private void openCoupons() {
        ((Dashboard)getActivity()).globalClass.showDialog(getActivity());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Dashboard)getActivity()).globalClass.hideLoader();
                NavHostFragment.findNavController(Profile.this).navigate(R.id.profile_to_checkout);
            }
        }, 1500);
    }
}