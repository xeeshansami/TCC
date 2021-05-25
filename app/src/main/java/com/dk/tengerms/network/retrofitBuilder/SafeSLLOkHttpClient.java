package com.dk.tengerms.network.retrofitBuilder;

import android.content.Context;

import com.dk.tengerms.network.enums.RetrofitEnums;
import com.dk.tengerms.network.retrofitBuilder.RetrofitBuilder;
import com.dk.tengerms.utils.Constants;
import com.dk.tengerms.utils.GlobalClass;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class SafeSLLOkHttpClient {
    public static OkHttpClient getUnsafeOkHttpClient(Context context, Boolean isHblLink) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(GlobalClass.LOG_LEVEL_API);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(interceptor);
            builder.addInterceptor(new ChuckInterceptor(context));
            builder.callTimeout(Constants.API_CONNECT_TIMEOUT, TimeUnit.SECONDS);
            String key = "sha256/MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqXhtcl9DxW+eIt0ShLEcy/kt4jTxPMAmrK9Q7OTjVNIjWwd0vsKeM04YbaYSAhvggvAQsvy67Ip4Tp16Mpd88TvPwdvcqCUyQjLIsVvhUBN59bOgIK7Z/7kKJPuom5BOtJ6n4PblZMRkWj4Jo2OxqZj77yZN9Eh/jpOiAM3ymV3a+lQbPEgidwTP77Cud6RPzQx7XyTEyxAsQelEX4y+GT3vWfvnLJQhF1ElaU0VUowXNE8UPsXwCMnQVMLo0UbGG5jMQiTrQSS9YLBF8D8gQIDAQAB";
            builder.certificatePinner(new CertificatePinner.Builder().add("www.tengrams.com", key).build());
            builder.connectTimeout(Constants.API_CONNECT_TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(Constants.API_READ_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(Constants.API_WRITE_TIMEOUT, TimeUnit.SECONDS).build();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            if (isHblLink)
                builder.addNetworkInterceptor(new RetrofitBuilder.NetworkInterceptorHBL(context));
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean enableNetworkInterceptor(String baseUrl) {
        return baseUrl == RetrofitEnums.URL_HBL.getUrl();
    }
}