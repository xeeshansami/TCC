package com.paxees.tcc.views.fragments.dignosticsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_diagnose1.*
import kotlinx.android.synthetic.main.toolbar.*

class Diagnose1 : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_diagnose1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        onBackPressed()
    }

    fun onBackPressed(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                switchFragment(R.id.navigation_home)
            }
        })
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        insideBtn.setOnClickListener(this)
        outsideBtn.setOnClickListener(this)
        header.text = ""
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
            R.id.insideBtn -> {
                gotoNextScreens("inside")
            }
            R.id.outsideBtn -> {
                gotoNextScreens("outside")
            }
        }
    }
    private fun gotoNextScreens(value:String) {
        findNavController().navigate(R.id.diagnose1_to_diagonse2)
        (activity as CIFRootActivity).dignoseRequest!!.meta.isYourGrowInsideOrOutside=value
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