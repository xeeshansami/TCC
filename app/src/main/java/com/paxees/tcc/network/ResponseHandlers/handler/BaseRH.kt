package com.paxees.tcc.network.ResponseHandlers.handler

import com.google.gson.Gson
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.utils.GlobalClass
import com.paxees.tcc.utils.ToastUtils
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

abstract class BaseRH<T> : Callback<T> {
    var globalClass = GlobalClass.applicationContext!!.applicationContext as GlobalClass
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response)
        } else {
            val body = response.errorBody()?.charStream()?.readText()
            var jsonResponse = JSONObject(body)
            var code = jsonResponse.get("code") as String
            var message = jsonResponse.get("message") as String
            var baseResponse=BaseResponse()
            baseResponse.code=code
            baseResponse.message=message
            onFailure(baseResponse!!)
//            ToastUtils.normalShowToast(
//                globalClass.applicationContext,
//                "Something went wrong with\nCode: $code \nMessage: $message\nplease try again",
//                3000
//            )
            globalClass.hideLoader()
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t.message.equals("timeout", ignoreCase = true)) {
            /*When server no response in 30 seconds*/
//            onFailure(new BaseResponse("-1", false, "Connection timeout, please check your internet connection, please try again "));
            globalClass.hideLoader()
        } else {
            if (t is SocketTimeoutException) {
                /*When server no response in 30 seconds*/
//                onFailure(new BaseResponse("-1", false, "Connection timeout, please check your internet connection, please try again "));
                globalClass.hideLoader()
            } else {
                /*When something unexpected error occurred.*/
                globalClass.hideLoader()
            }
        }
    }

    protected abstract fun onSuccess(response: Response<T>?)
    protected abstract fun onFailure(response: BaseResponse?)
}