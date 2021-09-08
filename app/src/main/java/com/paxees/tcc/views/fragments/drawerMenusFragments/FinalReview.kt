package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.*
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.CreateOrderRequest
import com.paxees.tcc.network.networkmodels.request.LineItem
import com.paxees.tcc.network.networkmodels.request.MetaData
import com.paxees.tcc.network.networkmodels.request.ShippingLine
import com.paxees.tcc.network.networkmodels.response.baseResponses.*
import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.CartAdapter
import kotlinx.android.synthetic.main.fragment_final_review.*
import kotlinx.android.synthetic.main.fragment_final_review.rvCarts
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header


class FinalReview : Fragment(), View.OnClickListener, CartAdapter.onItemPlus,
    CartAdapter.onItemMinus, CartAdapter.onItemRemove {
    var sessionManager: SessionManager? = null
    var address: MyAddressesListResponse? = null
    var orders: GetAddToCartResponse? = null
    var ordersResponse: CreateOrderResponse? = null
    var total = 0.0
    var subTotal = 0.0
    var disc = 10.0
    var tx = 5.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_final_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        onBackPressed()
        ordersResponse= arguments?.getParcelable("ORDER_RESPONSE")
        getCarts()
        rvAddressFunc()
    }

    private fun rvAddressFunc() {
        paymentRate.text=ordersResponse!!.shippingLines[0].methodTitle.toUpperCase()
        nextButtonCreateOrder.text="Pay $"+ordersResponse!!.total+" Now"
        nextButtonCreateOrder.setOnClickListener(this)
        billingAddress.text =ordersResponse!!.billing.address1+" "+ordersResponse!!.billing.city+" "+ordersResponse!!.billing.country+"\n"+ordersResponse!!.billing.address2
    }

    fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    switchFragment(R.id.navigation_home)
                }
            })
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.final_review)
        getCarts()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                findNavController().popBackStack()
            }
            R.id.nextButtonCreateOrder -> {
//                if (validation()) {
//                    createOrder()
//                }
            }

        }
    }

//    private fun validation(): Boolean {
//        if (!shippingAddressCB.isChecked && !billingAddressCB.isChecked) {
//            ToastUtils.showToastWith(activity, "Please check the Use this as deliver address","")
//            return false
//        } else if (!fixedRateCB.isChecked && !tableRateCB.isChecked) {
//            ToastUtils.showToastWith(activity, "Please check the shipping method","")
//            return false
//        } else {
//            return true
//        }
//    }

    private fun switchFragment(startDestId: Int) {
        val navController = findNavController()
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }

    private fun rvCart(response: GetAddToCartResponse) {
        // set up the RecyclerView
        carteDetails.text=response.size.toString()+" items"
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvCarts.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = CartAdapter(activity, response, this, this,this,2)
        rvCarts.adapter = VideosAdapter
        VideosAdapter.notifyDataSetChanged()
    }

    private fun getCarts() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getCarts(
            RetrofitEnums.URL_HBL,
            (activity as CIFRootActivity).token,
            object :
                GetCartsCallBack {
                override fun Success(response: GetAddToCartResponse) {
                    rvCart(response)
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    private fun createOrder() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        var request = CreateOrderRequest()
        var shippingLine= ShippingLine()
        var lineItem= LineItem()
        var metaData= MetaData()
        request.paymentMethod="stripe"
        request.paymentMethodTitle="Credit Card (Stripe)"
        request.setPaid=false
        metaData.key="strip_id"
        metaData.value=(activity as CIFRootActivity).sharedPreferenceManager.singleConsumer.id
//        if (fixedRateCB.isChecked) {
//            shippingLine.methodId = "flat_rate"
//            shippingLine.methodTitle = "Flat Rate"
//            shippingLine.total = "10.00"
//        } else if (tableRateCB.isChecked) {
//            shippingLine.methodId = "table_rate"
//            shippingLine.methodTitle = "Table Rate"
//            request.shippingLines[0].total = "50.00"
//        }
        for(x in 0 until  orders!!.size){
            lineItem.productId=orders!![x].productId
            lineItem.quantity=orders!![x].quantity
        }
        request.shippingLines.add(shippingLine)
        request.lineItems.add(lineItem)
        request.metaData.add(metaData)
//        var json= Gson().toJson(request)
//        var jsonObj= JSONObject(json)
//        if (shippingAddressCB.isChecked) {
            request.billing=address!!.billing
//            jsonObj.remove("shipping")
//        } else if (billingAddressCB.isChecked) {
            request.shipping=address!!.shipping
//            jsonObj.remove("billing")
//        }
//        var gson = Gson().fromJson(
//            jsonObj.toString(),
//            CreateOrderRequest::class.java
//        )
        TCCStore.instance!!.createOrder(
            RetrofitEnums.URL_HBL,
            request,
            object :
                CreateOrderCallBack {
                override fun Success(response: CreateOrderResponse) {
                    ToastUtils.showToastWith(activity,"Order created successfully")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    override fun onItemPlus(
        data: GetAddToCartResponse?,
        view: View?,
        position: Int,
        value: String?
    ) {

    }

    override fun onItemMinus(
        data: GetAddToCartResponse?,
        view: View?,
        position: Int,
        value: String?
    ) {

    }

    override fun onItemRemove(
        data: GetAddToCartResponse?,
        view: View?,
        position: Int,
        value: String?
    ) {

    }

}