package com.paxees.tcc.views.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.paxees.tcc.utils.ToastUtils
import androidx.navigation.fragment.NavHostFragment
import com.paxees.tcc.R
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class SplashScreen : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var PERMISSION_ALL = 1
    var PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE) // List of permissions required

    fun askPermission() {
        for (permission in PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission((activity as launcher), permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS, PERMISSION_ALL)
                return
            } else {
                init()
            }
        }
    }
    fun init(){
        skipBtn.setOnClickListener(this)
        btnGetStarted.setOnClickListener(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //Do your work.
                    init()
                } else {
                    ToastUtils.showToastWith(activity, "Until you grant the permission, we cannot proceed further", "")
                }
                return
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager = SessionManager(activity)
        askPermission()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.skipBtn->{
            NavHostFragment.findNavController(this@SplashScreen).navigate(R.id.splash_to_login)
            }
            R.id.btnGetStarted->{
            NavHostFragment.findNavController(this@SplashScreen).navigate(R.id.splash_to_login)
            }
        }

    }
}