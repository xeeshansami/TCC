package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.CategoryCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.models.CategoriesResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.BlogsAdapter
import kotlinx.android.synthetic.main.fragment_blogs.*
import kotlinx.android.synthetic.main.fragment_strains.*
import kotlinx.android.synthetic.main.toolbar.*

class BlogsFragment : Fragment(), View.OnClickListener, BlogsAdapter.ItemClickListener {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blogs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onBackPressed()
    }

    fun onBackPressed(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                switchFragment(R.id.navigation_home)
            }
        })
    }

    fun init() {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.Blog)
        rvStrainsFunc()
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
        val inflater = navController.navInflater
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }
    private fun rvStrainsFunc() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        // set up the RecyclerView
        TCCStore.instance!!.getCategories(RetrofitEnums.URL_HBL, object :
            CategoryCallBack {
            override fun CategorySuccess(response: CategoriesResponse?) {
                setCategories(response!!)

            }

            override fun  CategoryFailure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setCategories(response: CategoriesResponse) {
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvBlogs.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = BlogsAdapter(activity as CIFRootActivity, response)
        VideosAdapter.setClickListener(this)
        rvBlogs.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
        (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
    }

    override fun onItemClick(view: View?, position: Int,cat:CategoriesResponse) {
        var bundle=Bundle()
        bundle.putString(Constants.WEBVIEW_LINK,cat[position].link)
        findNavController().navigate(R.id.navigation_blogs_to_navigation_fragment_blogs_details,bundle)
    }


}