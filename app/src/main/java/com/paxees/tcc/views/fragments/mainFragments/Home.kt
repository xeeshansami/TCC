package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.Dashboard
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.models.mFilterDashboard
import com.paxees.tcc.utils.managers.SharedPreferenceManager
import com.paxees.tcc.views.adapters.PlantTypeAdapter
import com.paxees.tcc.views.adapters.PopularAdapter
import com.paxees.tcc.views.adapters.VideosAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_videos.*
import java.util.ArrayList

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
//        checkBackground()
        poplarPlants()
        rvPlantsType()
    }

    private fun checkBackground() {
        if ((activity as CIFRootActivity).sharedPreferenceManager.getIntFromSharedPreferences(SharedPreferenceManager.DARK_MODE) == 1) {
            searchBg!!.background = resources.getDrawable(R.drawable.bg_bottom_line)
            searchtEt!!.background = resources.getDrawable(R.drawable.card_border_profile)
        } else {
            searchBg!!.background = resources.getDrawable(android.R.color.transparent)
            searchtEt!!.background = resources.getDrawable(R.drawable.bg_border)
        }
    }

    fun poplarPlants() {
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
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer2 = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.layoutManager = horizontalLayoutManagaer
        rvNightUseage.layoutManager = horizontalLayoutManagaer2
        var VideosAdapter = PopularAdapter(activity, rec)
        rvPopular.setAdapter(VideosAdapter)
        rvNightUseage.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }

    fun rvPlantsType() {
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