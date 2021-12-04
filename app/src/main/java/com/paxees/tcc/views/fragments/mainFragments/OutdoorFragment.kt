package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.*
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.AddToCartRequest
import com.paxees.tcc.network.networkmodels.request.AddToWishlistRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.*
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.DiscoveryAdapter
import kotlinx.android.synthetic.main.fragment_outdoor.*


class OutdoorFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outdoor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        getDiscover()
    }

    private fun getDiscover() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getDiscoverMenu(RetrofitEnums.URL_HBL, object :
            DiscoveryMenuCallBack {
            override fun Success(response: DiscoveryResponse) {
                response.get(1).categoryId.let {
                    getIndoorProducts(it)
                }
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun getIndoorProducts(cateId: String) {
        TCCStore.instance!!.getDiscoverProducts(RetrofitEnums.URL_HBL,"red",cateId, object :
            ProductSearchCallBack {
            override fun Success(response: ProductSearchResponse) {
                setProdcutSearch(response)

            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }
    private fun addToCart(prodId: String) {
        var request= AddToCartRequest()
        request.productId=prodId
        request.quantity=1
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.addToCart(RetrofitEnums.URL_HBL,(activity as CIFRootActivity).token,request, object :
            AddToCartCallBack {
            override fun Success(response: AddtoCartResponse) {
                ToastUtils.showToastWith(activity,"Product has been added successfully")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }
    private fun setProdcutSearch(response: ProductSearchResponse) {
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvOutdoor.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = DiscoveryAdapter(
            activity,
            response,
            DiscoveryAdapter.ItemClickListener { view, position, response ->
                addToCart(response[0].id.toString())
            },DiscoveryAdapter.ItemClickListener { view, position, response ->
                getShareKey((activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id.toString(), response[0].id.toString())
            })
        rvOutdoor.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
        (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
        }
    }
    private fun getShareKey(userID: String,prodId: String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getWishlistShareKeyByUser(RetrofitEnums.URL_HBL,userID.toInt(), object :
            WishlistShareKeyByUserCallBack {
            override fun Success(response: WishlistShareKeyByUserResponse) {
                addToWishlist(response[0].shareKey,prodId)
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }
    private fun addToWishlist(sharekey: String, prodId: String) {
        var request= AddToWishlistRequest()
        request.productId=prodId.toInt()
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.AddToWishlist(RetrofitEnums.URL_HBL,sharekey,request, object :
            AddToWishlistCallBack {
            override fun Success(response: AddToWishlistResponse) {
                ToastUtils.showToastWith(activity,"Product has been added to wishlist successfully")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
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

