package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.AddToCartCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.GetWishlistCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.RemoveProdCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.WishlistShareKeyByUserCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.AddToCartRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.*
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.WishlistAdapter
import kotlinx.android.synthetic.main.fragment_my_wishlist.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class MyWishlist : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        onBackPressed()
    }

    fun onBackPressed(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                switchFragment(R.id.navigation_home)
            }
        })
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.my_wishlist)
        getShareKey((activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id.toString())
    }

    private fun getShareKey(userID: String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!!!
            .getWishlistShareKeyByUser(RetrofitEnums.URL_HBL, userID.toInt(), object :
                WishlistShareKeyByUserCallBack {
                override fun Success(response: WishlistShareKeyByUserResponse) {
                    getWishlist(response[0].shareKey)
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    private fun deletePopup(key: String) {
        AlertDialog.Builder(activity as CIFRootActivity)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete this product from wishlist?") // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(
                android.R.string.yes
            ) { dialog, which ->
                removeCart(key)
            }
            .setNegativeButton(
                android.R.string.no
            ) { dialog, which ->
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun removeCart(key: String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.removeWishlistProd(RetrofitEnums.URL_HBL, key, object :
            RemoveProdCallBack {
            override fun Success(response: String) {
                ToastUtils.showToastWith(activity, response)
                getShareKey((activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id.toString())
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun getWishlist(sharekey: String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getWishlist(RetrofitEnums.URL_HBL, sharekey, object :
            GetWishlistCallBack {
            override fun Success(response: GetWishlistResponse) {
                rvWishlist(response)
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
        }
    }

    private fun switchFragment(startDestId: Int) {
        val navController = findNavController()
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }

    private fun rvWishlist(response: GetWishlistResponse) {
        // set up the RecyclerView
        val horizontalLayoutManagaer =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvWishList!!.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = WishlistAdapter(
            activity as CIFRootActivity,response,
            object : WishlistAdapter.ItemClickListener{
                override fun onItemClick(data: GetWishlistResponse?, view: View?, position: Int) {
                    addToCart(response[0].productId.toString())
                }
            },
            object : WishlistAdapter.ItemClickListener{
                override fun onItemClick(data: GetWishlistResponse?, view: View?, position: Int) {
                    deletePopup(data?.get(0)!!.itemId.toString())
                }
            }
           )
        rvWishList!!.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }

    private fun addToCart(prodId: String) {
        var request = AddToCartRequest()
        request.productId = prodId
        request.quantity = 1
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.addToCart(RetrofitEnums.URL_HBL, (activity as CIFRootActivity).token,request, object :
            AddToCartCallBack {
            override fun Success(response: AddtoCartResponse) {
                ToastUtils.showToastWith(activity, "Product has been added successfully")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }
}