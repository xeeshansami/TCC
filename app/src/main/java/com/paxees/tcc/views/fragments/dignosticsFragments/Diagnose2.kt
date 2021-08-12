package com.paxees.tcc.views.fragments.dignosticsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.models.Plants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.views.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_diagnose2.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header
import java.util.ArrayList

class Diagnose2 : Fragment(), View.OnClickListener, RecyclerViewAdapter.ItemClickListener {
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
        diaglosePageNoTv.text = "2/8"
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
        txt.add("<100")
        txt.add("<500")
        txt.add("<800")
        txt.add("1000")
        txt.add("1500")
        txt.add("2000")
        txt.add("3000")
        txt.add("4000")
        txt.add("5000+")
        for (i in txt.indices) {
            val filterDashboard = Plants()
            filterDashboard.plantValue=txt[i].toString()
            rec.add(filterDashboard)
        }
//        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer = GridLayoutManager(activity, 3)
        rvGrowArea!!.layoutManager = horizontalLayoutManagaer
        var adapter = RecyclerViewAdapter(requireActivity(), rec,2)
        rvGrowArea.setAdapter(adapter)
        adapter.setClickListener(this)
        adapter!!.notifyDataSetChanged()
    }

    override fun onItemClick(view: View?, position: Int,value:String) {
        findNavController().navigate(R.id.diagnose2_to_diagonse3)
        (activity as CIFRootActivity).dignoseRequest!!.meta.whatIsTheSpaceOfYourGrowAreaSqFt=value
    }
}