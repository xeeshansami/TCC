package com.paxees.tcc.views.fragments.settingsFragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.AddressListCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.UpdateAddress2Request
import com.paxees.tcc.network.networkmodels.request.UpdateAddressRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import kotlinx.android.synthetic.main.change_address_popup.view.*
import kotlinx.android.synthetic.main.fragment_myaddresses.*
import kotlinx.android.synthetic.main.toolbar_theme.*

class AddNewAddresses : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    var address: MyAddressesListResponse? = null
    var alertDialog: AlertDialog? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myaddresses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        getAddressesList()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                findNavController().popBackStack()
            }
            R.id.addressChangeBtn -> {
                changeAddressAlert("Billing Address")
            }
            R.id.addressChangeBtn2 -> {
                changeAddressAlert("Shipping Address")
            }
            R.id.addressChangeBtn2 -> {


            }
        }
    }

    private fun changeAddressAlert(addressType: String) {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity as CIFRootActivity)
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.change_address_popup, null)
        dialogBuilder.setCancelable(false)
        dialogBuilder.setView(dialogView)
        alertDialog= dialogBuilder.create()
        dialogView.addresstype.text = addressType
        if(addressType=="Billing Address"){
            dialogView.address1Et.setText(address!!.billing.address1.toString())
            dialogView.address2Et.setText(address!!.billing.address2.toString())
        }else{
            dialogView.address1Et.setText(address!!.shipping.address1.toString())
            dialogView.address2Et.setText(address!!.shipping.address2.toString())
        }
        dialogView.updateBtn.setOnClickListener(View.OnClickListener {
            var address1 = dialogView.address1Et.text.toString().trim()
            var address2 = dialogView.address2Et.text.toString().trim()
            if(validation(address1,address2,dialogView)) {
                updateAddress(addressType,address1, address2)
            }
        })
        dialogView.cancelBtn.setOnClickListener(View.OnClickListener {
            alertDialog!!.dismiss()
        })
        alertDialog!!.show()
    }

    private fun validation(address1: String, address2: String, dialogView: View): Boolean {
        return if (TextUtils.isEmpty(address1)) {
            dialogView.address1Et!!.error = "Address 1 should not be empty"
            dialogView.address1Et!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(address2)) {
            dialogView.address2Et!!.error = "Address 2 should not be empty"
            dialogView.address2Et!!.requestFocus()
            false
        } else {
            true
        }
    }
    private fun updateAddress(
        addressType: String,
        address1: String,
        address2: String
    ) {
        var userid = (activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id
        var email = (activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].email
        var request=UpdateAddressRequest()
        var request2= UpdateAddress2Request()
        if (addressType == "Billing Address") {
            request2.billing.email=email
            request2.billing.address1=address1
            request2.billing.address2=address2
            (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
            TCCStore.instance!!.updateBillingAddress(RetrofitEnums.URL_HBL, userid,request2, object :
                AddressListCallBack {
                override fun Success(response: MyAddressesListResponse) {
                    ToastUtils.showToastWith(activity,"$addressType has been updated")
                    alertDialog!!.dismiss()
                    getAddressesList()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
        } else {
            request.shipping.email=email
            request.shipping.address1=address1
            request.shipping.address2=address2
            (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
            TCCStore.instance!!.updateShippingAddress(RetrofitEnums.URL_HBL, userid,request, object :
                AddressListCallBack {
                override fun Success(response: MyAddressesListResponse) {
                    ToastUtils.showToastWith(activity,"$addressType has been updated")
                    alertDialog!!.dismiss()
                    getAddressesList()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
        }

    }

    fun getAddressesList() {
        var userid = (activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getAddressList(RetrofitEnums.URL_HBL, userid, object :
            AddressListCallBack {
            override fun Success(response: MyAddressesListResponse) {
                rvAddressFunc(response)
                response.let { address=it }
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun rvAddressFunc(response: MyAddressesListResponse) {
        addressChangeBtn.setOnClickListener(this)
        addressChangeBtn2.setOnClickListener(this)
        addresstype1.text = "Billing Address"
        addressBilling.text =
            response.billing.address1 + " " + response.billing.city + " " + response.billing.country
        addresstype2.text = "Shipping Address"
        addressShipping.text =
            response.shipping.address1 + " " + response.shipping.city + " " + response.shipping.country
//        val rec: ArrayList<mFilterDashboard> = ArrayList<mFilterDashboard>()
//        val txt = ArrayList<String>()
//        val lbl = ArrayList<String>()
//        val img = ArrayList<Int>()
//        txt.add("Piata Unirii 2, Apartment23...")
//        txt.add("Piata Victoria 2, Apartment23...")
//        txt.add("Karachi Pakistan, Surjani...")
//        lbl.add("Home Address")
//        lbl.add("Work Address")
//        lbl.add("Office Address")
//        img.add(R.drawable.circle_img)
//        img.add(R.mipmap.ic_done)
//        img.add(R.drawable.circle_img)
//        for (i in txt.indices) {
//            val filterDashboard = mFilterDashboard()
//            filterDashboard.setTxt(txt[i])
//            filterDashboard.img = img[i]
//            filterDashboard.value = lbl[i]
//            rec.add(filterDashboard)
//        }
//        // set up the RecyclerView
//        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        rvAddresses.layoutManager = horizontalLayoutManagaer
//        var VideosAdapter = MyAddressesAdapter(activity, response)
//        rvAddresses.setAdapter(VideosAdapter)
//        VideosAdapter.notifyDataSetChanged()
    }
}