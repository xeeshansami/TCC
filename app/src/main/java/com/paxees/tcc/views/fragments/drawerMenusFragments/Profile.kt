package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.Dashboard
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.managers.SharedPreferenceManager
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.mainLoginLayout
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class Profile : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        checkBackground()
    }
    @SuppressLint("WrongConstant")
    private fun checkBackground() {
        if(AppCompatDelegate.getDefaultNightMode()==2){
            mainLayout.background=resources.getDrawable(R.color.colorPrimary)
        }else{
            mainLayout.background=resources.getDrawable(R.drawable.loginbg)
        }
    }
    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.profile)
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

    private fun logout() {
        (activity as Dashboard?)!!.globalClass.showDialog(activity)
        val handler = Handler()
        handler.postDelayed({
            (activity as Dashboard?)!!.globalClass.hideLoader()
            sessionManager!!.setLogin(false)
            activity!!.finish()
        }, 1500)
    }

    private fun couponRedemption() {
        (activity as Dashboard?)!!.globalClass.showDialog(activity)
        val handler = Handler()
        handler.postDelayed({ (activity as Dashboard?)!!.globalClass.hideLoader() }, 1500)
    }

    private fun referAFriend() {
        (activity as Dashboard?)!!.globalClass.showDialog(activity)
        val handler = Handler()
        handler.postDelayed({ (activity as Dashboard?)!!.globalClass.hideLoader() }, 1500)
    }

    private fun myProfile() {
        (activity as Dashboard?)!!.globalClass.showDialog(activity)
        val handler = Handler()
        handler.postDelayed({ (activity as Dashboard?)!!.globalClass.hideLoader() }, 1500)
    }

    private fun changePwd() {
        (activity as Dashboard?)!!.globalClass.showDialog(activity)
        val handler = Handler()
        handler.postDelayed({ (activity as Dashboard?)!!.globalClass.hideLoader() }, 1500)
    }

    private fun openCoupons() {
        (activity as Dashboard?)!!.globalClass.showDialog(activity)
        val handler = Handler()
        handler.postDelayed({ (activity as Dashboard?)!!.globalClass.hideLoader() }, 1500)
    }
}