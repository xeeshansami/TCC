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
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.models.mFilterDashboard
import com.paxees.tcc.network.ResponseHandlers.callbacks.CategoriesCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.RegisterCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.RegisterRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.CategoriesResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.managers.SharedPreferenceManager
import com.paxees.tcc.views.adapters.PlantTypeAdapter
import com.paxees.tcc.views.adapters.PopularAdapter
import kotlinx.android.synthetic.main.fragment_diagnose2.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_videos.*
import kotlinx.android.synthetic.main.toolbar_theme.*
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
        (activity as launcher?)!!.globalClass!!.showDialog(activity)
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer2 = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.layoutManager = horizontalLayoutManagaer
        rvNightUseage.layoutManager = horizontalLayoutManagaer2
        TCCStore.getInstance().getCategories(RetrofitEnums.URL_HBL, object : CategoriesCallBack {
            override fun CategoriesSuccess(response: CategoriesResponse) {
                setCategories(response)
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }

            override fun CategoriesFailure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.msg, "")
                (activity as launcher?)!!.globalClass!!.hideLoader()
            }
        })




        val rec: ArrayList<mFilterDashboard> = ArrayList<mFilterDashboard>()
        val txt = ArrayList<String>()
        val img = ArrayList<Int>()
        txt.add("Black Berry")
        txt.add("Gorilla Plants")
        txt.add("Natural Plants")
        txt.add("Flowers")
        txt.add("Actual Plants")
        img.add(R.drawable.blue_dream2)
        img.add(R.drawable.bruce_banner)
        img.add(R.drawable.diagnose10img)
        img.add(R.drawable.blueberry_og)
        img.add(R.drawable.diagnose9image)
        for (i in txt.indices) {
            val filterDashboard = mFilterDashboard()
            filterDashboard.setTxt(txt[i])
            filterDashboard.img = img[i]
            rec.add(filterDashboard)
        }




        rec.reverse()
//        var VideosAdapter2 = PopularAdapter(activity, rec)
//        rvNightUseage.setAdapter(VideosAdapter2)
//        VideosAdapter2.notifyDataSetChanged()
    }

    private fun setCategories(response: CategoriesResponse) {
//        var VideosAdapter = PopularAdapter(activity, response.get(0))
//        rvPopular.setAdapter(VideosAdapter)
//        VideosAdapter.notifyDataSetChanged()
    }

    private fun rvPlantsType() {
        val rec: ArrayList<mFilterDashboard> = ArrayList<mFilterDashboard>()
        val txt = ArrayList<String>()
        val img = ArrayList<Int>()
        txt.add("Indica")
        txt.add("Hybird")
        txt.add("Plants")
        txt.add("Blue")
        txt.add("Sativable")
        img.add(R.drawable.blackberry)
        img.add(R.drawable.bling)
        img.add(R.drawable.blue_dream2)
        img.add(R.drawable.blueberry_og)
        img.add(R.drawable.bruce_banner)
        for (i in txt.indices) {
            val filterDashboard = mFilterDashboard()
            filterDashboard.setTxt(txt[i])
            filterDashboard.img = img[i]
            rec.add(filterDashboard)
        }
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//       val horizontalLayoutManagaer = GridLayoutManager(activity, 1)
        rvPlantsType.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = PlantTypeAdapter(activity, rec)
        rvPlantsType.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }

}