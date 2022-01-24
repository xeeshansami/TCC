package com.paxees.tcc.network.apiInterface;

import com.paxees.tcc.network.networkmodels.request.AddConsumerInStripRequest;
import com.paxees.tcc.network.networkmodels.request.AddToCartRequest;
import com.paxees.tcc.network.networkmodels.request.AddToWishlistRequest;
import com.paxees.tcc.network.networkmodels.request.BrandDetailsRequest;
import com.paxees.tcc.network.networkmodels.request.ChangePasswordRequest;
import com.paxees.tcc.network.networkmodels.request.CreateOrderRequest;
import com.paxees.tcc.network.networkmodels.request.DashboardRequest;
import com.paxees.tcc.network.networkmodels.request.DiagnoseRequest;
import com.paxees.tcc.network.networkmodels.request.LoginRequest;
import com.paxees.tcc.network.networkmodels.request.RegistrationRequest;
import com.paxees.tcc.network.networkmodels.request.StrainRequestFromRequest;
import com.paxees.tcc.network.networkmodels.request.UpdateAddress2Request;
import com.paxees.tcc.network.networkmodels.request.UpdateAddressRequest;
import com.paxees.tcc.network.networkmodels.request.UpdateCartRequest;
import com.paxees.tcc.network.networkmodels.request.UpdateProfileRequest;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddConsumerStripResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewCreditCardResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddToWishlistResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddtoCartResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandByCategoryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.BrandDetailResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ChangePasswordResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.CreateOrderResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiagnoseListResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiscoveryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ForgetPasswordResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetPaymentMethodListOfConsumerResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetWishlistResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ImageResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PaymentMethodListResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.PriceSummaryResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.SingleLocationDetailsResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainRequestFormResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainRequestTokenResponse;
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
import com.paxees.tcc.network.networkmodels.response.baseResponses.VideosListResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.WishlistImageResponse;
import com.paxees.tcc.network.networkmodels.response.baseResponses.WishlistShareKeyByUserResponse;
import com.paxees.tcc.network.networkmodels.response.models.CategoriesResponse;
import com.paxees.tcc.network.networkmodels.response.models.DiagnoseResponse;
import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse;

import org.jetbrains.annotations.NotNull;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
    Call<ProductSearchResponse> getDiscoverProducts(@Query("search") String search, @Query("category") String category);

    @PUT("wc/v3/customers/{userid}")
    Call<ChangePasswordResponse> changePassword(@Path("userid") String userid, @Body ChangePasswordRequest request);

    @GET("wp/v2/video")
    Call<VideosListResponse> getVideosList();

    @GET("refresh/refresh-token")
    Call<StrainRequestTokenResponse> getTokenForStrainRequest();

    @GET("wp/v2/categories")
    Call<CategoriesResponse> getCategories();

    @POST("crm/v2/Leads")
    Call<StrainRequestFormResponse> createStrainRequestForm(@Header("Authorization") String header, @Body StrainRequestFromRequest request);

    @POST("cocart/v1/item")
    Call<UpdateCartResponse> updateCart(@Header("Authorization") String header, @Body UpdateCartRequest request);

    @DELETE("cocart/v1/item")
    Call<String> removeCart(@Header("Authorization") String header, @Query("cart_item_key") String search);

    @GET("wc/v3/wishlist/remove_product/{key}")
    Call<String> removeWishlistProd(@Path("key") String key);

    @GET("wc/v3/products/{key}")
    Call<ProductResponse> getProduct(@Path("key") String key);

    @GET("apiuser/v1/forgotpwd/{useremail}")
    Call<ForgetPasswordResponse> getForgetPassword(@Path("useremail") String useremail);

    @GET("single/location/203")
    Call<SingleLocationDetailsResponse> getSingleLocationDetails(/*@Path("userid") String userid*/);

    @POST("wp/v2/diagnostic")
    Call<DiagnoseResponse> diagnoseCreate(@Header("Authorization") String header, @Body DiagnoseRequest request);

    @POST("wp/v2/diagnostic/{userid}")
    Call<DiagnoseResponse> editDiagnose(@Path("userid") String userid,@Header("Authorization") String header, @Body DiagnoseRequest request);

    @POST("wc/v3/orders")
    Call<CreateOrderResponse> createOrder(@Body CreateOrderRequest request);

    @GET("wc/v3/customers/{userid}")
    Call<MyAddressesListResponse> getAddressList(@Path("userid") int userid);

    @GET("image-url/{code}")
    Call<ImageResponse> getImage(@Path("code") String code);

    @GET("wishlist/product-image/{code}")
    Call<WishlistImageResponse> getImageForWishList(@Path("code") String code);

    @Headers({"Authorization: Bearer sk_test_lWIOYLjp3fBuFPJiUTLOhSZh00DhWRHj6p"})
    @GET("v1/customers/{code}}/sources")
    Call<GetPaymentMethodListOfConsumerResponse> getPaymentMethodsListOfConsumer(@Path("code") String code);

    @FormUrlEncoded
    @POST("v1/customers")
    Call<AddConsumerStripResponse> addCustomerToStrip(@Body() AddConsumerInStripRequest request);

    @Headers({"Authorization: Bearer sk_test_lWIOYLjp3fBuFPJiUTLOhSZh00DhWRHj6p"})
    @POST("v1/tokens")
    Call<AddNewCreditCardResponse> addNewCreditCard(@Body() RequestBody body
                                                    /*@Header("Content-Type") String type,
                                                    @Field("card[number]") String number,
                                                    @Field("card[exp_month]") String exp_month,
                                                    @Field("card[exp_year]") String exp_year,
                                                    @Field("card[number]") String cvc*/);

    @GET("wc/v3/payment_gateways")
    Call<PaymentMethodListResponse> getPaymentMethods();

    @PUT("wc/v3/customers/{userid}")
    Call<MyAddressesListResponse> updateShippingAddress(@Path("userid") int userid, @Body UpdateAddressRequest request);

    @PUT("wc/v3/customers/{userid}")
    Call<MyAddressesListResponse> updateBillingAddress(@Path("userid") int userid, @Body UpdateAddress2Request request);

    @GET("wc/v3/wishlist/get_by_user/{userid}")
    Call<WishlistShareKeyByUserResponse> getWishlistShareKeyByUser(@Path("userid") int userid);

    @GET("wp/v2/media")
    Call<WishlistShareKeyByUserResponse> uploadImageFile(@Part MultipartBody.Part filePart);

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
    Call<AddtoCartResponse> addToCart(@Header("Authorization") String header, @Body AddToCartRequest request);

    @POST("popularbrands.php")
    Call<BrandByCategoryResponse> getPopularBrands(@Body DashboardRequest request);

    @GET("strain/strain-list")
    Call<StrainResponse> getStrains();

    @GET("wp/v2/diagnostic/")
    Call<DiagnoseListResponse> getDiagnoseList();

    @GET("wp/v2/diagnostic/{userid}")
    Call<DiagnoseResponse> getSelectedDiagnose(@Path("userid") String userid);



    @POST("branddetails.php")
    Call<BrandDetailResponse> getBrandsDetails(@Body BrandDetailsRequest request);

    @GET("product/product-list-count")
    Call<PlantsByTypeResponse> getPlantsByType();

    @GET("products/night-time-usage")
    Call<NightTimeUsuageResponse> getNightTimeUsage();

}
