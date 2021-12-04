package com.paxees.tcc.network.store

import android.app.Application
import com.paxees.tcc.network.ResponseHandlers.callbacks.*
import com.paxees.tcc.network.ResponseHandlers.handler.*
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.*
import com.paxees.tcc.network.networkmodels.response.models.DiagnoseResponse
import com.paxees.tcc.network.retrofitBuilder.RetrofitBuilder.getRetrofitInstance
import com.paxees.tcc.network.timeoutInterface.IOnConnectionTimeoutListener
import com.paxees.tcc.utils.GlobalClass
import okhttp3.RequestBody
import retrofit2.http.Header
import retrofit2.http.Query

open class TCCStore : Application(), IOnConnectionTimeoutListener {
    //:TODO post getLogin
    fun getLogin(url: RetrofitEnums?, loginRequest: LoginRequest?, loginCallBack: LoginCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getLogin(loginRequest).enqueue(LoginBaseHR(loginCallBack))
    }

    //:TODO post getCustomerDetails
    fun getCustomerDetails(
        url: RetrofitEnums?,
        email: String?,
        loginCallBack: CustomerDetailsCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getCustomerDetails(email)
            .enqueue(CustomerDetailsBaseHR(loginCallBack))
    }

    //:TODO post getProductSearch
    fun getProductSearch(url: RetrofitEnums?, email: String?, callBack: ProductSearchCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getProductSearch(email).enqueue(ProductSearchBaseHR(callBack))
    }

    //:TODO post getDiscoverProducts
    fun getDiscoverProducts(
        url: RetrofitEnums?,
        search: String?,
        catid: String?,
        callBack: ProductSearchCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getDiscoverProducts(search, catid)
            .enqueue(ProductSearchBaseHR(callBack))
    }

 //:TODO post changePassword
    fun changePassword(
        url: RetrofitEnums?,
        userId: String?,
        request:ChangePasswordRequest,
        callBack: ChangePasswordCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.changePassword(userId,request)
            .enqueue(ChangePasswordBaseHR(callBack))
    }

  //:TODO post getVideosList
    fun getVideosList(
        url: RetrofitEnums?,
        callBack: VideosListCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getVideosList()
            .enqueue(VideosListBaseHR(callBack))
    }

    //:TODO post StrainRequestTokenBaseHR
    fun getTokenForStrainRequest(
        url: RetrofitEnums?,
        callBack: StrainRequestTokenCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getTokenForStrainRequest()
            .enqueue(StrainRequestTokenBaseHR(callBack))
    }

    //:TODO post getCategories
    fun getCategories(
        url: RetrofitEnums?,
        callBack: CategoryCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getCategories()
            .enqueue(CategoryBaseHR(callBack))
    }


    //:TODO post createStrainRequestForm
    fun createStrainRequestForm(
        url: RetrofitEnums?,
        header: String,
        request: StrainRequestFromRequest,
        callBack: StrainRequestFormCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.createStrainRequestForm(
            header,
            request
        )
            .enqueue(StrainRequestFormBaseHR(callBack))
    }

    //:TODO post updateCart
    fun updateCart(
        url: RetrofitEnums?,
        header: String,
        request: UpdateCartRequest,
        callBack: UpdateCartCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.updateCart(header, request)
            .enqueue(UpdateCartBaseHR(callBack))
    }

    //:TODO post removeCart
    fun removeCart(
        url: RetrofitEnums?,
        header: String,
        key: String?,
        callBack: RemoveProdCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.removeCart(header, key).enqueue(RemoveProdBaseHR(callBack))
    }

    //:TODO post removeWishlistProd
    fun removeWishlistProd(url: RetrofitEnums?, key: String?, callBack: RemoveProdCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.removeWishlistProd(key).enqueue(RemoveProdBaseHR(callBack))
    }

  //:TODO post removeWishlistProd
    fun getProduct(url: RetrofitEnums?, key: String?, callBack: ProductCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getProduct(key).enqueue(ProductBaseHR(callBack))
    }

    //:TODO post getForgetPassword
    fun getForgetPassword(url: RetrofitEnums?, key: String?, callBack: ForgetPasswordCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getForgetPassword(key).enqueue(ForgetPasswordBaseHR(callBack))
    }

