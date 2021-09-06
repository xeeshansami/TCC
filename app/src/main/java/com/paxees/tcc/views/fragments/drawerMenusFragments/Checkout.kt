package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.paxees.tcc.R
import com.paxees.tcc.controllers.Dashboard

class Checkout : Fragment(), View.OnClickListener {
    var bt_login: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View) {
        bt_login = view.findViewById(R.id.bt_login)
        bt_login!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_login -> login()
        }
    }

    private fun login() {
        (activity as Dashboard?)!!.globalClass.showDialog(activity)
        val handler = Handler()
        handler.postDelayed({
            (activity as Dashboard?)!!.globalClass.hideLoader()
            //                NavHostFragment.findNavController(Checkout.this).navigate(R.id.login_to_dashboard);
        }, 1500)
    }
}