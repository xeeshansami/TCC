package com.paxees.tcc.views.fragments.dignosticsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.Dashboard
import com.paxees.tcc.network.ResponseHandlers.callbacks.DiagnoseCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiagnoseListResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiagnoseListResponseItem
import com.paxees.tcc.network.networkmodels.response.models.DiagnoseResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_diagnose1.*
import kotlinx.android.synthetic.main.toolbar.*

class Diagnose1 : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_diagnose1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        onBackPressed()
    }

    fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    switchFragment(R.id.navigation_home)
                }
            })
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        diagnoseList.setOnClickListener(this)
        backBtn.setOnClickListener(this)
        insideBtn.setOnClickListener(this)
        outsideBtn.setOnClickListener(this)
        header.text = ""
        diaglosePageNoTv.text = "Create Diagnose"
        if (!arguments?.getString(Constants.DIAGNOSE_LIST_RESPONSE_ID).isNullOrEmpty()) {
            arguments?.getString(Constants.DIAGNOSE_LIST_RESPONSE_ID)?.let { getSelectedDiagnose(it) }
        }
    }

    private fun getSelectedDiagnose(id: String) {
        (activity as CIFRootActivity).globalClass?.showDialog(activity)
        TCCStore.instance!!.getSelectedDiagnose(
            RetrofitEnums.URL_HBL,
            id, object :
                DiagnoseCallBack {
                override fun Success(response: DiagnoseResponse) {
                    (activity as CIFRootActivity).sharedPreferenceManager.diagnose = response
                    diaglosePageNoTv.text = "Update Diagnose ID: "+response.id+" "+ response.title.rendered
                    if ((activity as CIFRootActivity).sharedPreferenceManager.diagnose.meta.isYourGrowInsideOrOutside == "inside") {
                        insideBtn.setTextColor(
                            ContextCompat.getColor(
                                activity as CIFRootActivity,
                                R.color.colorTheme
                            )
                        )
                    } else {
                        outsideBtn.setTextColor(
                            ContextCompat.getColor(
                                activity as CIFRootActivity,
                                R.color.colorTheme
                            )
                        )
                    }
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
            R.id.insideBtn -> {
                gotoNextScreens("inside")
            }
            R.id.outsideBtn -> {
                gotoNextScreens("outside")
            }
            R.id.diagnoseList -> {
                switchFragment(R.id.navigation_diagnose11)
            }
        }
    }

    private fun gotoNextScreens(value: String) {
        findNavController().navigate(R.id.diagnose1_to_diagonse2)
        (activity as CIFRootActivity).dignoseRequest!!.meta.isYourGrowInsideOrOutside = value
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
}