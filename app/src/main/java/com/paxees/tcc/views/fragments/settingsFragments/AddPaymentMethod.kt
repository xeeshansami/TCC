package com.paxees.tcc.views.fragments.settingsFragments

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
import com.paxees.tcc.views.adapters.PaymentMethodAdapter
import com.paxees.tcc.views.adapters.StrainAdapter
import kotlinx.android.synthetic.main.fragment_add_payment_method.*
import kotlinx.android.synthetic.main.fragment_strains.*
import kotlinx.android.synthetic.main.fragment_strains.rvStrains
import kotlinx.android.synthetic.main.toolbar_theme.backBtn
import java.util.ArrayList

class AddPaymentMethod : Fragment(), View.OnClickListener {
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_payment_method, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }
    fun init(view: View?) {
        sessionManager = SessionManager(activity)
        backBtn.setOnClickListener(this)
        rvPaymentFunc()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn -> {
               findNavController().popBackStack()
            }
        }
    }

    private fun rvPaymentFunc() {
        val rec: ArrayList<mFilterDashboard> = ArrayList<mFilterDashboard>()
        val txt = ArrayList<String>()
        val lbl = ArrayList<String>()
        val img = ArrayList<Int>()
        txt.add("12345678946513654897")
        txt.add("12345678946513654897")
        txt.add("12345678946513654897")
        lbl.add("Master Card - 04/22")
        lbl.add("Visa Card - 01/21")
        lbl.add("Master Card - 04/25")
        img.add(R.mipmap.ic_done)
        img.add(R.drawable.circle_img)
        img.add(R.drawable.circle_img)
        for (i in txt.indices) {
            val filterDashboard = mFilterDashboard()
            filterDashboard.setTxt(txt[i])
            filterDashboard.img = img[i]
            filterDashboard.value = lbl[i]
            rec.add(filterDashboard)
        }
        // set up the RecyclerView
        val horizontalLayoutManagaer = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvPayments.layoutManager = horizontalLayoutManagaer
        var VideosAdapter = PaymentMethodAdapter(activity, rec)
        rvPayments.setAdapter(VideosAdapter)
        VideosAdapter.notifyDataSetChanged()
    }
}