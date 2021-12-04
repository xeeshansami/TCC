package com.paxees.tcc.views.fragments.dignosticsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_diagnose9.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class Diagnose9 : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_diagnose9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        yesBtn.setOnClickListener(this)
        noBtn.setOnClickListener(this)
        header.text = ""
        if (!(activity as CIFRootActivity).sharedPreferenceManager.diagnose.meta.doYouPhBalanceYourWater.isNullOrEmpty()) {
            when ((activity as CIFRootActivity).sharedPreferenceManager.diagnose.meta.doYouPhBalanceYourWater) {
                "Yes" -> {
                    yesBtn.setTextColor(ContextCompat.getColor(activity as CIFRootActivity, R.color.white))
                    yesBtn.background = resources.getDrawable(R.drawable.const_bg_border_square_green_2)
                }
                "No" -> {
                    noBtn.setTextColor(ContextCompat.getColor(activity as CIFRootActivity, R.color.white))
                    noBtn.background = resources.getDrawable(R.drawable.const_bg_border_square_green_2)
                }

            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                findNavController().popBackStack()
            }
            R.id.yesBtn -> {
                gotoNextScreens("Yes")
            }
            R.id.noBtn -> {
                gotoNextScreens("No")
            }
        }
    }

    private fun gotoNextScreens(value:String) {
        (activity as CIFRootActivity).dignoseRequest!!.meta.doYouPhBalanceYourWater=value
        findNavController().navigate(R.id.diagnose9_to_diagonse10)
    }
}