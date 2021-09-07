package com.paxees.tcc.views.fragments.mainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.*
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.*
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.NightTimeUsageAdapter
import com.paxees.tcc.views.adapters.PlantTypeAdapter
import com.paxees.tcc.views.adapters.PopularAdapter
import com.paxees.tcc.views.adapters.ProductSearchAdapter
import kotlinx.android.synthetic.main.drawer_bottom_layout.*
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun getCarts() {
        var accessToken =   (activity as CIFRootActivity?)!!.sharedPreferenceManager.loginData.token
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getCarts(RetrofitEnums.URL_HBL, accessToken, object :
            GetCartsCallBack {
            override fun Success(response: GetAddToCartResponse) {
                orderItems.text = response.size.toString() + " items"
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }
    fun init(view: View?) {
        searchFilter!!.setOnClickListener(this)
        poplarPlants()
        rvPlantsType()
        searchProducts()
    }

    private fun searchProducts() {
        searchtEt.setOnEditorActionListener(
            object : TextView.OnEditorActionListener {
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event != null
                        && event.getAction() === KeyEvent.ACTION_DOWN && event.getKeyCode() === KeyEvent.KEYCODE_ENTER) {
                        if (event == null || !event.isShiftPressed()) {
                            // the user is done typing.
                            filterPlants(searchtEt.text.trim().toString())
                            return true // consume.
                        }
                    }
                    return false // pass on to other listeners.
                }
            }
        )


        searchtEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s!!.isEmpty()) {
                    poplarPlants()
                }
            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        checkBackground()
        getCarts()
    }

    @SuppressLint("WrongConstant")
    private fun checkBackground() {
        if (AppCompatDelegate.getDefaultNightMode() == 2) {
            searchBg!!.background = resources.getDrawable(R.drawable.bg_bottom_line)
            searchtEt!!.background = resources.getDrawable(R.drawable.bg_border_filled)
            searchBg!!.setPadding(10, 0, 10, 0)
        } else {
            searchBg!!.background = resources.getDrawable(android.R.color.transparent)
            searchtEt!!.setPadding(10, 10, 10, 10)
            searchtEt!!.background = resources.getDrawable(R.drawable.bg_border_filled)
        }
    }

    private fun poplarPlants() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        // set up the RecyclerView
        val horizontalLayoutManagaer =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManagaer2 =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.layoutManager = horizontalLayoutManagaer
        rvNightUseage.layoutManager = horizontalLayoutManagaer2
        TCCStore.instance!!.getPopularByThisWeek(RetrofitEnums.URL_HBL, object :
            PopularByThisWeekCallBack {
            override fun PopularByThisWeekSuccess(response: PopularByThisWeekResponse) {
                setCategories(response)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun PopularByThisWeekFailure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setCategories(response: PopularByThisWeekResponse) {
        var VideosAdapter = PopularAdapter(activity, response)
        rvPopular.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }

    private fun rvPlantsType() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        // set up the RecyclerView
        val horizontalLayoutManagaer =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPlantsType.layoutManager = horizontalLayoutManagaer
        TCCStore.instance!!.getPlantsByType(RetrofitEnums.URL_HBL, object :
            PlantsByTypeCallBack {
            override fun Success(response: PlantsByTypeResponse) {
                setPlantType(response)
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setPlantType(response: PlantsByTypeResponse) {
        var VideosAdapter = PlantTypeAdapter(activity, response)
        rvPlantsType.adapter = VideosAdapter
        VideosAdapter.notifyDataSetChanged()
        // set up the RecyclerView
        val horizontalLayoutManagaer =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvNightUseage.layoutManager = horizontalLayoutManagaer
        TCCStore.instance!!.getNightTimeUsage(RetrofitEnums.URL_HBL, object :
            NightTimeUsageCallBack {
            override fun Success(response: NightTimeUsuageResponse) {
                setNightTimeUsage(response)
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setNightTimeUsage(response: NightTimeUsuageResponse) {
        try {
            var VideosAdapter2 = NightTimeUsageAdapter(activity, response)
            rvNightUseage.setAdapter(VideosAdapter2)
            VideosAdapter2.notifyDataSetChanged()
            (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
        }catch (e:Exception){
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.searchFilter -> {
                var filter = searchtEt!!.text.toString().trim()
                filterPlants(filter)
            }
        }
    }

    private fun filterPlants(filter: String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getProductSearch(RetrofitEnums.URL_HBL, filter, object :
            ProductSearchCallBack {
            override fun Success(response: ProductSearchResponse) {
                setProductSearch(response)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setProductSearch(response: ProductSearchResponse) {
        var VideosAdapter = ProductSearchAdapter(activity, response)
        rvPopular.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }

}