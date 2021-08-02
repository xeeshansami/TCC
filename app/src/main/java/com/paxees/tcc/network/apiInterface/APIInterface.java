package com.paxees.tcc.network.apiInterface;

import com.paxees.tcc.network.networkmodels.request.BrandDetailsRequest;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.request.LoginRequest;
import com.paxees.tcc.network.networkmodels.request.RegistrationRequest;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.NightTimeUsuageResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PlantsByTypeResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PopularByThisWeekResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CustomerDetailsResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @NotNull
    String HEADER_TAG = "@";
    @NotNull
    String HEADER_POSTFIX = ": ";
    @NotNull
    String HEADER_TAG_PUBLIC = "public";

    @POST("jwt-auth/v1/token")
    Call<LoginResponse> getLogin(@Body LoginRequest request);

    @GET("wc/v3/customers/?email=devtest@gmail.com")
    Call<CustomerDetailsResponse> getCustomerDetails();

    @GET("popular/products")
    Call<PopularByThisWeekResponse> getPopularByThisWeek();

    @POST("register.php")
    Call<BaseResponse> getRegister(@Body RegistrationRequest request);

    @POST("dashboard.php")
    Call<BrandByCategoryResponse> getDashboard(@Body DashboardRequest request);

    @POST("popularbrands.php")
    Call<BrandByCategoryResponse> getPopularBrands(@Body DashboardRequest request);

    @GET("strain/strain-list")
    Call<StrainResponse> getStrains();

    @POST("branddetails.php")
    Call<BrandDetailResponse> getBrandsDetails(@Body BrandDetailsRequest request);

    @GET("product/product-list-count")
    Call<PlantsByTypeResponse> getPlantsByType();

    @GET("products/night-time-usage")
    Call<NightTimeUsuageResponse> getNightTimeUsage();

}
