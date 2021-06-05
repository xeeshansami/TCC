package com.paxees.tcc.views.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.Dashboard
import com.paxees.tcc.models.Plants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.views.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_diagnose2.*
import kotlinx.android.synthetic.main.fragment_strain_form.*
import kotlinx.android.synthetic.main.fragment_strain_form.rvSeedsInterested
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header
import java.util.ArrayList

class Diagnose2 : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_diagnose2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = ""
        setGrowArea()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
              findNavController().navigateUp()
            }
        }
    }

    fun setGrowArea() {
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
        var adapter = RecyclerViewAdapter(requireActivity(), rec,0)
        rvGrowArea.setAdapter(adapter)
        adapter!!.notifyDataSetChanged()
    }
}