package com.paxees.tcc.views.fragments.settingsFragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewConsumerResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewCreditCardResponse
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.TransparentProgressDialog
import kotlinx.android.synthetic.main.change_add_consumer.view.*
import kotlinx.android.synthetic.main.change_address_popup.view.cancelBtn
import kotlinx.android.synthetic.main.fragment_add_consumer_method.*
import kotlinx.android.synthetic.main.toolbar_theme.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


class AddConsumerMethod : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    var header: TextView? = null
    var alertDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_consumer_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        addConsumerBtn.setOnClickListener(this)
        existingConsumerBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                findNavController().popBackStack()
            }
            R.id.addConsumerBtn -> {
                addConsumer()
            }
            R.id.existingConsumerBtn -> {
                findNavController().navigate(R.id.going_get_consumer_list)
            }
        }
    }

    //add consumer in strip
    private fun addConsumer() {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity as CIFRootActivity)
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.change_add_consumer, null)
        dialogBuilder.setCancelable(false)
        dialogBuilder.setView(dialogView)
        alertDialog = dialogBuilder.create()
        dialogView.addConsumerBtn.setOnClickListener(View.OnClickListener {
            var email = dialogView.emailID.text.toString().trim()
            var desc = dialogView.descID.text.toString().trim()
            if (validation(email, desc, dialogView)) {
                var obj = addConusmerInStrip(desc, email, activity, alertDialog!!)
                obj.execute()
            }
        })
        dialogView.cancelBtn.setOnClickListener(View.OnClickListener {
            alertDialog!!.dismiss()
        })
        alertDialog!!.show()
    }

    //add consumer in strip
    private class addConusmerInStrip(
        desc: String,
        email: String,
        activity: FragmentActivity?,
        alertDialog: AlertDialog
    ) :
        AsyncTask<Void?, Response?, Response?>() {
        var alertDialog: AlertDialog? = null
        var desc = ""
        var email = ""
        var context: Context? = null
        private var progressDialog: TransparentProgressDialog? = null

        init {
            this.context = activity
            this.desc = desc
            this.email = email
            this.alertDialog = alertDialog
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
            var string = "description=$desc&email=$email"
            val body = RequestBody.create(
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
            if (result!!.isSuccessful) {
                var responses: Response? = null
                try {
                    responses = result
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val jsonData = responses!!.body!!.string()
                var gson = Gson().fromJson(
                    jsonData,
                    AddNewConsumerResponse::class.java
                )
                Log.i("ResponseStrip", gson.toString())
                (this.context as CIFRootActivity).sharedPreferenceManager.setConsumerInStripe(gson)
                ToastUtils.showToastWith(this.context, "Consumer Successfully added!")
            }
            hideLoader()
            alertDialog!!.dismiss()
        }
    }


    private fun validation(address1: String, address2: String, dialogView: View): Boolean {
        return when {
            TextUtils.isEmpty(address1) -> {
                dialogView.emailID!!.error = "Email Address should not be empty"
                dialogView.emailID!!.requestFocus()
                false
            }
            TextUtils.isEmpty(address2) -> {
                dialogView.descID!!.error = "Description should not be empty"
                dialogView.descID!!.requestFocus()
                false
            }
            else -> {
                true
            }
        }
    }
}