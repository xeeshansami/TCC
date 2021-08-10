package com.paxees.tcc.views.fragments.signFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.network.ResponseHandlers.callbacks.ForgetPasswordCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateProfileCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.RegistrationRequest
import com.paxees.tcc.network.networkmodels.request.UpdateProfileRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.ForgetPasswordResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateProfileResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_forget.*
import kotlinx.android.synthetic.main.fragment_forget.mainLoginLayout
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class ForgotPassword : Fragment(), View.OnClickListener {
    var bt_registration: Button? = null
    var request: RegistrationRequest? = null
    var promoEt: EditText? = null
    var maleRB: RadioButton? = null
    var femaleRB: RadioButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        checkBackground()
        bt_submit!!.setOnClickListener(this)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.forgetPwd)
    }
    @SuppressLint("WrongConstant")
    private fun checkBackground() {
        if(AppCompatDelegate.getDefaultNightMode()==2){
            mainLoginLayout.background=resources.getDrawable(R.color.colorPrimary)
        }else{
            mainLoginLayout.background=resources.getDrawable(R.drawable.loginbg)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_submit -> if (validation()) {
                forgetPassword()
            }
            R.id.backBtn -> {
                switchFragment(R.id.login)
            }
        }
    }

    private fun switchFragment(startDestId: Int) {
//        val fragmentContainer = view?.findViewById<View>(R.id.nav_host)
//        val navController = Navigation.findNavController(fragmentContainer!!)
        val navController = findNavController()
        val inflater = navController.navInflater
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }

    fun validation(): Boolean {
        val email = emailEt!!.text.toString().trim { it <= ' ' }
        return if (TextUtils.isEmpty(email)) {
            emailEt!!.error = "Email should not be empty"
            emailEt!!.requestFocus()
            false
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            emailEt!!.error = "Email should be valid"
            emailEt!!.requestFocus()
            false
        }else {
            true
        }
    }

    private fun forgetPassword() {
        val email = emailEt!!.text.toString().trim { it <= ' ' }
        (activity as launcher?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getForgetPassword(RetrofitEnums.URL_HBL,email.toString(),object :
            ForgetPasswordCallBack {
            override fun Success(response: ForgetPasswordResponse) {
                ToastUtils.showToastWith(activity,"Reset email sent to your mailbox.")
                switchFragment(R.id.login)
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }
        })
    }
}