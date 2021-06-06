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
import com.paxees.tcc.views.adapters.CartAdapter
import com.paxees.tcc.views.adapters.PlantTypeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.rvPlantsType
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.header
import java.util.ArrayList


class MyOrders : Fragment(), View.OnClickListener, CartAdapter.onItemMinus, CartAdapter.onItemPlus {
    var sessionManager: SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_order, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        header.text = getText(R.string.myCart)
        rvCarts()
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

    private fun rvCarts() {
        val rec: ArrayList<mFilterDashboard> = ArrayList<mFilterDashboard>()
        val txt = ArrayList<String>()
        val txt2 = ArrayList<String>()
        val img = ArrayList<Int>()
        txt.add("Indica")
        txt.add("Hybird")
        txt.add("Plants")
        txt.add("Blue")
        txt.add("Sativable")

        txt2.add("$1")
        txt2.add("$3")
        txt2.add("$1")
        txt2.add("$4")
        txt2.add("$2")
        img.add(R.drawable.blackberry)
        img.add(R.drawable.bling)
        img.add(R.drawable.blue_dream2)
        img.add(R.drawable.blueberry_og)
        img.add(R.drawable.bruce_banner)
        for (i in txt.indices) {
            val filterDashboard = mFilterDashboard()
            filterDashboard.setTxt(txt[i])
            filterDashboard.img = img[i]
            filterDashboard.value = txt2[i]
            rec.add(filterDashboard)
        }
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//       val horizontalLayoutManagaer = GridLayoutManager(activity, 1)
        rvCarts.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = CartAdapter(activity, rec,this,this)
        rvCarts.adapter = VideosAdapter
        VideosAdapter.notifyDataSetChanged()
    }

    override fun onItemMinus(view: View?, position: Int) {

    }

    override fun onItemPlus(view: View?, position: Int) {

    }
}