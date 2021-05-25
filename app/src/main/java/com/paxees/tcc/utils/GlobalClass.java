package com.paxees.tcc.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import com.paxees.tcc.network.store.TenGermsStore;
import com.paxees.tcc.network.utils.Helper;

import org.jetbrains.annotations.Nullable;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import okhttp3.logging.HttpLoggingInterceptor;

public class GlobalClass extends TenGermsStore {
    public static WifiManager wManager;
    public static TelephonyManager telephonyManager;
    public static final HttpLoggingInterceptor.Level LOG_LEVEL_API = HttpLoggingInterceptor.Level.BODY;
    public static String BASE_URL_HBL = "";
    @Nullable
    public static Context applicationContext;
    private static TransparentProgressDialog progressDialog = null;
    public static TransparentProgressDialog getProgressDialogInstance(Context context) {
        if (progressDialog == null)
            progressDialog = new TransparentProgressDialog(context);
        return progressDialog;
    }
    public void showDialog(Context context) {
        progressDialog = getProgressDialogInstance(context);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideLoader() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        BASE_URL_HBL = Helper.getConfigValue(applicationContext, "BASE_URL_HBL");
    }

    public static String getMacAddress() {
        wManager = (WifiManager) GlobalClass.applicationContext.getSystemService(WIFI_SERVICE);
        WifiInfo info = wManager.getConnectionInfo();
        return info.getMacAddress();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getIMEI() {
        telephonyManager = (TelephonyManager) GlobalClass.applicationContext.getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(GlobalClass.applicationContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return telephonyManager.getImei();
        }
        return telephonyManager.getImei();
    }

    public static String getOs() {
        return "android";
    }

    public static String getIP() {
        wManager = (WifiManager) GlobalClass.applicationContext.getSystemService(WIFI_SERVICE);
        return Formatter.formatIpAddress(wManager.getConnectionInfo().getIpAddress());
    }

}
