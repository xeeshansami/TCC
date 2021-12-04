package com.paxees.tcc.views.fragments.dignosticsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_diagnose6.*
import kotlinx.android.synthetic.main.fragment_diagnose7.*
import kotlinx.android.synthetic.main.fragment_diagnose7.diaglosePageNoTv
import kotlinx.android.synthetic.main.fragment_diagnose7.diagnosebtn1
import kotlinx.android.synthetic.main.fragment_diagnose7.diagnosebtn2
import kotlinx.android.synthetic.main.fragment_diagnose7.diagnosebtn3
import kotlinx.android.synthetic.main.fragment_diagnose7.diagnosebtn4
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header

class Diagnose7 : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_diagnose7, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        diagnosebtn1.setOnClickListener(this)
        diagnosebtn2.setOnClickListener(this)
        diagnosebtn3.setOnClickListener(this)
        diagnosebtn4.setOnClickListener(this)
        header.text = ""
        diaglosePageNoTv.text = "7/8"
        if (!(activity as CIFRootActivity).sharedPreferenceManager.diagnose.meta.areYouUsingAnyNutrients.isNullOrEmpty()) {
            when ((activity as CIFRootActivity).sharedPreferenceManager.diagnose.meta.areYouUsingAnyNutrients) {
                "Yes" -> {
                    diagnosebtn1.background = resources.getDrawable(R.drawable.const_bg_border_square_green)
                }
                "Enter What Brand?" -> {
                    diagnosebtn2.background = resources.getDrawable(R.drawable.const_bg_border_square_green)
                }
                "Your Nutrient Cycle" -> {
                    diagnosebtn3.background = resources.getDrawable(R.drawable.const_bg_border_square_green)
                }
                "no" -> {
                    diagnosebtn4.background = resources.getDrawable(R.drawable.const_bg_border_square_green)
                }
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                findNavController().popBackStack()
            }
            R.id.diagnosebtn1 -> {
                gotoNextScreens("Yes")
            }
            R.id.diagnosebtn2 -> {
                gotoNextScreens("Enter What Brand?")
            }
            R.id.diagnosebtn3 -> {
                gotoNextScreens("Your Nutrient Cycle?")
            }
            R.id.diagnosebtn4 -> {
                gotoNextScreens("no")
            }

        }
    }

    private fun gotoNextScreens(value:String) {
        (activity as CIFRootActivity).dignoseRequest!!.meta.areYouUsingAnyNutrients=value
        findNavController().navigate(R.id.diagnose7_to_diagonse8)
    }
}