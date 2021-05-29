package com.paxees.tcc.controllers

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.facebook.FacebookSdk
import com.paxees.tcc.R
import com.paxees.tcc.network.networkmodels.response.models.Brand
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.GlobalClass
import com.paxees.tcc.utils.managers.SharedPreferenceManager

class launcher : AppCompatActivity() {
    @JvmField
    var globalClass: GlobalClass? = null
    @JvmField
    var sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        globalClass = GlobalClass.applicationContext!!.applicationContext as GlobalClass
        sharedPreferenceManager.getInstance(globalClass)
        FacebookSdk.sdkInitialize(applicationContext)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        globalClass = GlobalClass()
        if (intent.hasExtra(Constants.LOGGEDIN)) {
            switchFragment(R.id.login, intent.extras!!.getParcelable(Constants.BRAND))
        }
    }

    private fun switchFragment(startDestId: Int, brand: Brand?) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.BRAND, brand)
        val navController = Navigation.findNavController(this@launcher, R.id.nav_host_fragment)
        navController.navigateUp()
        navController.navigate(startDestId, bundle)
    }
}