package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.models.mFilterDashboard
import com.paxees.tcc.network.ResponseHandlers.callbacks.VideosListCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.VideosListResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.VideosAdapter
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_videos.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class Videos : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.Videos)
        getVideosList()
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

    fun videos(response: VideosListResponse) {
        // set up the RecyclerView
        val horizontalLayoutManagaer =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer2 =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer3 =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvForYou.layoutManager = horizontalLayoutManagaer
        rvTraining.layoutManager = horizontalLayoutManagaer2
        rvNewVideos.layoutManager = horizontalLayoutManagaer3
        var videosAdapter = VideosAdapter((activity as CIFRootActivity), response)
        rvForYou.adapter = videosAdapter
        rvTraining.adapter = videosAdapter
        rvNewVideos.adapter = videosAdapter
        videosAdapter.notifyDataSetChanged()
    }


    private fun getVideosList() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getVideosList(RetrofitEnums.URL_HBL, object :
            VideosListCallBack {
            override fun Success(response: VideosListResponse) {
                videos(response)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }
}