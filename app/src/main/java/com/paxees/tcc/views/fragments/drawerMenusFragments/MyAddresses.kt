package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.network.ResponseHandlers.callbacks.AddressListCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.MyAddressesAdapter
import kotlinx.android.synthetic.main.fragment_add_payment_method.*
import kotlinx.android.synthetic.main.fragment_myaddresses.*
import kotlinx.android.synthetic.main.toolbar_theme.*

class MyAddresses : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
        }
    }

    fun getAddressesList(){
        var userid=(activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id
        TCCStore.getInstance().getAddressList(RetrofitEnums.URL_HBL,userid, object :
            AddressListCallBack {
            override fun  Success(response: MyAddressesListResponse) {
                rvAddressFunc(response)
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }

            override fun  Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun rvAddressFunc(response: MyAddressesListResponse) {
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
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvAddresses.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = MyAddressesAdapter(activity, response)
        rvAddresses.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }
}