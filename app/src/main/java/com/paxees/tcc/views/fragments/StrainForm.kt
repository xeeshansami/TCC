package com.paxees.tcc.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.models.Plants
import com.paxees.tcc.network.networkmodels.response.models.Branch
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.views.adapters.ProductAdapter
import com.paxees.tcc.views.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products.rvProducts1
import kotlinx.android.synthetic.main.fragment_strain_form.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar_theme.*
import kotlinx.android.synthetic.main.toolbar_theme.backBtn
import java.util.ArrayList

class StrainForm : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_strain_form, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        setPlants()
        setSeeds()
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
    fun setPlants() {
        val rec: ArrayList<Plants> = ArrayList<Plants>()
        val txt = ArrayList<String>()
        txt.add("2-5")
        txt.add("6-11")
        txt.add("12-23")
        txt.add("24-49")
        txt.add("50-99")
        txt.add("100+")
        for (i in txt.indices) {
            val filterDashboard = Plants()
            filterDashboard.plantValue=txt[i].toString()
            rec.add(filterDashboard)
        }
//        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer = GridLayoutManager(activity, 5)
        rvPlantsInterested!!.layoutManager = horizontalLayoutManagaer
        var adapter = RecyclerViewAdapter(activity, rec)
        rvPlantsInterested.setAdapter(adapter)
        adapter!!.notifyDataSetChanged()
    }
    fun setSeeds() {
        val rec: ArrayList<Plants> = ArrayList<Plants>()
        val txt = ArrayList<String>()
        txt.add("10")
        txt.add("20")
        txt.add("30")
        txt.add("40")
        txt.add("50+")
        for (i in txt.indices) {
            val filterDashboard = Plants()
            filterDashboard.plantValue=txt[i].toString()
            rec.add(filterDashboard)
        }
//        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer = GridLayoutManager(activity, 5)
        rvSeedsInterested!!.layoutManager = horizontalLayoutManagaer
        var adapter = RecyclerViewAdapter(activity, rec)
        rvSeedsInterested.setAdapter(adapter)
        adapter!!.notifyDataSetChanged()
    }
}