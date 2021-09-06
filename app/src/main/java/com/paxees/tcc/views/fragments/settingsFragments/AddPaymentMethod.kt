package com.paxees.tcc.views.fragments.settingsFragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.manojbhadane.PaymentCardView.OnPaymentCardEventListener
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.Dashboard
import com.paxees.tcc.network.ResponseHandlers.callbacks.AddNewCreditCardCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.PaymentMethodListCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.AddNewCreditCardResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.PaymentMethodListResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_add_payment_method.*


class AddPaymentMethod : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    var header: TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_payment_method, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        header=requireView().findViewById(R.id.header)
        header!!.setText("Add New Payment Method")
        sessionManager = SessionManager(activity)
        //Callbacks
        creditCard.setCardTitle("Add New\nCredit Card")
        //Callbacks
        creditCard.setOnPaymentCardEventListener(object : OnPaymentCardEventListener {
            override fun onCardDetailsSubmit(
                month: String,
                year: String,
                cardNumber: String,
                cvv: String
            ) {
                Log.i("CardDetails","$month, $year, $cardNumber, $cvv")
                addNewCreditCard(cardNumber,month,year,cvv)
            }

            override fun onError(error: String) {
            }

            override fun onCancelClick() {}
        })
    }


    fun addNewCreditCard(number:String,month:String,year:String,cvv:String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.addNewCreditCard(
            RetrofitEnums.URL_STRIP_API,number,month,year,cvv,
            object :
                AddNewCreditCardCallBack {
                override fun Success(response: AddNewCreditCardResponse) {
                    ToastUtils.showToastWith(activity, "Your TokenID: ${response.card.id}\nCardDetails: Date: $month/$year\nCard: $number\n Card:$cvv\nPayment method add successfully!",5000)
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                    findNavController().popBackStack()
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
               findNavController().popBackStack()
            }
        }
    }


}