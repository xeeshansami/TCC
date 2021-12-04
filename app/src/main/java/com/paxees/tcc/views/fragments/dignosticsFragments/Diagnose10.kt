package com.paxees.tcc.views.fragments.dignosticsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.network.ResponseHandlers.callbacks.DiagnoseCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.LoginCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.SingleLocationDetailsCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.LoginRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.LoginResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.SingleLocationDetailsResponse
import com.paxees.tcc.network.networkmodels.response.models.DiagnoseResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_diagnose10.*
import kotlinx.android.synthetic.main.fragment_diagnose10.noBtn
import kotlinx.android.synthetic.main.fragment_diagnose10.yesBtn
import kotlinx.android.synthetic.main.fragment_diagnose8.*
import kotlinx.android.synthetic.main.fragment_diagnose9.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class Diagnose10 : Fragment(), View.OnClickListener {
    var tvCoupons: TextView? = null
    var tvChangePwd: TextView? = null
    var tvMyProfile: TextView? = null
    var tvReferAFriend: TextView? = null
    var tvCouponsRedemption: TextView? = null
    var tvLogout: TextView? = null
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diagnose10, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        yesBtn.setOnClickListener(this)
        noBtn.setOnClickListener(this)
        header.text = ""
        if (!(activity as CIFRootActivity).sharedPreferenceManager.diagnose.meta.olderlowerLeavesAffected.isNullOrEmpty()) {
            when ((activity as CIFRootActivity).sharedPreferenceManager.diagnose.meta.olderlowerLeavesAffected) {
                "Yes" -> {
                    yesBtn.setTextColor(ContextCompat.getColor(activity as CIFRootActivity, R.color.white))
                    yesBtn.background =
                        resources.getDrawable(R.drawable.const_bg_border_square_green_2)
                }
                "No" -> {
                    noBtn.setTextColor(ContextCompat.getColor(activity as CIFRootActivity, R.color.white))
                    noBtn.background =
                        resources.getDrawable(R.drawable.const_bg_border_square_green_2)
                }

            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                findNavController().popBackStack()
            }
            R.id.yesBtn -> {
                gotoNextScreens("Yes")
            }
            R.id.noBtn -> {
                gotoNextScreens("No")
            }
        }
    }

    private fun gotoNextScreens(value: String) {
        (activity as CIFRootActivity).dignoseRequest!!.meta.olderlowerLeavesAffected = value
        login()
    }

    private fun login() {
        val email = "theclonetest"
        val pwd = "TheCloneTest1@3\$5"
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        var request = LoginRequest()
        request.username = email
        request.password = pwd
        TCCStore.instance!!.getLogin(RetrofitEnums.URL_HBL, request, object : LoginCallBack {
            override fun LoginSuccess(response: LoginResponse) {
                if ((activity as CIFRootActivity).sharedPreferenceManager.diagnose.id!= 0) {
                    editDiagnose((activity as CIFRootActivity).sharedPreferenceManager.diagnose.id.toString(),response.token)
                } else {
                    getDianoseCreated(response.token)
                }
            }

            override fun LoginFailure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun getDianoseCreated(token: String) {
        TCCStore.instance!!.diagnoseCreate(RetrofitEnums.URL_HBL,
            "Bearer $token", (activity as CIFRootActivity).dignoseRequest, object :
                DiagnoseCallBack {
                override fun Success(response: DiagnoseResponse) {
                    (activity as CIFRootActivity)!!.sharedPreferenceManager.diagnose= DiagnoseResponse()
                    ToastUtils.showToastWith(activity, "Diagnosed completed successfully!")
                    findNavController().navigate(R.id.diagnose10_to_diagonse11)
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    private fun editDiagnose(id: String, token: String) {
        TCCStore.instance!!.editDiagnose(RetrofitEnums.URL_HBL, id,
            "Bearer $token", (activity as CIFRootActivity).dignoseRequest, object :
                DiagnoseCallBack {
                override fun Success(response: DiagnoseResponse) {
                    (activity as CIFRootActivity)!!.sharedPreferenceManager.diagnose= DiagnoseResponse()
                    ToastUtils.showToastWith(activity, "Edited diagnose successfully!")
                    findNavController().navigate(R.id.diagnose10_to_diagonse11)
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }
}