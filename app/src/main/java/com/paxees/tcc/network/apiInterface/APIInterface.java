package com.paxees.tcc.network.apiInterface;

import com.paxees.tcc.network.networkmodels.request.BrandDetailsRequest;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.request.LoginRequest;
import com.paxees.tcc.network.networkmodels.request.RegisterRequest;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse;
import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @NotNull
    String HEADER_TAG = "@";
    @NotNull
    String HEADER_POSTFIX = ": ";
    @NotNull
    String HEADER_TAG_PUBLIC = "public";

    @POST("login.php")
    Call<LoginResponse> getLogin(@Body LoginRequest request);

    @POST("register.php")
    Call<BaseResponse> getRegister(@Body RegisterRequest request);

    @POST("dashboard.php")
    Call<BrandByCategoryResponse> getDashboard(@Body DashboardRequest request);

    @POST("popularbrands.php")
    Call<BrandByCategoryResponse> getPopularBrands(@Body DashboardRequest request);

    @POST("branddetails.php")
    @Headers("Content-Type: text/plain")
    Call<BrandDetailResponse> getBrandsDetails(@Body BrandDetailsRequest request);

    @GET("categories.php")
    @Headers("Content-Type: text/plain")
    Call<CategoryResponse> getCategories();
}
