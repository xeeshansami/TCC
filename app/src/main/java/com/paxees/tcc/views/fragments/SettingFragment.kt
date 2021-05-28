package com.paxees.tcc.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.utils.managers.SharedPreferenceManager
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.toolbar.*


class SettingFragment : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        myadressesBtn.setOnClickListener(this)
        addNewPaymentMethodBtn.setOnClickListener(this)
        languageBtn.setOnClickListener(this)
        privacyPolicyBtn.setOnClickListener(this)
        changePwdBtn.setOnClickListener(this)
        helpBtn.setOnClickListener(this)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.settings)
        darkMaode.isChecked = (activity as CIFRootActivity).sharedPreferenceManager.getIntFromSharedPreferences(SharedPreferenceManager.DARK_MODE)==1
        darkMaode.setOnCheckedChangeListener { _, isChecked -> darkMode(isChecked) }
        notifications.setOnCheckedChangeListener { _, isChecked -> notificationFunc(isChecked) }
    }

    private fun notificationFunc(checked: Boolean) {
        if(checked) {
            ToastUtils.showToastWith(activity, "Notifications Enabled")
        }else{
            ToastUtils.showToastWith(activity, "Notifications Disabled")
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
            R.id.privacyPolicyBtn->{
                findNavController().navigate(R.id.navigation_privacy_policy)
            }
            R.id.helpBtn->{
                findNavController().navigate(R.id.setting_to_help)
            }
            R.id.languageBtn->{
                findNavController().navigate(R.id.setting_to_language)
            }
            R.id.changePwdBtn->{
                findNavController().navigate(R.id.setting_to_changepwd)
            }
            R.id.myadressesBtn->{
                findNavController().navigate(R.id.setting_to_myaddresses)
            }
            R.id.addNewPaymentMethodBtn->{
                findNavController().navigate(R.id.setting_to_add_new_payment_method)
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

    fun darkMode(boolean: Boolean){
        if(boolean) {
            (activity as CIFRootActivity).sharedPreferenceManager.storeIntInSharedPreferences(
                    SharedPreferenceManager.DARK_MODE,1
            )
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            (activity as CIFRootActivity).sharedPreferenceManager.storeIntInSharedPreferences(
                    SharedPreferenceManager.DARK_MODE,0
            )
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


}