    //:TODO post getSingleLocationDetails
    fun getSingleLocationDetails(
        url: RetrofitEnums?, /*key: String?,*/
        callBack: SingleLocationDetailsCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getSingleLocationDetails(/*key*/)
            .enqueue(SingleLocationDetailsBaseHR(callBack))
    }

    //:TODO post getAddressList
    fun getAddressList(url: RetrofitEnums?, email: Int, loginCallBack: AddressListCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getAddressList(email).enqueue(AddressListBaseHR(loginCallBack))
    }

    //:TODO post getImage
    fun getImage(url: RetrofitEnums?, code: String, callBack:  ImageCallBack) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getImage(code).enqueue(ImageBaseHR(callBack))
    }

    //:TODO post getImageForWishList
    fun getImageForWishList(url: RetrofitEnums?, code: String, callBack:  WishlistImageCallBack) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getImageForWishList(code).enqueue(WishlistImageBaseHR(callBack))
    }

    //:TODO post getPaymentMethodsListOfConsumer
    fun getPaymentMethodsListOfConsumer(url: RetrofitEnums?, code: String, callBack:  GetPaymentMethodConusmerCallBack) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getPaymentMethodsListOfConsumer(code).enqueue(GetPaymentMethodConsumerBaseHR(callBack))
    }

    //:TODO post getPaymentMethods
    fun getPaymentMethods(url: RetrofitEnums?,  callBack:  PaymentMethodListCallBack) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getPaymentMethods().enqueue(PaymentMethodListBaseHR(callBack))
    }

    //:TODO post updateShippingAddress
    fun updateShippingAddress(
        url: RetrofitEnums?,
        email: Int,
        request: UpdateAddressRequest,
        loginCallBack: AddressListCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.updateShippingAddress(email, request)
            .enqueue(AddressListBaseHR(loginCallBack))
    }

    //:TODO post addCustomerToStrip
    fun addCustomerToStrip(
        url: RetrofitEnums?,
        request: AddConsumerInStripRequest,
        callBack: AddConsumerStripCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.addCustomerToStrip( request)
            .enqueue(AddConsumerStripBaseHR(callBack))
    }
    //:TODO post addNewCreditCard
    fun addNewCreditCard(
        url: RetrofitEnums?,
        header:RequestBody/*,
        type:String,
        number:String,
        mon:String,
        year:String,
        cvv:String*/,
        callBack: AddNewCreditCardCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.addNewCreditCard( header/*,type,number,mon,year,cvv*/)
            .enqueue(AddNewCreditCardBaseHR(callBack))
    }

    //:TODO post updateBillingAddress
    fun updateBillingAddress(
        url: RetrofitEnums?,
        email: Int,
        request: UpdateAddress2Request,
        loginCallBack: AddressListCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.updateBillingAddress(email, request)
            .enqueue(AddressListBaseHR(loginCallBack))
    }

    //:TODO post getWishlistShareKeyByUser
    fun getWishlistShareKeyByUser(
        url: RetrofitEnums?,
        userId: Int,
        loginCallBack: WishlistShareKeyByUserCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getWishlistShareKeyByUser(userId)
            .enqueue(WishlistShareKeyByUserBaseHR(loginCallBack))
    }

    //:TODO post AddToWishlist
    fun AddToWishlist(
        url: RetrofitEnums?,
        sharekey: String?,
        request: AddToWishlistRequest?,
        loginCallBack: AddToWishlistCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.AddToWishlist(sharekey, request)
            .enqueue(AddToWishlistBaseHR(loginCallBack))
    }

    //:TODO post getWishlist
    fun getWishlist(url: RetrofitEnums?, sharekey: String?, loginCallBack: GetWishlistCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getWishlist(sharekey).enqueue(GetWishlistBaseHR(loginCallBack))
    }

    //:TODO post profileUpdate
    fun profileUpdate(
        url: RetrofitEnums?,
        userID: String?,
        request: UpdateProfileRequest?,
        callBack: UpdateProfileCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.profileUpdate(userID, request)
            .enqueue(UpdateProfileBaseHR(callBack))
    }

 //:TODO post getSelectedDiagnose
    fun getSelectedDiagnose(
        url: RetrofitEnums?,
        userID: String?,
        callBack: DiagnoseCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getSelectedDiagnose(userID)
            .enqueue(DiagnoseBaseHR(callBack))
    }

    //:TODO post getRegister
    fun getRegister(
        url: RetrofitEnums?,
        request: RegistrationRequest?,
        callBack: RegistrationCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getRegister(request).enqueue(RegisterBaseHR(callBack))
    }

    //:TODO post diagnoseCreate
    fun diagnoseCreate(
        url: RetrofitEnums?,
        header: String,
        request: DiagnoseRequest?,
        callBack: DiagnoseCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.diagnoseCreate(header, request).enqueue(DiagnoseBaseHR(callBack))
    }
    //:TODO post editDiagnose
    fun editDiagnose(
        url: RetrofitEnums?,
        id:String,
        header: String,
        request: DiagnoseRequest?,
        callBack: DiagnoseCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.editDiagnose(id,header, request).enqueue(DiagnoseBaseHR(callBack))
    }

