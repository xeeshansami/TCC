package com.paxees.tcc.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paxees.tcc.R
import com.paxees.tcc.models.DocumentCategoryState
import com.paxees.tcc.models.DrawerItem
import com.paxees.tcc.models.DrawerModel
import com.paxees.tcc.models.ModelDocumentUpload

class SharedCIFViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val types = arrayOf(
        ModelDocumentUpload("ID Document", DocumentCategoryState.Optional, ArrayList()),
        ModelDocumentUpload("Nadra Verification", DocumentCategoryState.Mandatory, ArrayList()),
        ModelDocumentUpload("Proof of Address", DocumentCategoryState.Optional, ArrayList()),
        ModelDocumentUpload("UNSC Validation Print", DocumentCategoryState.Mandatory, ArrayList()),
        ModelDocumentUpload("Customer Signed ACOIF", DocumentCategoryState.Mandatory, ArrayList()),
        ModelDocumentUpload(
            "Customer & Bank Signed T&Cs",
            DocumentCategoryState.Optional,
            ArrayList()
        ),
        ModelDocumentUpload("Signature Specimen", DocumentCategoryState.Optional, ArrayList()),
        ModelDocumentUpload(
            "Proof of Income/ Source of Funds",
            DocumentCategoryState.Mandatory,
            ArrayList()
        ),
        ModelDocumentUpload("Key Fact Sheet", DocumentCategoryState.Optional, ArrayList()),
        ModelDocumentUpload(
            "Signature Specimen (SS) Card",
            DocumentCategoryState.Optional,
            ArrayList()
        )
    )
    val isPhotoTaken = MutableLiveData<Boolean>()
    val showAvatar = MutableLiveData<Boolean>()
    val mList = mutableListOf<DrawerModel>()
    val indicator = MutableLiveData<Boolean>(true)
    var currentFragmentIndex = MutableLiveData<Int>()
    var totalSteps = MutableLiveData<Int>()

    //:TODO initiateDrawer
    fun initiateDrawer(context: Context) {
        mList.clear()
        // All Models
        mList.add(
                DrawerModel(
                        context.getString(R.string.discovry),
                        DrawerItem.ITEM_LINEUP
                )
        )
        mList.add(
                DrawerModel(
                        context.getString(R.string.strains),
                        DrawerItem.ITEM_LINEUP
                )
        )
        mList.add(
                DrawerModel(
                        context.getString(R.string.product),
                        DrawerItem.ITEM_LINEUP
                )
        )
        mList.add(
                DrawerModel(
                        context.getString(R.string.Locations),
                        DrawerItem.ITEM_LINEUP
                )
        )
        mList.add(
                DrawerModel(
                        context.getString(R.string.Videos),
                        DrawerItem.ITEM_LINEUP
                )
        )
        mList.add(
                DrawerModel(
                        context.getString(R.string.Blog),
                        DrawerItem.ITEM_LINEUP
                )
        )
        mList.add(
                DrawerModel(
                        context.getString(R.string.Diagnose),
                        DrawerItem.ITEM_LINEUP
                )
        )
    }
}
