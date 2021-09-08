package com.paxees.tcc.views.fragments.settingsFragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.networkmodels.response.baseResponses.DataXXX
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetExistingConsumerList
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.TransparentProgressDialog
import com.paxees.tcc.views.adapters.GetExistingConsumerAdapter
import kotlinx.android.synthetic.main.fragment_get_consumer_list.*
import kotlinx.android.synthetic.main.toolbar_theme.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


class GetConsumerList : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    var header: TextView? =null
    var alertDialog: AlertDialog? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_consumer_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        var obj =getExistingConsumers(activity)
        obj.execute()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
               findNavController().popBackStack()
            }
        }
    }


    private class getExistingConsumers(
        activity: FragmentActivity?
    ) :
        AsyncTask<Void?, Response?, Response?>() {
        var desc=""
        var email=""
        var context: Context?=null
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
            val body = RequestBody.create(
                mediaType,
                string
            )
            val request: Request = Request.Builder()
                .url("https://api.stripe.com/v1/customers")
                .method("GET", null)
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
                    GetExistingConsumerList::class.java
                )
                Log.i("ResponseStrip",gson.toString())
                var list=ArrayList<DataXXX>()
                val email = (context as CIFRootActivity?)!!.sharedPreferenceManager.loginData.userEmail
                for(x in gson.data){
                    if(email.equals(x.email)){
                        list.add(x)
                    }
                }
                (this.context as CIFRootActivity).sharedPreferenceManager.setExistingConsumerInStripe(gson)

                val horizontalLayoutManagaer = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                (this.context as CIFRootActivity).rvConsumers.layoutManager = horizontalLayoutManagaer
                var VideosAdapter = GetExistingConsumerAdapter(this.context, list
                ) { view, position ,data->
                    (this.context as CIFRootActivity).sharedPreferenceManager.singleConsumer = data
                    val bundle = bundleOf("CONSUMER_CODE" to  data.id.toString())
                    (this.context as CIFRootActivity).findNavController(R.id.cifHostFragment).navigate(R.id.going_to_mypayment_method,bundle)
                }
                (this.context as CIFRootActivity).rvConsumers.setAdapter(VideosAdapter)
                VideosAdapter.notifyDataSetChanged()
            }else{
                ToastUtils.showToastWith(this.context,"Something went wrong, please try again","")
            }
            hideLoader()
        }
    }



}