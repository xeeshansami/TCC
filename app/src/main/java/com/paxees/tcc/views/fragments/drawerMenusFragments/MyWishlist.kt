package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.models.mFilterDashboard
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.views.adapters.StrainAdapter
import com.paxees.tcc.views.adapters.WishlistAdapter
import kotlinx.android.synthetic.main.fragment_my_wishlist.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header
import java.util.ArrayList

class MyWishlist : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.my_wishlist)
        rvWishlist()
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
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }
    private fun rvWishlist() {
        val rec: ArrayList<mFilterDashboard> = ArrayList<mFilterDashboard>()
        val txt = ArrayList<String>()
        val img = ArrayList<Int>()
        txt.add("Alien Rift")
        txt.add("Astro")
        txt.add("Animal Face")
        txt.add("Blue-Dream2")
        txt.add("Actual Plants")
        img.add(R.drawable.alien)
        img.add(R.drawable.bruce_banner)
        img.add(R.drawable.diagnose10img)
        img.add(R.drawable.blue_dream2)
        img.add(R.drawable.blueberry_og)
        for (i in txt.indices) {
            val filterDashboard = mFilterDashboard()
            filterDashboard.setTxt(txt[i])
            filterDashboard.img = img[i]
            rec.add(filterDashboard)
        }
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvWishList!!.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = WishlistAdapter(activity, rec)
        rvWishList!!.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }
}