//:TODO post createOrder
    fun createOrder(
        url: RetrofitEnums?,
        request: CreateOrderRequest?,
        callBack: CreateOrderCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.createOrder(request).enqueue(CreateOrderBaseHR(callBack))
    }

    //:TODO post getDashboard
    fun getDashboard(
        url: RetrofitEnums?,
        request: DashboardRequest?,
        callBack: BrandResponseCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getDashboard(request).enqueue(BrandResponseBaseHR(callBack))
    }

    //:TODO post addToCart
    fun addToCart(
        url: RetrofitEnums?,
        header: String,
        request: AddToCartRequest?,
        callBack: AddToCartCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.addToCart(header, request).enqueue(AddToCartBaseHR(callBack))
    }

    //:TODO post getPopularBrands
    fun getPopularBrands(
        url: RetrofitEnums?,
        request: DashboardRequest?,
        callBack: BrandResponseCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getPopularBrands(request).enqueue(BrandResponseBaseHR(callBack))
    }

    //:TODO post getBrandByCategory
    fun getBrandsDetails(
        url: RetrofitEnums?,
        request: BrandDetailsRequest?,
        callBack: BrandByCategoryCallBack?
    ) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getBrandsDetails(request).enqueue(BrandByCategoryBaseHR(callBack))
    }

    //:TODO getPopularByThisWeek
    fun getPopularByThisWeek(url: RetrofitEnums?, callBack: PopularByThisWeekCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.popularByThisWeek.enqueue(PopularByThisWeekBaseHR(callBack))
    }

    //:TODO getCarts
    fun getCarts(url: RetrofitEnums?, headers: String, callBack: GetCartsCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getCarts(headers).enqueue(GetCartsBaseHR(callBack))
    }

    //:TODO getPriceSummary
    fun getPriceSummary(url: RetrofitEnums?, headers: String, callBack: PriceSummaryCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.getPriceSummary(headers).enqueue(PriceSummaryBaseHR(callBack))
    }

    //:TODO getDiscoverMenu
    fun getDiscoverMenu(url: RetrofitEnums?, callBack: DiscoveryMenuCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.discoverMenu.enqueue(DiscoverMenuBaseHR(callBack))
    }

    //:TODO getPlantsByType
    fun getPlantsByType(url: RetrofitEnums?, callBack: PlantsByTypeCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.plantsByType.enqueue(PlantsByTypeBaseHR(callBack))
    }

    //:TODO getNightTimeUsage
    fun getNightTimeUsage(url: RetrofitEnums?, callBack: NightTimeUsageCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.nightTimeUsage.enqueue(NightTimeResponseBaseHR(callBack))
    }

    //:TODO getStrains
    fun getStrains(url: RetrofitEnums?, callBack: StrainCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.strains.enqueue(StrainBaseHR(callBack))
    }

    //:TODO getDignoseList
    fun getDiagnoseList(url: RetrofitEnums?, callBack: DiagnoseListCallBack?) {
        val privateInstanceRetrofit = getRetrofitInstance(GlobalClass.applicationContext!!, url!!)
        privateInstanceRetrofit.diagnoseList.enqueue(DiagnoseListBaseHR(callBack))
    }

    override fun onConnectionTimeout() {}

    companion object {
        private val consumerStore: TCCStore? = null

        //    APIInterface globalBaseUrl = RetrofitBuilder.INSTANCE.getRetrofitInstance(GlobalClass.applicationContext, RetrofitEnums.URL_HBL);
        val instance: TCCStore?
            get() = consumerStore ?: TCCStore()
    }
}