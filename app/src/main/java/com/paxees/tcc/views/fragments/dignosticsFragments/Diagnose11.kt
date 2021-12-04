package com.paxees.tcc.views.fragments.dignosticsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.DiagnoseCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.DiagnoseListCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.ProductSearchCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiagnoseListResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiagnoseListResponseItem
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductSearchResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.DiagnoseAdapter
import com.paxees.tcc.views.adapters.DiscoveryAdapter
import kotlinx.android.synthetic.main.fragment_diagnose10.*
import kotlinx.android.synthetic.main.fragment_diagnose11.*
import kotlinx.android.synthetic.main.fragment_indoor.*
import kotlinx.android.synthetic.main.fragment_indoor.rvIndoor
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class Diagnose11 : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_diagnose11, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        headerRight.visibility = View.VISIBLE
        headerRight.text = "Home"
        headerRight.setOnClickListener(this)
        starBtn.setOnClickListener(this)
        header.text = ""
        getDiagnoseList()
    }

    private fun getDiagnoseList() {
        (activity as CIFRootActivity).globalClass?.showDialog(activity)
        TCCStore.instance!!.getDiagnoseList(RetrofitEnums.URL_HBL, object :
            DiagnoseListCallBack {
            override fun DiagnoseListSuccess(response: DiagnoseListResponse?) {
                setDiagnoseListResponse(response!!)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun DiagnoseListFailure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setDiagnoseListResponse(response: ArrayList<DiagnoseListResponseItem>) {
        val horizontalLayoutManagaer =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvDiagnoseList.layoutManager = horizontalLayoutManagaer
        var adapter = DiagnoseAdapter(
            activity,
            response, DiagnoseAdapter.ItemClickListener { view, position, response ->
                val bundle = bundleOf(Constants.DIAGNOSE_LIST_RESPONSE_ID to response.id.toString())
                switchFragment(R.id.navigation_diagnose1, bundle)
            })
        rvDiagnoseList.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                findNavController().navigateUp()
            }
            R.id.headerRight -> {
//                switchFragment(R.id.navigation_home)
                findNavController().navigate(
                    R.id.navigation_home,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(findNavController().graph.startDestination, true).build()
                )
            }
        }
    }

    private fun switchFragment(startDestId: Int, bundle: Bundle) {
        findNavController().navigate(
            startDestId,
            bundle,
            NavOptions.Builder().setPopUpTo(findNavController().graph.startDestination, true)
                .build()
        )
    }

}