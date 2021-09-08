package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.*
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.*
import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.TransparentProgressDialog
import com.paxees.tcc.views.adapters.CartAdapter
import kotlinx.android.synthetic.main.fragment_final_review.*
import kotlinx.android.synthetic.main.fragment_final_review.rvCarts
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


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
        ordersResponse = arguments?.getParcelable("ORDER_RESPONSE")
        getCarts()
        rvAddressFunc()
    }

    private fun rvAddressFunc() {
        paymentRate.text = ordersResponse!!.shippingLines[0].methodTitle.toUpperCase()
        nextButtonCreateOrder.text = "Pay $" + ordersResponse!!.total + " Now"
        nextButtonCreateOrder.setOnClickListener(this)
        billingAddress.text =
            ordersResponse!!.billing.address1 + " " + ordersResponse!!.billing.city + " " + ordersResponse!!.billing.country + "\n" + ordersResponse!!.billing.address2
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
                chargePayment()
            }
        }
    }

    private fun switchFragment(startDestId: Int) {
        val navController = findNavController()
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }

    private fun rvCart(response: GetAddToCartResponse) {
        // set up the RecyclerView
        carteDetails.text = response.size.toString() + " items"
        val horizontalLayoutManagaer =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvCarts.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = CartAdapter(activity, response, this, this, this, 2)
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

    private fun chargePayment() {
        var currency = "USD"
        var description = "test test"
        var source = (activity as CIFRootActivity).sharedPreferenceManager.cardSaved.id
        var orderID = ordersResponse!!.id
        var amount = ordersResponse!!.total.toDouble().toInt()
        if(amount>40) {
            var cusomter = (activity as CIFRootActivity).sharedPreferenceManager.cardSaved.customer
            var obj = chargeAmount(
                currency,
                description,
                source,
                orderID.toString(),
                amount.toString(),
                cusomter,
                activity as CIFRootActivity
            )
            obj.execute()
        }else{
            ToastUtils.showToastWith(activity,"Amount too small, please add more items","")
        }
    }

    private class chargeAmount(
        currency: String,
        description: String,
        source: String,
        orderID: String,
        amount: String,
        customer: String,
        activity: FragmentActivity?
    ) :
        AsyncTask<Void?, Response?, Response?>() {
        var currency = ""
        var description = ""
        var source = ""
        var orderID = ""
        var amount = ""
        var customer = ""
        var context: Context? = null
        private var progressDialog: TransparentProgressDialog? = null

        init {
            this.context = activity
            this.currency = currency
            this.description = description
            this.source = source
            this.orderID = orderID
            this.amount = amount
            this.customer = customer

        }

        fun getProgressDialogInstance(context: Context?): TransparentProgressDialog? {
            if (progressDialog == null) progressDialog = TransparentProgressDialog(
                context!!
            )
            return progressDialog
        }

        fun showDialog(context: Context?) {
            progressDialog = getProgressDialogInstance(context)
            progressDialog!!.setCancelable(false)
            progressDialog!!.show()
        }

        fun hideLoader() {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.cancel()
                progressDialog = null
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
            showDialog(this.context as CIFRootActivity)
            //this method will be running on UI thread
        }

        protected override fun doInBackground(vararg params: Void?): Response? {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            val client = OkHttpClient().newBuilder()
                .build()
            val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
            var string = "currency=${this.currency}&description=${this.description}&source=${this.source}&metadata[order_id]=${this.orderID}&amount=${this.amount}&customer=${this.customer}"
            val body = RequestBody.create(
                mediaType,
                string
            )
            Log.i(
                "DetailsOFCard",
                string
            )
            val request: Request = Request.Builder()
                .url("https://api.stripe.com/v1/charges")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer sk_test_lWIOYLjp3fBuFPJiUTLOhSZh00DhWRHj6p")
                .build()
            return client.newCall(request).execute();
        }

        override fun onPostExecute(result: Response?) {
            super.onPostExecute(result)
            if (result!!.isSuccessful) {
                var responses: Response? = null
                try {
                    responses = result
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                (this.context as CIFRootActivity).findNavController(R.id.cifHostFragment).navigate(R.id.going_to_congratulations)
                ToastUtils.showToastWith(this.context, "Payment Successfully completed", "")
            } else {
                ToastUtils.showToastWith(this.context, "Something went wrong, try again", "")
            }
            hideLoader()
        }
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