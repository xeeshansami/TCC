package com.paxees.tcc.views.fragments

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.paxees.tcc.R
import com.paxees.tcc.controllers.launcher
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import java.util.*

class SplashScreen : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    var PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE) // List of permissions required
    private fun checkForPermissions() {
        Permissions.check(activity /*context*/, PERMISSIONS, null /*rationale*/, null /*options*/, object : PermissionHandler() {
            override fun onGranted() {
                // do your task.
                init()
            }

            override fun onDenied(context: Context, deniedPermissions: ArrayList<String>) {
                ToastUtils.showToastWith(activity, "Until you grant the permission, we cannot proceed further", "")

            }
        })
    }
    fun init(){
        skipBtn.setOnClickListener(this)
        btnGetStarted.setOnClickListener(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager = SessionManager(activity)
        checkForPermissions()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.skipBtn -> {
                NavHostFragment.findNavController(this@SplashScreen).navigate(R.id.splash_to_login)
            }
            R.id.btnGetStarted -> {
                NavHostFragment.findNavController(this@SplashScreen).navigate(R.id.splash_to_login)
            }
        }

    }
}