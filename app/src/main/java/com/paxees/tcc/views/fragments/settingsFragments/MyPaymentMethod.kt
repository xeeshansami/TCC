package com.paxees.tcc.views.fragments.settingsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.PaymentMethodListCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.PaymentMethodListResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.PaymentMethodAdapter
import kotlinx.android.synthetic.main.fragment_add_payment_method.*
import kotlinx.android.synthetic.main.fragment_changedpwd.*
import kotlinx.android.synthetic.main.fragment_mypayment_method.*
import kotlinx.android.synthetic.main.fragment_strains.*
import kotlinx.android.synthetic.main.toolbar_theme.backBtn

class MyPaymentMethod : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypayment_method, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        addtoCart.setOnClickListener(this)
        getPaymentMethods()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
               findNavController().popBackStack()
            }
            R.id.addtoCart -> {
                findNavController().navigate(R.id.going_to_add_new_payment_method)
            }
        }
    }

    fun getPaymentMethods() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getPaymentMethods(
            RetrofitEnums.URL_HBL,
            object :
                PaymentMethodListCallBack {
                override fun Success(response: PaymentMethodListResponse) {
                    rvPaymentFunc(response)
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    private fun rvPaymentFunc(response: PaymentMethodListResponse) {
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvPayments.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = PaymentMethodAdapter(activity, response)
        rvPayments.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }
}