package com.paxees.tcc.network.store;

import android.app.Application;

import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandByCategoryCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandResponseCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.CategoryCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.LoginCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.RegisterCallBack;
import com.paxees.tcc.network.ResponseHandlers.handler.BrandByCategoryBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.BrandResponseBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.CategoryBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.LoginBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.RegisterBaseHR;
import com.paxees.tcc.network.apiInterface.APIInterface;
import com.paxees.tcc.network.enums.RetrofitEnums;
import com.paxees.tcc.network.networkmodels.request.BrandDetailsRequest;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.request.LoginRequest;
import com.paxees.tcc.network.networkmodels.request.RegisterRequest;
import com.paxees.tcc.network.timeoutInterface.IOnConnectionTimeoutListener;
import com.paxees.tcc.utils.GlobalClass;
import com.paxees.tcc.network.retrofitBuilder.RetrofitBuilder;

public class TenGermsStore extends Application implements IOnConnectionTimeoutListener {

    private static TenGermsStore consumerStore;

    //    APIInterface globalBaseUrl = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, RetrofitEnums.URL_HBL);
    public static TenGermsStore getInstance() {
        if (consumerStore == null)
            return new TenGermsStore();
        else
            return consumerStore;
    }

    //:TODO post getLogin
    public void getLogin(RetrofitEnums url, LoginRequest loginRequest, LoginCallBack loginCallBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getLogin(loginRequest).enqueue(new LoginBaseHR(loginCallBack));
    }
    //:TODO post getRegister
    public void getRegister(RetrofitEnums url, RegisterRequest request, RegisterCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getRegister(request).enqueue(new RegisterBaseHR(callBack));
    }

    //:TODO post getDashboard
    public void getDashboard(RetrofitEnums url, DashboardRequest request, BrandResponseCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getDashboard(request).enqueue(new BrandResponseBaseHR(callBack));
    }

    //:TODO post getPopularBrands
    public void getPopularBrands(RetrofitEnums url, DashboardRequest request, BrandResponseCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getPopularBrands(request).enqueue(new BrandResponseBaseHR(callBack));
    }

    //:TODO post getBrandByCategory
    public void getBrandsDetails(RetrofitEnums url, BrandDetailsRequest request, BrandByCategoryCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getBrandsDetails(request).enqueue(new BrandByCategoryBaseHR(callBack));
    }

    //:TODO getCategories
    public void getCategories(RetrofitEnums url,  CategoryCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getCategories().enqueue(new CategoryBaseHR(callBack));
    }

    @Override
    public void onConnectionTimeout() {

    }
}
