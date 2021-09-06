package com.paxees.tcc.views.fragments.settingsFragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.ChangePasswordCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.ChangePasswordRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.ChangePasswordResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_changedpwd.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar_theme.*

class ChangePassword : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_changedpwd, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        bt_submit.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                findNavController().popBackStack()
            }
            R.id.bt_submit -> {
                if (validation()) {
                    changePassword()
                }
            }
        }
    }

    fun validation(): Boolean {
        val pwd = etNewPassword!!.text.toString().trim { it <= ' ' }
        val confirmPwd = etConfirmPassword!!.text.toString().trim { it <= ' ' }
        return if (TextUtils.isEmpty(pwd)) {
            etNewPassword!!.error = "New Password should not be empty"
            etNewPassword!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(confirmPwd)) {
            etConfirmPassword!!.error = "Confirm New Password should not be empty"
            etConfirmPassword!!.requestFocus()
            false
        } else if (pwd != confirmPwd) {
            etNewPassword!!.error = "New & Confirm New Password should be same"
            etNewPassword!!.requestFocus()
            false
        } else {
            true
        }
    }


    fun changePassword() {
        var userid = (activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id
        var request = ChangePasswordRequest()
        request.password = etConfirmPassword.text.toString().trim()
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.changePassword(
            RetrofitEnums.URL_HBL,
            userid.toString(),
            request,
            object :
                ChangePasswordCallBack {
                override fun Success(response: ChangePasswordResponse) {
                    ToastUtils.showToastWith(activity, "Password has been updated")
                    findNavController().popBackStack()
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }
}