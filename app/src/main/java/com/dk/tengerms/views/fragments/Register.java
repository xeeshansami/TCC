package com.dk.tengerms.views.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.dk.tengerms.R;
import com.dk.tengerms.controllers.launcher;
import com.dk.tengerms.network.ResponseHandlers.callbacks.RegisterCallBack;
import com.dk.tengerms.network.enums.RetrofitEnums;
import com.dk.tengerms.network.networkmodels.request.RegisterRequest;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;
import com.dk.tengerms.network.store.TenGermsStore;
import com.dk.tengerms.utils.ToastUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


public class Register extends Fragment implements View.OnClickListener {
    Button bt_registration;
    RegisterRequest request;
    EditText numberEt, promoEt;
    RadioButton maleRB, femaleRB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void init(View view) {
        bt_registration = view.findViewById(R.id.bt_registration);
        maleRB = view.findViewById(R.id.maleRB);
        femaleRB = view.findViewById(R.id.femaleRB);
        numberEt = view.findViewById(R.id.numberEt);
        promoEt = view.findViewById(R.id.promoEt);
        bt_registration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_registration:
                if (validation()) {
                    register();
                }
                break;
        }
    }

    public boolean validation() {
        String email = numberEt.getText().toString().trim();
        String promo = promoEt.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            numberEt.setError("Number should not be empty");
            numberEt.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(promo)) {
            promoEt.setError("Promo code should not be empty");
            promoEt.requestFocus();
            return false;
        }
        if (!maleRB.isChecked() && !femaleRB.isChecked()) {
            ToastUtils.showToastWith(getActivity(), "Please check the gender first", "");
            return false;
        } else {
            return true;
        }
    }


    private void register() {
        String number = numberEt.getText().toString().trim();
        String promo = promoEt.getText().toString().trim();
        ((launcher) getActivity()).globalClass.showDialog(getActivity());
        request = new RegisterRequest();
        request.setNumber(number);
        if (maleRB.isChecked()) {
            request.setGender("Male");
        } else if (femaleRB.isChecked()) {
            request.setGender("Female");
        }
        request.setPromo(promo);
        TenGermsStore.getInstance().getRegister(RetrofitEnums.URL_HBL, request, new RegisterCallBack() {
            @Override
            public void RegisterSuccess(BaseResponse response) {
                ToastUtils.showToastWith(getActivity(), response.getMsg());
                if (response.getStatus()) {
                    NavHostFragment.findNavController(Register.this).navigate(R.id.register_to_login);
                }
                ((launcher) getActivity()).globalClass.hideLoader();
            }

            @Override
            public void RegisterFailure(BaseResponse baseResponse) {
                ToastUtils.showToastWith(getActivity(), baseResponse.getMsg(), "");
                ((launcher) getActivity()).globalClass.hideLoader();
            }
        });
    }

}