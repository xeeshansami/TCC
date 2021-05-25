package com.dk.tengerms.network.store;

import android.app.Application;

import com.dk.tengerms.network.ResponseHandlers.callbacks.BrandByCategoryCallBack;
import com.dk.tengerms.network.ResponseHandlers.callbacks.BrandResponseCallBack;
import com.dk.tengerms.network.ResponseHandlers.callbacks.CategoryCallBack;
import com.dk.tengerms.network.ResponseHandlers.callbacks.LoginCallBack;
import com.dk.tengerms.network.ResponseHandlers.callbacks.RegisterCallBack;
import com.dk.tengerms.network.ResponseHandlers.handler.BrandByCategoryBaseHR;
import com.dk.tengerms.network.ResponseHandlers.handler.BrandResponseBaseHR;
import com.dk.tengerms.network.ResponseHandlers.handler.CategoryBaseHR;
import com.dk.tengerms.network.ResponseHandlers.handler.LoginBaseHR;
import com.dk.tengerms.network.ResponseHandlers.handler.RegisterBaseHR;
import com.dk.tengerms.network.apiInterface.APIInterface;
import com.dk.tengerms.network.enums.RetrofitEnums;
import com.dk.tengerms.network.networkmodels.request.BrandDetailsRequest;
import com.dk.tengerms.network.networkmodels.request.DashboardRequest;
import com.dk.tengerms.network.networkmodels.request.LoginRequest;
import com.dk.tengerms.network.networkmodels.request.BrandByCategoryRequest;
import com.dk.tengerms.network.networkmodels.request.RegisterRequest;
import com.dk.tengerms.network.timeoutInterface.IOnConnectionTimeoutListener;
import com.dk.tengerms.utils.GlobalClass;
import com.dk.tengerms.network.retrofitBuilder.RetrofitBuilder;

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
