package com.paxees.tcc.views.fragments.settingsFragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.models.Plants
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainRequestFormCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.StrainRequestTokenCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.StrainRequestFromRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainRequestFormResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.StrainRequestTokenResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_strain_form.*
import kotlinx.android.synthetic.main.toolbar_theme.*
import java.util.*


class StrainForm : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    var plantsInterested = ""
    var seedsInterested = ""
    var findInterested = ""
    var otherPlanets = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_strain_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        onBackPressed()
    }

    fun onBackPressed(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                switchFragment(R.id.navigation_home)
            }
        })
    }

    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        bt_submit.setOnClickListener(this)
        setPlants()
        setSeeds()
        setFinds()
    }

    private fun createStrainRequest(accessToken: String) {
        var FullName = etFullName.text.toString().trim()
        var Email = etEmail.text.toString().trim()
        var Phone = etPhone.text.toString().trim()
        var Address1 = etAddress1.text.toString().trim()
        var Address2 = etAddress2.text.toString().trim()
        var City = etCity.text.toString().trim()
        var StateResign = etStateResign.text.toString().trim()
        var PostalCode = etPostalCode.text.toString().trim()
        var Countrye = etCountrye.text.toString().trim()
        var request = StrainRequestFromRequest()
        var data=com.paxees.tcc.network.networkmodels.request.Data()
        if (FullName.contains(' ')) {
            val tokens: List<String> = FullName.split(' ')
            data.firstName = tokens[0].toString()
            data.lastName = tokens[1].toString()
        } else {
            data.firstName = FullName
            data.lastName = FullName
        }
        data.email = Email
        data.phone = Phone
        data.city = City
        data.state = StateResign
        data.street = PostalCode
        data.country = Countrye
        if (nutreintsCB.isChecked) {
            otherPlanets = "Nutrients;"
        }
        if (growTentsCB.isChecked) {
            otherPlanets += "Grow Tents;"
        }
        if (growLightsCB.isChecked) {
            otherPlanets += "Grow Lights;"
        }
        if (growMentorshipCB.isChecked) {
            otherPlanets += "Grow Mentorship;"
        }
        data.iMInterestedInOtherProducts = otherPlanets
        data.iMInterestedInCannabisSeeds = seedsInterested
        data.iMInterestedInCannabisPlants = plantsInterested
        data.howDidYouFindUs = findInterested
        request.data.add(data)
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.createStrainRequestForm(RetrofitEnums.URL_ZOHO,
            "Bearer $accessToken", request,
            object : StrainRequestFormCallBack {
                override fun Success(response: StrainRequestFormResponse) {
                    ToastUtils.showToastWith(activity, response.data[0].message)
                    switchFragment(R.id.navigation_home)
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    private fun validation(): Boolean {
        var FullName = etFullName.text.toString().trim()
        var Email = etEmail.text.toString().trim()
        var Phone = etPhone.text.toString().trim()
        var Address1 = etAddress1.text.toString().trim()
        var Address2 = etAddress2.text.toString().trim()
        var City = etCity.text.toString().trim()
        var StateResign = etStateResign.text.toString().trim()
        var PostalCode = etPostalCode.text.toString().trim()
        var Countrye = etCountrye.text.toString().trim()
        return if (TextUtils.isEmpty(FullName)) {
            etFullName!!.error = "FullName should not be empty"
            etFullName!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(Email)) {
            etEmail!!.error = "Email should not be empty"
            etEmail!!.requestFocus()
            false
        } else if ((!Email.isValidEmail())) {
            etEmail!!.error = "Email should be valid"
            etEmail!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(Phone)) {
            etPhone!!.error = "Phone number should not be empty"
            etPhone!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(Address1)) {
            etAddress1!!.error = "Address should not be empty"
            etAddress1!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(City)) {
            etCity!!.error = "City should not be empty"
            etCity!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(StateResign)) {
            etStateResign!!.error = "State should not be empty"
            etStateResign!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(PostalCode)) {
            etPostalCode!!.error = "Postal code should not be empty"
            etPostalCode!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(Countrye)) {
            etCountrye!!.error = "Country should not be empty"
            etCountrye!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(plantsInterested)) {
            ToastUtils.showToastWith(activity, "Please select any plants interested", "")
            false
        } else if (TextUtils.isEmpty(seedsInterested)) {
            ToastUtils.showToastWith(activity, "Please select any seeds interested", "")
            false
        } else if (TextUtils.isEmpty(findInterested)) {
            ToastUtils.showToastWith(
                activity,
                "Please select any reference of how did you find us?",
                ""
            )
            false
        } else if (!nutreintsCB.isChecked && !growTentsCB.isChecked && !growLightsCB.isChecked && !growMentorshipCB.isChecked) {
            ToastUtils.showToastWith(
                activity,
                "Please checked the interest in any other plants",
                ""
            )
            false
        } else {
            true
        }
    }

    fun String.matches(regex: String): Boolean {
        return regex == "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"
    }

    private fun getAccessToken() {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getTokenForStrainRequest(RetrofitEnums.URL_HBL,
            object : StrainRequestTokenCallBack {
                override fun Success(response: StrainRequestTokenResponse) {
                    createStrainRequest(response.accessToken)
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
            R.id.bt_submit -> {
                if (validation()) {
                    getAccessToken()
                }
            }
        }
    }

    private fun switchFragment(startDestId: Int) {
        val navController = findNavController()
        val graph = navController.graph
        graph.startDestination = startDestId
        navController.graph = graph
    }

    fun setPlants() {
        val rec: ArrayList<Plants> = ArrayList<Plants>()
        val txt = ArrayList<String>()
        txt.add("2-5")
        txt.add("6-11")
        txt.add("12-23")
        txt.add("24-49")
        txt.add("50-99")
        txt.add("100+")
        for (i in txt.indices) {
            val filterDashboard = Plants()
            filterDashboard.plantValue = txt[i].toString()
            rec.add(filterDashboard)
        }
        val horizontalLayoutManagaer = GridLayoutManager(activity, 5)
        rvPlantsInterested!!.layoutManager = horizontalLayoutManagaer
        var adapter = RecyclerViewAdapter(requireActivity(), rec, 0, "")
        adapter.setClickListener(object : RecyclerViewAdapter.ItemClickListener {
            override fun onItemClick(view: View?, position: Int, value: String) {
                plantsInterested = value
            }
        })
        rvPlantsInterested.setAdapter(adapter)
        adapter!!.notifyDataSetChanged()
    }

    fun setSeeds() {
        val rec: ArrayList<Plants> = ArrayList<Plants>()
        val txt = ArrayList<String>()
        txt.add("10")
        txt.add("20")
        txt.add("30")
        txt.add("40")
        txt.add("50+")
        for (i in txt.indices) {
            val filterDashboard = Plants()
            filterDashboard.plantValue = txt[i].toString()
            rec.add(filterDashboard)
        }
        val horizontalLayoutManagaer = GridLayoutManager(activity, 5)
        rvSeedsInterested!!.layoutManager = horizontalLayoutManagaer
        var adapter = RecyclerViewAdapter(requireActivity(), rec, 0, "")
        adapter.setClickListener(object : RecyclerViewAdapter.ItemClickListener {
            override fun onItemClick(view: View?, position: Int, value: String) {
                seedsInterested = value
            }
        })
        rvSeedsInterested.setAdapter(adapter)
        adapter!!.notifyDataSetChanged()
    }

    fun setFinds() {
        val rec: ArrayList<Plants> = ArrayList<Plants>()
        val txt = ArrayList<String>()
        txt.add("Google")
        txt.add("Friends/Family")
        txt.add("Facebook")
        txt.add("Other")
        for (i in txt.indices) {
            val filterDashboard = Plants()
            filterDashboard.plantValue = txt[i].toString()
            rec.add(filterDashboard)
        }
        val horizontalLayoutManagaer =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvfindInterested!!.layoutManager = horizontalLayoutManagaer
        var adapter = RecyclerViewAdapter(requireActivity(), rec, 1, "")
        adapter.setClickListener(object : RecyclerViewAdapter.ItemClickListener {
            override fun onItemClick(view: View?, position: Int, value: String) {
                findInterested = value
            }
        })
        rvfindInterested.setAdapter(adapter)
        adapter!!.notifyDataSetChanged()
    }
    fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}