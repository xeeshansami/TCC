package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.models.mFilterDashboard
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.views.adapters.VideosAdapter
import kotlinx.android.synthetic.main.fragment_videos.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class Videos : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.Videos)
        videos()
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

    fun videos() {
        val rec: ArrayList<mFilterDashboard> = ArrayList<mFilterDashboard>()
        val txt = ArrayList<String>()
        val img = ArrayList<Int>()
        txt.add("1000+ package for order")
        img.add(R.drawable.image1)
        for (i in txt.indices) {
            val filterDashboard = mFilterDashboard()
            filterDashboard.setTxt(txt[i])
            filterDashboard.img=img[i]
            rec.add(filterDashboard)
        }
        // set up the RecyclerView
        rvForYou.setLayoutManager(GridLayoutManager(activity, 1))
        rvTraining.setLayoutManager(GridLayoutManager(activity, 1))
        rvNewVideos.setLayoutManager(GridLayoutManager(activity, 1))
        var VideosAdapter = VideosAdapter(activity, rec)
        rvForYou.setAdapter(VideosAdapter)
        rvTraining.setAdapter(VideosAdapter)
        rvNewVideos.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }

}