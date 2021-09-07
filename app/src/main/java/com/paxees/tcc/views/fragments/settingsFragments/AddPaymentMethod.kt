package com.paxees.tcc.views.fragments.settingsFragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.manojbhadane.PaymentCardView.OnPaymentCardEventListener
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewCreditCardResponse
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.TransparentProgressDialog
import kotlinx.android.synthetic.main.fragment_add_payment_method.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.create
import okhttp3.Response
import java.io.IOException
import java.math.BigInteger


class AddPaymentMethod : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    var header: TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_payment_method, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        header=requireView().findViewById(R.id.header)
        header!!.setText("Credit Method")
        sessionManager = SessionManager(activity)
        //Callbacks
        creditCard.setCardTitle("Add New\nCredit Card")
        //Callbacks
        creditCard.setOnPaymentCardEventListener(object : OnPaymentCardEventListener {
            override fun onCardDetailsSubmit(
                month: String,
                year: String,
                cardNumber: String,
                cvv: String
            ) {
                Log.i("CardDetails","$month, $year, $cardNumber, $cvv")
                var ex=addNewPaymentMethod(BigInteger(cardNumber.trim().replace(" ","")),month.toInt(),year.toInt(),cvv.toInt(),activity)
                ex.execute()
            }

            override fun onError(error: String) {
            }

            override fun onCancelClick() {}
        })
    }



    private class addNewPaymentMethod(
        number: BigInteger,
        month: Int,
        year: Int,
        cvv: Int,
        activity: FragmentActivity?
    ) :
        AsyncTask<Void?, Response?, Response?>() {
        var number:BigInteger
        var month=0
        var year=0
        var cvv=0
        var context:Context?=null
        private var progressDialog: TransparentProgressDialog? = null
        init {
            this.context=activity
            this.number=number
            this.month=month
            this.year=year
            this.cvv=cvv
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
            var param=HashMap<String,String>()
            param.put("card[number]",number.toString())
            param.put("card[exp_month]",month.toString())
            param.put("card[exp_year]",year.toString())
            param.put("card[cvc]",cvv.toString())
            var string="card[number]=${number.toString()}&card[exp_month]=${month.toString()}&card[exp_year]=${year.toString()}&card[cvc]=${cvv.toString()}"
            val body = create(
                mediaType,
                string
            )
            Log.i("DetailsOFCard","card[number]=$number&card[exp_month]=$month&card[exp_year]=$year&card[cvc]=$cvv")
            val request: Request = Request.Builder()
                .url("https://api.stripe.com/v1/tokens")
                .method("POST", body)
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
                    AddNewCreditCardResponse::class.java
                )
                Log.i("ResponseStrip",gson.toString())
                var consumerStrip=addConusmerInStrip("Test",(this.context as CIFRootActivity).sharedPreferenceManager.loginData.userEmail,this.context as CIFRootActivity)
                consumerStrip.execute()
            }else{
                ToastUtils.showToastWith(this.context,"Something went wrong, try again","")
            }

            hideLoader()
        }
    }

    private class addConusmerInStrip(
        desc: String,
        email: String,
        activity: FragmentActivity?
    ) :
        AsyncTask<Void?, Response?, Response?>() {
        var desc=""
        var email=""
        var context:Context?=null
        private var progressDialog: TransparentProgressDialog? = null
        init {
            this.context=activity
            this.desc=desc
            this.email=email
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
            var string="description=$desc&email=$email"
            val body = create(
                mediaType,
                string
            )
            val request: Request = Request.Builder()
                .url("https://api.stripe.com/v1/customers")
                .method("POST", body)
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
                    AddNewCreditCardResponse::class.java
                )
                Log.i("ResponseStrip",gson.toString())
                (this.context as CIFRootActivity).findNavController(R.id.cifHostFragment).popBackStack()
                ToastUtils.showToastWith(this.context,"Card Successfully added!")
            }
            hideLoader()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
               findNavController().popBackStack()
            }
        }
    }


}