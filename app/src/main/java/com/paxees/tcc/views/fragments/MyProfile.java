package com.paxees.tcc.views.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.paxees.tcc.R;
import com.paxees.tcc.controllers.Dashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


public class MyProfile extends Fragment implements View.OnClickListener {
    Button btn_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myprofile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void init(View view) {
        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                login();
                break;

        }
    }

    private void login() {
        ((Dashboard)getActivity()).globalClass.showDialog(getActivity());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Dashboard)getActivity()).globalClass.hideLoader();
                NavHostFragment.findNavController(MyProfile.this).navigate(R.id.profile_to_myProfile);
            }
        }, 1500);
    }
}