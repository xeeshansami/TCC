package com.paxees.tcc.views.fragments.drawerMenusFragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.UpdateProfileCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.UpdateProfileRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.CustomerDetailsItems
import com.paxees.tcc.network.networkmodels.response.baseResponses.CustomerDetailsResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.UpdateProfileResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.FileNotFoundException
import java.io.InputStream


class Profile : Fragment(), View.OnClickListener {
    val RESULT_LOAD_IMG = 1
    val CAMERA_REQUEST = 2
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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        checkBackground()
    }
    @SuppressLint("WrongConstant")
    private fun checkBackground() {
        if(AppCompatDelegate.getDefaultNightMode()==2){
            mainLayout.background=resources.getDrawable(R.color.colorPrimary)
        }else{
            mainLayout.background=resources.getDrawable(R.drawable.loginbg)
        }
    }
    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        updateProfileBtn.setOnClickListener(this)
        chang_photo.setOnClickListener(this)
        header.text = getText(R.string.profile)
        updateData()

    }

    private fun updateData() {
        (activity as CIFRootActivity).globalClass!!.setEnabled(emailEt,false,0.5f)
        emailEt!!.setText((activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].email)
        fullName!!.setText((activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].firstName )
        etNumber!!.setText((activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].billing.phone)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
                switchFragment(R.id.navigation_home)
            }
            R.id.updateProfileBtn -> if (validation()) {
                profileUpdate()
            }
            R.id.chang_photo -> {
                changePhoto()
            }
        }
    }
    private fun validation(): Boolean {
        val fullname = fullName!!.text.toString().trim { it <= ' ' }
        val number = etNumber!!.text.toString().trim { it <= ' ' }
        return if (TextUtils.isEmpty(fullname)) {
            fullName!!.error = "Fullname should not be empty"
            fullName!!.requestFocus()
            false
        } else if (TextUtils.isEmpty(number)) {
            etNumber!!.error = "Phone number should not be empty"
            etNumber!!.requestFocus()
            false
        } else {
            true
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

    private fun profileUpdate() {
        val fullname = fullName!!.text.toString().trim { it <= ' ' }
        val number = etNumber!!.text.toString().trim { it <= ' ' }
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        var userId=(activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id
        var email=(activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].email
        var request=UpdateProfileRequest()
        request.billing=(activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].billing
        request.billing.firstName=fullname
        request.billing.phone=number
        request.email=email
        TCCStore.getInstance().profileUpdate(RetrofitEnums.URL_HBL,userId.toString(), request,object :
            UpdateProfileCallBack {
            override fun Success(response: UpdateProfileResponse) {
                ToastUtils.showToastWith(activity,"Profile has been updated")
//                (activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0]=response as CustomerDetailsItems
                switchFragment(R.id.navigation_home)
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }
    private fun changePhoto() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
    }
    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            try {
                val imageUri: Uri = data!!.data!!
                val imageStream: InputStream = (activity as CIFRootActivity).contentResolver.openInputStream(imageUri!!)!!
                val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
                ivProfile.setImageBitmap(selectedImage)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(activity, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }

}