package com.paxees.tcc.views.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.network.ResponseHandlers.callbacks.RegisterCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.RegisterRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.store.TenGermsStore
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.managers.SharedPreferenceManager
import kotlinx.android.synthetic.main.fragment_forget.*
import kotlinx.android.synthetic.main.fragment_forget.mainLoginLayout
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class ForgotPassword : Fragment(), View.OnClickListener {
    var bt_registration: Button? = null
    var request: RegisterRequest? = null
    var numberEt: EditText? = null
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
    private fun checkBackground() {
        if((activity as launcher).sharedPreferenceManager.getIntFromSharedPreferences(SharedPreferenceManager.DARK_MODE)==1){
            mainLoginLayout.background=resources.getDrawable(R.color.blackLight)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_submit -> if (validation()) {
//                register()
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
        val email = numberEt!!.text.toString().trim { it <= ' ' }
        val promo = promoEt!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(email)) {
            numberEt!!.error = "Number should not be empty"
            numberEt!!.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(promo)) {
            promoEt!!.error = "Promo code should not be empty"
            promoEt!!.requestFocus()
            return false
        }
        return if (!maleRB!!.isChecked && !femaleRB!!.isChecked) {
            ToastUtils.showToastWith(activity, "Please check the gender first", "")
            false
        } else {
            true
        }
    }

    private fun register() {
        val number = numberEt!!.text.toString().trim { it <= ' ' }
        val promo = promoEt!!.text.toString().trim { it <= ' ' }
        (activity as launcher?)!!.globalClass!!.showDialog(activity)
        request = RegisterRequest()
        request!!.number = number
        if (maleRB!!.isChecked) {
            request!!.gender = "Male"
        } else if (femaleRB!!.isChecked) {
            request!!.gender = "Female"
        }
        request!!.promo = promo
        TenGermsStore.getInstance().getRegister(RetrofitEnums.URL_HBL, request, object : RegisterCallBack {
            override fun RegisterSuccess(response: BaseResponse) {
                ToastUtils.showToastWith(activity, response.msg)
                if (response.status) {
//                    NavHostFragment.findNavController(this@ForgotPassword).navigate(R.id.register_to_login)
                }
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }

            override fun RegisterFailure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.msg, "")
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }
        })
    }
}