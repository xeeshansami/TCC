package com.paxees.tcc.views.fragments.settingsFragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.GetPaymentMethodConusmerCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.PaymentMethodListCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewCreditCardResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetPaymentMethodListOfConsumerResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.PaymentMethodListResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.TransparentProgressDialog
import com.paxees.tcc.views.adapters.PaymentMethodAdapter
import kotlinx.android.synthetic.main.fragment_add_payment_method.*
import kotlinx.android.synthetic.main.fragment_changedpwd.*
import kotlinx.android.synthetic.main.fragment_mypayment_method.*
import kotlinx.android.synthetic.main.fragment_strains.*
import kotlinx.android.synthetic.main.toolbar_theme.backBtn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class MyPaymentMethod : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypayment_method, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        addtoCart.setOnClickListener(this)
        var obj=addConusmerInStrip("cus_J10foBiIbM03zL",activity as CIFRootActivity)
        obj.execute()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
               findNavController().popBackStack()
            }
            R.id.addtoCart -> {
                findNavController().navigate(R.id.going_to_add_new_payment_method)
            }
        }
    }

    private class addConusmerInStrip(
        code: String,
        activity: FragmentActivity?
    ) :
        AsyncTask<Void?, Response?, Response?>() {
        var code=""
        var context: Context?=null
        private var progressDialog: TransparentProgressDialog? = null
        init {
            this.context=activity
            this.code=code
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
            showDialog( this.context as CIFRootActivity)
            //this method will be running on UI thread
        }

        protected override fun doInBackground(vararg params: Void?): Response? {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            val client = OkHttpClient().newBuilder()
                .build()
            val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
//            var string="description=$desc&email=$email"
            val body = RequestBody.create(
                mediaType,
                ""
            )
            val request: Request = Request.Builder()
                .url("https://api.stripe.com/v1/customers/$code/sources")
                .method("GET",null)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "Bearer sk_test_lWIOYLjp3fBuFPJiUTLOhSZh00DhWRHj6p")
                .build()
            return client.newCall(request).execute();
        }

        override fun onPostExecute(result: Response?) {
            super.onPostExecute(result)
            if(result!!.isSuccessful){
                var responses: Response? = null
                try {
                    responses = result
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val jsonData = responses!!.body!!.string()
                var gson = Gson().fromJson(
                    jsonData,
                    GetPaymentMethodListOfConsumerResponse::class.java
                )
                val horizontalLayoutManagaer = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                (this.context as CIFRootActivity).rvPayments.layoutManager = horizontalLayoutManagaer
                var VideosAdapter = PaymentMethodAdapter(this.context, gson)
                (this.context as CIFRootActivity).rvPayments.setAdapter(VideosAdapter)
                VideosAdapter.notifyDataSetChanged()
                Log.i("ResponseStrip",gson.toString())
            }
            hideLoader()
        }
    }


    fun getPaymentMethods() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getPaymentMethodsListOfConsumer(
            RetrofitEnums.URL_STRIP_API,"cus_J10foBiIbM03zL",
            object :
                GetPaymentMethodConusmerCallBack {
                override fun Success(response: GetPaymentMethodListOfConsumerResponse) {

                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    private fun rvPaymentFunc(response: GetPaymentMethodListOfConsumerResponse) {
        // set up the RecyclerView

    }
}