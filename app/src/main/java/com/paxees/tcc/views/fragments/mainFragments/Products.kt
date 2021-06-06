package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.networkmodels.response.models.Branch
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.views.adapters.Product2Adapter
import com.paxees.tcc.views.adapters.ProductAdapter
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class Products : Fragment(), View.OnClickListener {
    private var productAdapter: ProductAdapter? = null
    private var product2Adapter: Product2Adapter? = null
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        backBtn.setColorFilter(ContextCompat.getColor(activity as CIFRootActivity, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        header.setTextColor(resources!!.getColor(R.color.white))
        header.text = getText(R.string.product)
        starBtn.visibility=View.VISIBLE
        productsRecycler()
        products2Recycler()
    }
    fun productsRecycler() {
        val rec: ArrayList<Branch> = ArrayList<Branch>()
        val txt = ArrayList<String>()
        txt.add("Medium")
        txt.add("Photo Period")
        txt.add("7-8 Weeks")
        txt.add("End of Sep/Oct")
        txt.add("Medium")
        txt.add("Short")
        for (i in txt.indices) {
            val filterDashboard = Branch()
            filterDashboard.branchName=txt[i].toString()
            rec.add(filterDashboard)
        }
        val horizontalLayoutManagaer = GridLayoutManager(activity, 1)
        rvProducts1.layoutManager = horizontalLayoutManagaer
        productAdapter = ProductAdapter(activity, rec)
        rvProducts1.setAdapter(productAdapter)
        productAdapter!!.notifyDataSetChanged()
    }
    fun products2Recycler() {
        val rec: ArrayList<Branch> = ArrayList<Branch>()
        val txt = ArrayList<String>()
        txt.add("SATVIA-DOM 60%")
        txt.add("CINDRELLA 99 ROMULAN CHEES")
        txt.add("24%")
        txt.add("20%")
        txt.add("CHEES\nFRUITY\nSWEET\nPUNGENT")
        txt.add("RELAXED\nMOTIVATED\nUPLIFTING\nHAPPY\nSOCIABLE")
        for (i in txt.indices) {
            val filterDashboard = Branch()
            filterDashboard.branchName=txt[i].toString()
            rec.add(filterDashboard)
        }
        val horizontalLayoutManagaer = GridLayoutManager(activity, 1)
        rvProducts2.layoutManager = horizontalLayoutManagaer
        product2Adapter = Product2Adapter(activity, rec)
        rvProducts2.setAdapter(product2Adapter)
        product2Adapter!!.notifyDataSetChanged()
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
}