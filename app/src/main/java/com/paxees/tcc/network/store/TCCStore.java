package com.paxees.tcc.network.store;

import android.app.Application;

import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandByCategoryCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandResponseCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.CustomerDetailsCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.NightTimeUsageCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.PlantsByTypeCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.PopularByThisWeekCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.LoginCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.RegisterCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.ResponseHandlers.handler.BrandByCategoryBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.BrandResponseBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.CustomerDetailsBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.NightTimeResponseBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.PopularByThisWeekBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.LoginBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.PlantsByTypeBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.RegisterBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.StrainBaseHR;
import com.paxees.tcc.network.apiInterface.APIInterface;
import com.paxees.tcc.network.enums.RetrofitEnums;
import com.paxees.tcc.network.networkmodels.request.BrandDetailsRequest;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.request.LoginRequest;
import com.paxees.tcc.network.networkmodels.request.RegistrationRequest;
import com.paxees.tcc.network.timeoutInterface.IOnConnectionTimeoutListener;
import com.paxees.tcc.utils.GlobalClass;
import com.paxees.tcc.network.retrofitBuilder.RetrofitBuilder;

public class TCCStore extends Application implements IOnConnectionTimeoutListener {

    private static TCCStore consumerStore;

    //    APIInterface globalBaseUrl = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, RetrofitEnums.URL_HBL);
    public static TCCStore getInstance() {
        if (consumerStore == null)
            return new TCCStore();
        else
            return consumerStore;
    }

    //:TODO post getLogin
    public void getLogin(RetrofitEnums url, LoginRequest loginRequest, LoginCallBack loginCallBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getLogin(loginRequest).enqueue(new LoginBaseHR(loginCallBack));
    }

    //:TODO post getCustomerDetails
    public void getCustomerDetails(RetrofitEnums url, CustomerDetailsCallBack loginCallBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getCustomerDetails().enqueue(new CustomerDetailsBaseHR(loginCallBack));
    }
    //:TODO post getRegister
    public void getRegister(RetrofitEnums url, RegistrationRequest request, RegisterCallBack callBack) {
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

    //:TODO getPopularByThisWeek
    public void getPopularByThisWeek(RetrofitEnums url, PopularByThisWeekCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getPopularByThisWeek().enqueue(new PopularByThisWeekBaseHR(callBack));
    }

 //:TODO getPlantsByType
    public void getPlantsByType(RetrofitEnums url, PlantsByTypeCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getPlantsByType().enqueue(new PlantsByTypeBaseHR(callBack));
    }

    //:TODO getNightTimeUsage
    public void getNightTimeUsage(RetrofitEnums url, NightTimeUsageCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getNightTimeUsage().enqueue(new NightTimeResponseBaseHR(callBack));
    }

    //:TODO getStrains
    public void getStrains(RetrofitEnums url, StrainCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getStrains().enqueue(new StrainBaseHR(callBack));
    }

    @Override
    public void onConnectionTimeout() {

    }
}
