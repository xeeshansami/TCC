package com.paxees.tcc.network.apiInterface;

import com.paxees.tcc.network.networkmodels.request.AddToCartRequest;
import com.paxees.tcc.network.networkmodels.request.BrandDetailsRequest;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.request.LoginRequest;
import com.paxees.tcc.network.networkmodels.request.RegistrationRequest;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddressListResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddtoCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiscoveryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetAddToCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.NightTimeUsuageResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PlantsByTypeResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PopularByThisWeekResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CustomerDetailsResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductSearchResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.RegistrationResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateCartResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
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

    @GET("wc/v3/customers/")
    Call<CustomerDetailsResponse> getCustomerDetails(@Query("email") String email);

    @GET("wc/v3/products")
    Call<ProductSearchResponse> getProductSearch(@Query("search") String search);


    @GET("wc/v3/products")
    Call<ProductSearchResponse> getDiscoverProducts(@Query("search") String search,@Query("category") String category);

    @GET("cocart/v1/item")
    Call<UpdateCartResponse> updateCart(@Query("cart_item_key") String search, @Query("quantity") String category);

    @DELETE("cocart/v1/item")
    Call<String> removeCart(@Query("cart_item_key") String search);

    /*@DELETE("apiuser/v1/forgotpwd/dtest@yopmail.com")
    Call<String> removeCart(@Query("cart_item_key") String search);*/

    @GET("wc/v3/customers/{userid}")
    Call<AddressListResponse> getAddressList(@Path("userid") int userid);

    @GET("popular/products")
    Call<PopularByThisWeekResponse> getPopularByThisWeek();

    @GET("cocart/v1/get-cart?thumb=true")
    Call<GetAddToCartResponse> getCarts();

    @GET("discover/discover-menu")
    Call<DiscoveryResponse> getDiscoverMenu();

    @POST("wc/v3/customers")
    Call<RegistrationResponse> getRegister(@Body RegistrationRequest request);

    @POST("dashboard.php")
    Call<BrandByCategoryResponse> getDashboard(@Body DashboardRequest request);

    @POST("cocart/v1/add-item")
    Call<AddtoCartResponse> addToCart(@Body AddToCartRequest request);

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
