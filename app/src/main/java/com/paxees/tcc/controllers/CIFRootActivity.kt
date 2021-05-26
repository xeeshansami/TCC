package com.paxees.tcc.controllers

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.view.View.OnSystemUiVisibilityChangeListener
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.paxees.tcc.R
import com.paxees.tcc.models.DrawerItem
import com.paxees.tcc.models.DrawerModel
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.GlobalClass
import com.paxees.tcc.utils.RecyclerTouchListener
import com.paxees.tcc.viewModels.SharedCIFViewModel
import com.paxees.tcc.views.adapters.DrawerAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_dashboard.drawer_layout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.toolbar.*

class CIFRootActivity : AppCompatActivity(), DrawerLayout.DrawerListener, View.OnClickListener {
    val viewModel: SharedCIFViewModel by viewModels()
    var TAG: String = this.javaClass.simpleName
    private lateinit var drawerAdapter: DrawerAdapter
    private lateinit var appBarConfiguration: AppBarConfiguration
    var listOfPages = mutableListOf<DrawerModel>()

    @JvmField
    public var globalClass: GlobalClass? = null
    var bundle = Bundle()
    var backInt = 0
    private fun hideNavigationBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalClass = GlobalClass.applicationContext!!.applicationContext as GlobalClass
        setContentView(R.layout.activity_dashboard)
        window.decorView.setOnSystemUiVisibilityChangeListener(OnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                hideNavigationBar()
            }
        })
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        /* when {
             intent.extras!!.getInt(Constants.ACTIVITY_KEY) == 1 -> {
                 backInt = intent.extras!!.getInt(Constants.ACTIVITY_KEY)
                 switchFragment(R.id.CIFStep1_4)
             }
             intent.extras!!.getInt(Constants.ACTIVITY_KEY) == 2 -> {
                 backInt = intent.extras!!.getInt(Constants.ACTIVITY_KEY)
                 switchFragment(R.id.openAccountStep1)
             }
             else -> {
                 backInt = intent.extras!!.getInt(Constants.ACTIVITY_KEY)*/
        switchFragment(R.id.navigation_home)
        start()
        recyclerViewSetup()
//            }
    }

    fun start() {
        title = ""
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.cifHostFragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
                setOf(R.id.navigation_home, R.id.navigation_profile,R.id.navigation_strains,R.id.navigation_products), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    toolbar.visibility = View.VISIBLE
                    tvTitle.text = "Discovery"
                }
                R.id.navigation_profile -> {
                    toolbar.visibility = View.GONE
                    tvTitle.text = "Profile"
                }
                R.id.navigation_strains -> {
                    toolbar.visibility = View.GONE
                    tvTitle.text = "Strains"
                }
                R.id.navigation_products -> {
                    toolbar.visibility = View.GONE
                    tvTitle.text = "Product"
                }
            }
        }
        drawerLayout.setDrawerListener(this)
    }

    fun recyclerViewSetup() {
        viewModel.initiateDrawer(this)
        drawerAdapter = DrawerAdapter(viewModel.mList)
        rvDrawer.apply {
            layoutManager = GridLayoutManager(
                    this@CIFRootActivity,
                    context.resources.getInteger(R.integer.drawer_column_count)
            )
            adapter = drawerAdapter
        }

        rvDrawer.addOnItemTouchListener(
                RecyclerTouchListener(
                        this,
                        rvDrawer,
                        object :
                                RecyclerTouchListener.ClickListener {
                            override fun onClick(view: View, position: Int) {
                                changeStartDestination(viewModel.mList[position].title!!)
                            }

                            override fun onLongClick(view: View?, position: Int) {

                            }
                        })
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.cifHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val host: NavHostFragment =
                supportFragmentManager.findFragmentById(R.id.cifHostFragment) as NavHostFragment?
                        ?: return
//            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val fragmen = host.childFragmentManager.fragments.get(0)
        fragmen.onActivityResult(requestCode, resultCode, intent)
        host.onActivityResult(requestCode, resultCode, intent)
    }

    override fun onDrawerStateChanged(newState: Int) {

    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerOpened(drawerView: View) {
//        drawerAdapter.notifyDataSetChanged()
    }

    @SuppressLint("WrongConstant")
    fun changeStartDestination(category: String) {
        /*24 pages of models*/
        when (category) {
            getString(R.string.discovry) -> {
                switchFragment(R.id.navigation_profile)
                drawer_layout.closeDrawer(Gravity.START, true)
            }
            getString(R.string.strains) -> {
                switchFragment(R.id.navigation_strains)
                drawer_layout.closeDrawer(Gravity.START, true)
            }
            getString(R.string.product) -> {
                switchFragment(R.id.navigation_products)
                drawer_layout.closeDrawer(Gravity.START, true)
            }

            getString(R.string.Locations) -> {
                switchFragment(R.id.navigation_map)
                drawer_layout.closeDrawer(Gravity.START, true)
            }
        }
    }

    private fun switchFragment(startDestId: Int) {
//        val fragmentContainer = view?.findViewById<View>(R.id.nav_host)
//        val navController = Navigation.findNavController(fragmentContainer!!)
        val navController = findNavController(R.id.cifHostFragment)
        val inflater = navController.navInflater
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.cifHostFragment)
        if (!navController.popBackStack()) {
            finish()
        } else {
            navController.popBackStack()
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.backBtn->onBackPressed()
        }

    }
}
