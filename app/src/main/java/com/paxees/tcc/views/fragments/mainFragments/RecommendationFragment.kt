package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.Dashboard
import com.paxees.tcc.network.ResponseHandlers.callbacks.DiscoveryMenuCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.ProductSearchCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.DiscoveryResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.ProductSearchResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.IndoorAdapter
import com.paxees.tcc.views.adapters.ProductSearchAdapter
import com.paxees.tcc.views.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_discovery.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.rvPopular
import kotlinx.android.synthetic.main.fragment_outdoor.*
import kotlinx.android.synthetic.main.fragment_outdoor.rvOutdoor
import kotlinx.android.synthetic.main.fragment_recommendation.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar_theme.*
import kotlinx.android.synthetic.main.toolbar_theme.backBtn
import kotlinx.android.synthetic.main.toolbar_theme.header


class RecommendationFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommendation, container, false)
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
        TCCStore.getInstance().getDiscoverMenu(RetrofitEnums.URL_HBL, object :
            DiscoveryMenuCallBack {
            override fun Success(response: DiscoveryResponse) {
                response.get(2).categoryId.let {
                    getIndoorProducts(it)
                }
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun getIndoorProducts(cateId: String) {
        TCCStore.getInstance().getDiscoverProducts(RetrofitEnums.URL_HBL,"red",cateId, object :
            ProductSearchCallBack {
            override fun Success(response: ProductSearchResponse) {
                setProdcutSearch(response)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }
    private fun setProdcutSearch(response: ProductSearchResponse) {
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvRecommendation.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = IndoorAdapter(activity, response)
        rvRecommendation.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
        }
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

