package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.controllers.Dashboard
import com.paxees.tcc.models.mFilterDashboard
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.views.adapters.PopularAdapter
import com.paxees.tcc.views.adapters.StrainAdapter
import kotlinx.android.synthetic.main.fragment_strains.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.ArrayList

class Strains : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_strains, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.strains)
        rvStrainsFunc()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
        }
    }

    private fun switchFragment(startDestId: Int) {
        val navController = findNavController()
        val inflater = navController.navInflater
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }
    private fun rvStrainsFunc() {
        val rec: ArrayList<mFilterDashboard> = ArrayList<mFilterDashboard>()
        val txt = ArrayList<String>()
        val lbl = ArrayList<String>()
        val img = ArrayList<Int>()
        txt.add("Alien Rift")
        txt.add("Astro")
        txt.add("Animal Face")
        txt.add("Blue-Dream2")
        txt.add("Actual Plants")
        txt.add("Blue Berry")
        txt.add("Sky Walker")
        lbl.add("Hybrid")
        lbl.add("Hybrid")
        lbl.add("Indica")
        lbl.add("Hybrid")
        lbl.add("Indica")
        lbl.add("Hybrid")
        lbl.add("Indica")
        img.add(R.drawable.alien)
        img.add(R.drawable.bruce_banner)
        img.add(R.drawable.diagnose10img)
        img.add(R.drawable.blue_dream2)
        img.add(R.drawable.blueberry_og)
        img.add(R.drawable.diagnose9image)
        img.add(R.drawable.amnesia_haze)
        for (i in txt.indices) {
            val filterDashboard = mFilterDashboard()
            filterDashboard.setTxt(txt[i])
            filterDashboard.img = img[i]
            filterDashboard.value = lbl[i]
            rec.add(filterDashboard)
        }
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvStrains.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = StrainAdapter(activity, rec)
        rvStrains.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }
}