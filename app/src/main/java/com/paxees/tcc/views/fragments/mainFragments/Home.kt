package com.paxees.tcc.views.fragments.mainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.models.mFilterDashboard
import com.paxees.tcc.network.ResponseHandlers.callbacks.NightTimeUsageCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.PlantsByTypeCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.PopularByThisWeekCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.NightTimeUsuageResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.PlantsByTypeResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.PopularByThisWeekResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.NightTimeUsageAdapter
import com.paxees.tcc.views.adapters.PlantTypeAdapter
import com.paxees.tcc.views.adapters.PopularAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class Home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        poplarPlants()
        rvPlantsType()
    }

    override fun onResume() {
        super.onResume()
        checkBackground()
    }

    @SuppressLint("WrongConstant")
    private fun checkBackground() {
        if (AppCompatDelegate.getDefaultNightMode()==2) {
            searchBg!!.background = resources.getDrawable(R.drawable.bg_bottom_line)
            searchtEt!!.background = resources.getDrawable(R.drawable.bg_border_filled)
            searchBg!!.setPadding(10,0,10,0)
        } else {
            searchBg!!.background = resources.getDrawable(android.R.color.transparent)
            searchtEt!!.setPadding(10,10,10,10)
            searchtEt!!.background = resources.getDrawable(R.drawable.bg_border_filled)
        }
    }

    private fun poplarPlants() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer2 = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.layoutManager = horizontalLayoutManagaer
        rvNightUseage.layoutManager = horizontalLayoutManagaer2
        TCCStore.getInstance().getPopularByThisWeek(RetrofitEnums.URL_HBL, object :
            PopularByThisWeekCallBack {
            override fun PopularByThisWeekSuccess(response: PopularByThisWeekResponse) {
                setCategories(response)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun PopularByThisWeekFailure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setCategories(response: PopularByThisWeekResponse) {
        var VideosAdapter = PopularAdapter(activity, response)
        rvPopular.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }

    private fun rvPlantsType() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPlantsType.layoutManager = horizontalLayoutManagaer
        TCCStore.getInstance().getPlantsByType(RetrofitEnums.URL_HBL, object :
            PlantsByTypeCallBack {
            override fun Success(response: PlantsByTypeResponse) {
                setPlantType(response)
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setPlantType(response: PlantsByTypeResponse) {
        var VideosAdapter = PlantTypeAdapter(activity, response)
        rvPlantsType.adapter = VideosAdapter
        VideosAdapter.notifyDataSetChanged()
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvNightUseage.layoutManager = horizontalLayoutManagaer
        TCCStore.getInstance().getNightTimeUsage(RetrofitEnums.URL_HBL, object :
           NightTimeUsageCallBack {
            override fun Success(response: NightTimeUsuageResponse) {
                setNightTimeUsage(response)
            }

            override fun  Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setNightTimeUsage(response: NightTimeUsuageResponse) {
        var VideosAdapter2 = NightTimeUsageAdapter(activity, response)
        rvNightUseage.setAdapter(VideosAdapter2)
        VideosAdapter2.notifyDataSetChanged()
        (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
    }

}