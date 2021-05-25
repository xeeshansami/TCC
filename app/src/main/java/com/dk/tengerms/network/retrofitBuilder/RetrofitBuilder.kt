package com.dk.tengerms.network.retrofitBuilder

import android.content.Context
import android.text.TextUtils
import com.dk.tengerms.network.apiInterface.APIInterface
import com.dk.tengerms.network.enums.RetrofitEnums
import com.dk.tengerms.network.gson.GsonProvider
import com.dk.tengerms.utils.Constants
import com.dk.tengerms.utils.GlobalClass
import com.dk.tengerms.utils.GlobalClass.getIMEI
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap


object RetrofitBuilder {
    private val retrofitHashMap = HashMap<String, APIInterface>()
    fun getRetrofitInstance(context: Context, url: RetrofitEnums): APIInterface {
        val baseUrl = url.url
        val okHttpClient = SafeSLLOkHttpClient.getUnsafeOkHttpClient(context, enableNetworkInterceptor(baseUrl))
        if (!retrofitHashMap.containsKey(baseUrl)
                || retrofitHashMap[baseUrl] == null) {
            synchronized(this) {
                val retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(GsonProvider.getInstance()))
                        .addConverterFactory(PageAdapter.FACTORY)
                        .client(getOkHttpClient(context, enableNetworkInterceptor(baseUrl)))

                val restAPI = retrofit.build().create<APIInterface>(APIInterface::class.java)
                retrofitHashMap[baseUrl] = restAPI
                return restAPI
            }
        }
        return retrofitHashMap[baseUrl]!!
    }

    public fun enableNetworkInterceptor(baseUrl: String): Boolean {
        return baseUrl == RetrofitEnums.URL_HBL.url
    }

    public fun getOkHttpClient(context: Context, isHblLink: Boolean): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = GlobalClass.LOG_LEVEL_API
        }

        val builder = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(ChuckInterceptor(context))
                .connectTimeout(Constants.API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.API_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.API_WRITE_TIMEOUT, TimeUnit.SECONDS)
        if (isHblLink)
            builder.addNetworkInterceptor(NetworkInterceptorHBL(context))

        return builder.build()
    }

    class NetworkInterceptorHBL(private val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
//           var token = "Bearer " + (context.applicationContext as GlobalClass).hrToken
            var token = "test"
            val headerTag = original.header(APIInterface.HEADER_TAG)
            val builder = original.newBuilder()
            if (!(headerTag != null || token.equals("", ignoreCase = true))) {
                builder.addHeader("hrtoken", token)
            }
            val request = builder
                    .addHeader("os", "ANDROID") // @TODO: add OS method
                    .addHeader("ip", GlobalClass.getIP()) // @TODO: add IP method
                    .addHeader("Content-Type", "text/plain") // @TODO: add IP method
                    .addHeader("mac-address", GlobalClass.getMacAddress()) // @TODO: add MAC Address method
                    .addHeader("uuid", UUID.randomUUID().toString()) // @TODO: add MAC Address method
                    .removeHeader(APIInterface.HEADER_TAG)
                    .method(original.method, original.body)
                    .build()
            val response = chain.proceed(request)
            return response
        }

    }

}