package com.paxees.tcc.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.format.Formatter
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.network.utils.Helper
import com.paxees.tcc.utils.managers.SharedPreferenceManager
import okhttp3.logging.HttpLoggingInterceptor

class GlobalClass : TCCStore() {
    @JvmField
    var sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager()
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

    override fun onCreate() {
        super.onCreate()
        Companion.applicationContext = applicationContext
        sharedPreferenceManager.getInstance(this@GlobalClass)
        BASE_URL_HBL = Helper.getConfigValue(Companion.applicationContext, "BASE_URL")
        BASE_URL_ZOHO = Helper.getConfigValue(Companion.applicationContext, "BASE_URL_ZOHO")
        BASE_URL_STRAIN_REQUEST_FROM = Helper.getConfigValue(Companion.applicationContext, "BASE_URL_STRAIN_REQUEST_FROM")
        BASE_URL_STRIP_API = Helper.getConfigValue(Companion.applicationContext, "BASE_URL_STRIP_API")
    }

    fun setEnabled(editText: EditText, isCheck: Boolean, value: Float) {
        editText.isEnabled = isCheck
        editText.alpha = value
    }

    companion object {
        var wManager: WifiManager? = null
        var telephonyManager: TelephonyManager? = null
        @JvmField
        val LOG_LEVEL_API: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
        @JvmField
        var BASE_URL_HBL = ""
        @JvmField
        var BASE_URL_ZOHO = ""
        @JvmField
        var BASE_URL_STRAIN_REQUEST_FROM = ""
        @JvmField
        var BASE_URL_STRIP_API = ""
        @JvmField
        var applicationContext: Context? = null
        private var progressDialog: TransparentProgressDialog? = null
        fun getProgressDialogInstance(context: Context?): TransparentProgressDialog? {
            if (progressDialog == null) progressDialog = TransparentProgressDialog(
                context!!
            )
            return progressDialog
        }

        val macAddress: String
            get() {
                wManager = applicationContext!!.getSystemService(WIFI_SERVICE) as WifiManager
                val info = wManager!!.connectionInfo
                return info.macAddress
            }

        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        @get:RequiresApi(api = Build.VERSION_CODES.O)
        val iMEI: String
            get() {
                telephonyManager =
                    applicationContext!!.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                return if (ActivityCompat.checkSelfPermission(
                        applicationContext!!,
                        Manifest.permission.READ_PHONE_STATE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    telephonyManager!!.imei
                } else telephonyManager!!.imei
            }
        val os: String
            get() = "android"
        val iP: String
            get() {
                wManager = applicationContext!!.getSystemService(WIFI_SERVICE) as WifiManager
                return Formatter.formatIpAddress(wManager!!.connectionInfo.ipAddress)
            }
    }
}