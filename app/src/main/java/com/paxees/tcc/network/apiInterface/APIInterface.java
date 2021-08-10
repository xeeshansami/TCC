package com.paxees.tcc.network.apiInterface;

import com.paxees.tcc.network.networkmodels.request.AddToCartRequest;
import com.paxees.tcc.network.networkmodels.request.AddToWishlistRequest;
import com.paxees.tcc.network.networkmodels.request.BrandDetailsRequest;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.request.LoginRequest;
import com.paxees.tcc.network.networkmodels.request.RegistrationRequest;
import com.paxees.tcc.network.networkmodels.request.UpdateAddress2Request;
import com.paxees.tcc.network.networkmodels.request.UpdateAddressRequest;
import com.paxees.tcc.network.networkmodels.request.UpdateCartRequest;
import com.paxees.tcc.network.networkmodels.request.UpdateProfileRequest;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddToWishlistResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddtoCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiscoveryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ForgetPasswordResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetWishlistResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PriceSummaryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateProfileResponse;
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
import com.paxees.tcc.network.networkmodels.response.baseResponses.WishlistShareKeyByUserResponse;
import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @POST("cocart/v1/item")
    Call<UpdateCartResponse> updateCart(@Header("Authorization") String header, @Body UpdateCartRequest request);

    @DELETE("cocart/v1/item")
    Call<String> removeCart(@Header("Authorization") String header,@Query("cart_item_key") String search);

    @GET("wc/v3/wishlist/remove_product/{key}")
    Call<String> removeWishlistProd(@Path("key") String key);

    @GET("apiuser/v1/forgotpwd/{useremail}")
    Call<ForgetPasswordResponse> getForgetPassword(@Query("useremail") String useremail);

    @GET("wc/v3/customers/{userid}")
    Call<MyAddressesListResponse> getAddressList(@Path("userid") int userid);

    @PUT("wc/v3/customers/{userid}")
    Call<MyAddressesListResponse> updateShippingAddress(@Path("userid") int userid, @Body UpdateAddressRequest request);

    @PUT("wc/v3/customers/{userid}")
    Call<MyAddressesListResponse> updateBillingAddress(@Path("userid") int userid, @Body UpdateAddress2Request request);

    @GET("wc/v3/wishlist/get_by_user/{userid}")
    Call<WishlistShareKeyByUserResponse> getWishlistShareKeyByUser(@Path("userid") int userid);

    @POST("wc/v3/wishlist/{sharekey}/add_product")
    Call<AddToWishlistResponse> AddToWishlist(@Path("sharekey") String sharekey, @Body AddToWishlistRequest request);

    @GET("wc/v3/wishlist/{sharekey}/get_products")
    Call<GetWishlistResponse> getWishlist(@Path("sharekey") String sharekey);

    @GET("popular/products")
    Call<PopularByThisWeekResponse> getPopularByThisWeek();

    @PUT("wc/v3/customers/{userid}")
    Call<UpdateProfileResponse> profileUpdate(@Path("userid") String userid, @Body UpdateProfileRequest request);

    @GET("cocart/v1/get-cart?thumb=true")
    Call<GetAddToCartResponse> getCarts(@Header("Authorization") String header);

    @GET("cocart/v1/totals?html=true")
    Call<PriceSummaryResponse> getPriceSummary(@Header("Authorization") String header);

    @GET("discover/discover-menu")
    Call<DiscoveryResponse> getDiscoverMenu();

    @POST("wc/v3/customers")
    Call<RegistrationResponse> getRegister(@Body RegistrationRequest request);

    @POST("dashboard.php")
    Call<BrandByCategoryResponse> getDashboard(@Body DashboardRequest request);

    @POST("cocart/v1/add-item")
    Call<AddtoCartResponse> addToCart(@Header("Authorization") String header,@Body AddToCartRequest request);

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
