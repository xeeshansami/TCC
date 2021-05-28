package com.paxees.tcc.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.utils.SessionManager
import kotlinx.android.synthetic.main.toolbar_theme.*

class Help : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
               findNavController().popBackStack()
            }
        }
    }
}