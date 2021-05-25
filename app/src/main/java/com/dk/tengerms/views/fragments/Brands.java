package com.dk.tengerms.views.fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.dk.tengerms.network.networkmodels.request.BrandDetailsRequest;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BaseResponse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dk.tengerms.R;
import com.dk.tengerms.controllers.Dashboard;
import com.dk.tengerms.models.mFilterDashboard;
import com.dk.tengerms.network.ResponseHandlers.callbacks.BrandByCategoryCallBack;
import com.dk.tengerms.network.enums.RetrofitEnums;
import com.dk.tengerms.network.networkmodels.response.baseResponses.BrandDetailResponse;
import com.dk.tengerms.network.networkmodels.response.models.Branch;
import com.dk.tengerms.network.networkmodels.response.models.Brand;
import com.dk.tengerms.network.networkmodels.response.models.Coupon;
import com.dk.tengerms.network.store.TenGermsStore;
import com.dk.tengerms.utils.Constants;
import com.dk.tengerms.utils.SessionManager;
import com.dk.tengerms.utils.ToastUtils;
import com.dk.tengerms.views.adapters.BranchAdapter;
import com.dk.tengerms.views.adapters.CouponsAdapter;

import java.util.ArrayList;

public class Brands extends Fragment implements CouponsAdapter.ItemClickListener, View.OnClickListener {
    private CouponsAdapter couponsAdapter;
    private BranchAdapter branchAdapter;
    RecyclerView rvBranches, rvCoupons;
    BrandDetailsRequest request;
    ImageView backBtn,favBtn,uploadBtn;
    boolean favCheck;
    SessionManager sessionManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brands, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        sessionManager=new SessionManager(getActivity());
        rvCoupons = view.findViewById(R.id.rvCoupons);
        rvBranches = view.findViewById(R.id.rvBranches);
        backBtn = view.findViewById(R.id.backBtn);
        favBtn = view.findViewById(R.id.favBtn);
        uploadBtn = view.findViewById(R.id.uploadBtn);
        backBtn.setOnClickListener(this);
        favBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
        branchesRecyclerView(view);
    }

    public void branchesRecyclerView(View view) {
        ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
        request = new BrandDetailsRequest();
//        if (getArguments() != null && getArguments().containsKey(Constants.BRAND)){
            request = new BrandDetailsRequest();
            request.setBrand_id(((Brand)getArguments().getParcelable(Constants.BRAND)).getBrandId());
//        } else {
//            request.setBrand_id("20");
//        }
        TenGermsStore.getInstance().getBrandsDetails(RetrofitEnums.URL_HBL, request, new BrandByCategoryCallBack() {
            @Override
            public void BrandByCategorySuccess(BrandDetailResponse response) {
                if (response.getStatus()) {
                    // set up the RecyclerView
                    TextView txtRating = view.findViewById(R.id.txtRating);
                    TextView tvBrandName = view.findViewById(R.id.tvBrandName);
                    TextView txtDesc = view.findViewById(R.id.txtDesc);
                    ImageView brandBannerImg = view.findViewById(R.id.brandBannerImg);
                    txtRating.setText(response.getBranddetails().get(0).getRating());
                    tvBrandName.setText(response.getBranddetails().get(0).getBrandName());
                    txtDesc.setText(response.getBranddetails().get(0).getBrandDescription());
                    Glide.with(getActivity()).load(response.getBranddetails().get(0).getBrandBanner()).into(brandBannerImg);
                    couponsRecycler(response.getBranddetails().get(0).getCoupons());
                    branchesRecycler(response.getBranddetails().get(0).getBranches(),response.getBranddetails().get(0).getBrandBanner());
                }
                ((Dashboard) getActivity()).globalClass.hideLoader();
            }

            @Override
            public void BrandByCategoryFailure(BaseResponse baseResponse) {
                ToastUtils.showToastWith(getActivity(), baseResponse.getMsg(), "");
                ((Dashboard) getActivity()).globalClass.hideLoader();
            }
        });


    }

    public void couponsRecycler(ArrayList<Coupon> coupons){
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCoupons.setLayoutManager(horizontalLayoutManagaer);
        couponsAdapter = new CouponsAdapter(getActivity(), coupons);
        couponsAdapter.setClickListener(Brands.this);
        rvCoupons.setAdapter(couponsAdapter);
    }

    public void branchesRecycler(ArrayList<Branch> branches, String brandBanner){
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvBranches.setLayoutManager(horizontalLayoutManagaer);
        branchAdapter = new BranchAdapter(getActivity(), branches,brandBanner);
        rvBranches.setAdapter(branchAdapter);
        branchAdapter.notifyDataSetChanged();
    }

    public void couponsRecyclerView() {
        ArrayList<mFilterDashboard> rec = new ArrayList<>();
        ArrayList<String> txt = new ArrayList<>();
        ArrayList<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.image4);
        imgs.add(R.drawable.image5);
        imgs.add(R.drawable.image2);
        imgs.add(R.drawable.image1);
        imgs.add(R.drawable.image3);
        txt.add("Macdonald's");
        txt.add("Pizza Hut");
        txt.add("Starfish");
        txt.add("Black Fish");
        txt.add("Salaad");
        for (int i = 0; i < imgs.size(); i++) {
            mFilterDashboard filterDashboard = new mFilterDashboard();
            filterDashboard.setImg(imgs.get(i));
            filterDashboard.setTxt(txt.get(i));
            rec.add(filterDashboard);
        }
        // set up the RecyclerView

    }

    public void redeemAlert() {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(R.layout.popup_redeem_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(getActivity()).create();
        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        deleteDialog.setView(deleteDialogView);
        deleteDialogView.findViewById(R.id.bt_redeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((Dashboard) getActivity()).globalClass.hideLoader();
                        successAlert();
                    }
                }, 1500);
                deleteDialog.dismiss();
            }
        });
        deleteDialog.show();
    }

    public void successAlert() {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(R.layout.popup_success_redeem_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(getActivity()).create();
        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        deleteDialog.setView(deleteDialogView);
        deleteDialogView.findViewById(R.id.bt_redeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((Dashboard) getActivity()).globalClass.hideLoader();
                        NavHostFragment.findNavController(Brands.this).navigate(R.id.brands_to_buy);
                    }
                }, 1500);
                deleteDialog.dismiss();
            }
        });
        deleteDialog.show();

    }

    @Override
    public void onItemClick(View view, int position) {
        if(sessionManager.isLoggedIn()) {
            redeemAlert();
        }else{
            ToastUtils.showToastWith(getActivity(),"You are not logged in please login first!");
            ((Dashboard) getActivity()).globalClass.showDialog(getActivity());
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((Dashboard) getActivity()).globalClass.hideLoader();
                    Brand getBrand = getArguments().getParcelable(Constants.BRAND);
                    Bundle bundle=new Bundle();
                    bundle.putInt(Constants.LOGGEDIN,1);
                    bundle.putParcelable(Constants.BRAND,getBrand);
                    NavHostFragment.findNavController(Brands.this).navigate(R.id.brands_to_loggin,bundle);
                }
            }, 1500);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backBtn:
                NavHostFragment.findNavController(Brands.this).popBackStack();
                break;
            case R.id.favBtn:
                if(!favCheck){
                    favCheck=true;
                    favBtn.setImageResource(R.drawable.ic_baseline_favorite_24);
                    ToastUtils.showToastWith(getActivity(),"Brand add in favourites");
                }else{
                    favCheck=false;
                    favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    ToastUtils.showToastWith(getActivity(),"Brand remove from favourites");
                }

                break;
            case R.id.uploadBtn:
                break;
        }
    }
}