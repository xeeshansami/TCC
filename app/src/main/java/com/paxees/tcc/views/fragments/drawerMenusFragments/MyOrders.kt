package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.GetCartsCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.RemoveCartCallBack
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
            RemoveCartCallBack {
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
        updatePrice(data)
    }

    override fun onItemPlus(
        data: GetAddToCartResponse,
        view: View,
        position: Int,
        value: String
    ) {
        updateCart(data[0].key,value)
        updatePrice(data)
    }

    override fun onItemRemove(
        data: GetAddToCartResponse?,
        view: View?,
        position: Int,
        value: String?
    ) {
        removeCart(data!![0].key.toString())
    }
    private fun updatePrice(data: GetAddToCartResponse) {
        var total=0
        var subTotal=0
        for (i in 0 until data.size) {
            total += data[i].lineTotal.toInt()
            subTotal += data[i].lineSubtotal.toInt()
        }
        totolItemPrice.text="$"+total.toString()
        discount.text="$5"
        tax.text="$"+data[0].lineTax
        subtotal.text="$"+subTotal.toString()
        updateProfileBtn.text= "Pay $$subTotal now"
    }
}