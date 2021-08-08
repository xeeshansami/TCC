package com.paxees.tcc.network.store;

import android.app.Application;

import com.paxees.tcc.network.ResponseHandlers.callbacks.AddToCartCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.AddressListCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandByCategoryCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.BrandResponseCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.CustomerDetailsCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.DiscoveryMenuCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.ForgetPasswordCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.GetCartsCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.NightTimeUsageCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.PlantsByTypeCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.PopularByThisWeekCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.LoginCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.ProductSearchCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.RegistrationCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.RemoveCartCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateCartCallBack;
import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateProfileCallBack;
import com.paxees.tcc.network.ResponseHandlers.handler.AddToCartBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.AddressListBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.BrandByCategoryBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.BrandResponseBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.CustomerDetailsBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.DiscoverMenuBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.ForgetPasswordBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.GetCartsBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.NightTimeResponseBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.PopularByThisWeekBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.LoginBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.PlantsByTypeBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.ProductSearchBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.RegisterBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.RemoveCartBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.StrainBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.UpdateCartBaseHR;
import com.paxees.tcc.network.ResponseHandlers.handler.UpdateProfileBaseHR;
import com.paxees.tcc.network.apiInterface.APIInterface;
import com.paxees.tcc.network.enums.RetrofitEnums;
import com.paxees.tcc.network.networkmodels.request.AddToCartRequest;
import com.paxees.tcc.network.networkmodels.request.BrandDetailsRequest;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.request.LoginRequest;
import com.paxees.tcc.network.networkmodels.request.RegistrationRequest;
import com.paxees.tcc.network.networkmodels.request.UpdateProfileRequest;
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
    public void getCustomerDetails(RetrofitEnums url,String email, CustomerDetailsCallBack loginCallBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getCustomerDetails(email).enqueue(new CustomerDetailsBaseHR(loginCallBack));
    }

    //:TODO post getProductSearch
    public void getProductSearch(RetrofitEnums url,String email, ProductSearchCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getProductSearch(email).enqueue(new ProductSearchBaseHR(callBack));
    }

    //:TODO post getDiscoverProducts
    public void getDiscoverProducts(RetrofitEnums url,String search,String catid, ProductSearchCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getDiscoverProducts(search,catid).enqueue(new ProductSearchBaseHR(callBack));
    }

    //:TODO post updateCart
    public void updateCart(RetrofitEnums url,String key,String quantity, UpdateCartCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.updateCart(key,quantity).enqueue(new UpdateCartBaseHR(callBack));
    }

  //:TODO post removeCart
    public void removeCart(RetrofitEnums url,String key, RemoveCartCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.removeCart(key).enqueue(new RemoveCartBaseHR(callBack));
    }

    //:TODO post getForgetPassword
    public void getForgetPassword(RetrofitEnums url,String key, ForgetPasswordCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getForgetPassword(key).enqueue(new ForgetPasswordBaseHR(callBack));
    }

    //:TODO post getAddressList
    public void getAddressList(RetrofitEnums url,int email, AddressListCallBack loginCallBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getAddressList(email).enqueue(new AddressListBaseHR(loginCallBack));
    }

    //:TODO post profileUpdate
    public void profileUpdate(RetrofitEnums url, String userID, UpdateProfileRequest request, UpdateProfileCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.profileUpdate(userID,request).enqueue(new UpdateProfileBaseHR(callBack));
    }
    //:TODO post getRegister
    public void getRegister(RetrofitEnums url, RegistrationRequest request, RegistrationCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getRegister(request).enqueue(new RegisterBaseHR(callBack));
    }

    //:TODO post getDashboard
    public void getDashboard(RetrofitEnums url, DashboardRequest request, BrandResponseCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getDashboard(request).enqueue(new BrandResponseBaseHR(callBack));
    }

    //:TODO post addToCart
    public void addToCart(RetrofitEnums url, AddToCartRequest request, AddToCartCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.addToCart(request).enqueue(new AddToCartBaseHR(callBack));
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

    //:TODO getCarts
    public void getCarts(RetrofitEnums url, GetCartsCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getCarts().enqueue(new GetCartsBaseHR(callBack));
    }

    //:TODO getDiscoverMenu
    public void getDiscoverMenu(RetrofitEnums url, DiscoveryMenuCallBack callBack) {
        APIInterface privateInstanceRetrofit = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, url);
        privateInstanceRetrofit.getDiscoverMenu().enqueue(new DiscoverMenuBaseHR(callBack));
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
