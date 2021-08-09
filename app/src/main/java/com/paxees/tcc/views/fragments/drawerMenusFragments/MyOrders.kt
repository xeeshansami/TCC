package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.GetCartsCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.RemoveProdCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateCartCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetAddToCartResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateCartResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.CartAdapter
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header


class MyOrders : Fragment(), View.OnClickListener, CartAdapter.onItemMinus, CartAdapter.onItemPlus,
    CartAdapter.onItemRemove {
    var sessionManager: SessionManager? = null
    var total=0
    var subTotal=0
    var disc=0
    var tx=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.myCart)
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvCarts.layoutManager = horizontalLayoutManagaer
        getCarts()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
        }
    }

    private fun switchFragment(startDestId: Int) {
        val navController = findNavController()
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }

    private fun rvCart(response: GetAddToCartResponse) {
        // set up the RecyclerView
        var VideosAdapter = CartAdapter(activity, response, this, this,this)
        rvCarts.adapter = VideosAdapter
        VideosAdapter.notifyDataSetChanged()
    }
    private fun getCarts() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.getInstance().getCarts(RetrofitEnums.URL_HBL, object :
            GetCartsCallBack {
            override fun Success(response: GetAddToCartResponse) {
                rvCart(response)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun deletePopup(data: GetAddToCartResponse?, key: String) {
        AlertDialog.Builder(activity as CIFRootActivity)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete this product?") // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.yes
            ) { dialog, which ->
                removeCart(key)
                updatePrice(data!!,2)
            }
            .setNegativeButton(android.R.string.no
            ) { dialog, which ->
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun updateCart(key:String,quantity: String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.getInstance().updateCart(RetrofitEnums.URL_HBL,key,quantity, object :
            UpdateCartCallBack {
            override fun Success(response: UpdateCartResponse) {
                ToastUtils.showToastWith(activity,response.message)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun removeCart(key:String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.getInstance().removeCart(RetrofitEnums.URL_HBL,key, object :
            RemoveProdCallBack {
            override fun Success(response: String) {
                ToastUtils.showToastWith(activity,response)
                getCarts()
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    override fun onItemMinus(
        data: GetAddToCartResponse,
        view: View,
        position: Int,
        value: String
    ) {
        updateCart(data[0].key,value)
        updatePrice(data,0)
    }

    override fun onItemPlus(
        data: GetAddToCartResponse,
        view: View,
        position: Int,
        value: String
    ) {
        updateCart(data[0].key,value)
        updatePrice(data,1)
    }

    override fun onItemRemove(
        data: GetAddToCartResponse?,
        view: View?,
        position: Int,
        value: String?
    ) {
        deletePopup(data,data!![0].key.toString())
    }
    private fun updatePrice(data: GetAddToCartResponse,lessAdd:Int) {
        for (i in 0 until data.size) {
            when (lessAdd) {
                1 -> {
                    total += data[i].lineTotal.toInt()
                    subTotal += data[i].lineSubtotal.toInt()
                    disc=5
                    tx=data[0].lineTax
                    updateProfileBtn.isEnabled=true
                }
                0 -> {
                    if(total>0 && subTotal>0) {
                        total -= data[i].lineTotal.toInt()
                        subTotal -= data[i].lineSubtotal.toInt()
                        disc = 5
                        tx = data[0].lineTax
                        updateProfileBtn.isEnabled = true
                    }
                }
                else -> {
                    total=0
                    subTotal=0
                    disc=0
                    tx=0
                    updateProfileBtn.isEnabled=false
                }
            }
        }
        totolItemPrice.text= "$$total"
        discount.text="$$disc"
        tax.text="$$tx"
        subtotal.text="$"+subTotal.toString()
        updateProfileBtn.text= "Pay $$subTotal now"
    }
}