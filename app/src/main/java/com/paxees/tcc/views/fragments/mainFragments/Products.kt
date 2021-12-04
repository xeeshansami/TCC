package com.paxees.tcc.views.fragments.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.paxees.tcc.R
import com.paxees.tcc.controllers.CIFRootActivity
import com.paxees.tcc.network.ResponseHandlers.callbacks.AddToCartCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.AddToWishlistCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.ProductCallBack
import com.paxees.tcc.network.ResponseHandlers.callbacks.WishlistShareKeyByUserCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.request.AddToCartRequest
import com.paxees.tcc.network.networkmodels.request.AddToWishlistRequest
import com.paxees.tcc.network.networkmodels.response.baseResponses.*
import com.paxees.tcc.network.networkmodels.response.models.Branch
import com.paxees.tcc.network.networkmodels.response.models.MetaDataX
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.Constants
import com.paxees.tcc.utils.SessionManager
import com.paxees.tcc.utils.ToastUtils
import com.paxees.tcc.views.adapters.GrowthtAdapter
import com.paxees.tcc.views.adapters.Product2Adapter
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class Products : Fragment(), View.OnClickListener {
    private var growthtAdapter: GrowthtAdapter? = null
    private var growthtAdapter2: GrowthtAdapter? = null
    private var product2Adapter: Product2Adapter? = null
    private var productItems: ProductResponse? = null
    var sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onBackPressed()
        try {
            if (requireArguments() != null && requireArguments().containsKey(Constants.PRODUCT_ID)) {
                requireArguments().getString(Constants.PRODUCT_ID)?.let { getProduct(it) }
            }
        } catch (e: IllegalStateException) {
        }
    }

    private fun getProduct(key: String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        // set up the RecyclerView
        TCCStore.instance!!.getProduct(RetrofitEnums.URL_HBL, key, object :
            ProductCallBack {
            override fun Success(response: ProductResponse?) {
                response.let {
                    productItems = it
                    growthAdapter(it!!.metaData)
                }
                setProductItems(response)

            }

            override fun Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(activity, baseResponse.message, "")
                (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
            }
        })
    }

    private fun setProductItems(response: ProductResponse?) {
        date.text = getDate() + "\nDay"
        humidity.text = 65.toString() + "%\nHumidity"
        weather.text = 73.toString() + "%\nWeather"
        productName.text = response!!.name
        slug.text = response!!.slug
        if (response!!.sale_price.isNullOrEmpty()) {
            salePrice.text = "$10"
        } else {
            salePrice.text = "$" + response!!.sale_price
        }
        if (response!!.price.isNullOrEmpty()) {
            price.text = "$20"
        } else {
            price.text = "$" + response!!.price
        }
        productDesc.text = response!!.short_description
        Glide.with(requireContext()).load(response!!.yoastHeadJson.ogImage[0].url)
            .placeholder(R.drawable.logo).into(product_Image)
        (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
    }

    fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    switchFragment(R.id.navigation_home)
                }
            })
    }

    fun init() {
        sessionManager = SessionManager(activity)
        orderItems.setOnClickListener(this)
        starBtn.setOnClickListener(this)
        backBtn.setOnClickListener(this)
        backBtn.setColorFilter(
            ContextCompat.getColor(activity as CIFRootActivity, R.color.white),
            android.graphics.PorterDuff.Mode.SRC_IN
        );
        header.setTextColor(resources!!.getColor(R.color.white))
        header.text = getText(R.string.product)
        starBtn.visibility = View.VISIBLE
    }


    fun growthAdapter(metax: ArrayList<MetaDataX>) {
        var metaxx = ArrayList<MetaDataX>()
        var metaxxx = ArrayList<MetaDataX>()
        for (meta in metax) {
            if (meta.key.equals("grow_difficulty", ignoreCase = true)) {
                metaxx.add(meta)
            } else if (meta.key.equals("flowering_type", ignoreCase = true)) {
                metaxx.add(meta)
            } else if (meta.key.equals("flowering_time", ignoreCase = true)) {
                metaxx.add(meta)
            } else if (meta.key.equals("harvest_time", ignoreCase = true)) {
                metaxx.add(meta)
            } else if (meta.key.equals("yield", ignoreCase = true)) {
                metaxx.add(meta)
            } else if (meta.key.equals("genetics", ignoreCase = true)) {
                metaxxx.add(meta)
            } else if (meta.key.equals("parents", ignoreCase = true)) {
                metaxxx.add(meta)
            } else if (meta.key.equals("thc", ignoreCase = true)) {
                metaxxx.add(meta)
            } else if (meta.key.equals("cbd", ignoreCase = true)) {
                metaxxx.add(meta)
            } else if (meta.key.equals("smell_flavour", ignoreCase = true)) {
                metaxxx.add(meta)
            } else if (meta.key.equals("effect", ignoreCase = true)) {
                metaxxx.add(meta)
            }
        }
        val horizontalLayoutManagaer = GridLayoutManager(activity, 1)
        rvProducts1.layoutManager = horizontalLayoutManagaer
        growthtAdapter = GrowthtAdapter(activity, metaxx)
        growthtAdapter2 = GrowthtAdapter(activity, metaxxx)
        rvProducts1.adapter = growthtAdapter
        rvProducts2.adapter = growthtAdapter2
        growthtAdapter!!.notifyDataSetChanged()
        growthtAdapter2!!.notifyDataSetChanged()
    }

    private fun getShareKey(userID: String, prodId: String) {
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.getWishlistShareKeyByUser(
            RetrofitEnums.URL_HBL,
            userID.toInt(),
            object :
                WishlistShareKeyByUserCallBack {
                override fun Success(response: WishlistShareKeyByUserResponse) {
                    addToWishlist(response[0].shareKey, prodId)
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }

    private fun addToWishlist(sharekey: String, prodId: String) {
        var request = AddToWishlistRequest()
        request.productId = prodId.toInt()
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.AddToWishlist(RetrofitEnums.URL_HBL, sharekey, request, object :
            AddToWishlistCallBack {
            override fun Success(response: AddToWishlistResponse) {
                ToastUtils.showToastWith(
                    activity,
                    "Product has been added to wishlist successfully"
                )
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

            R.id.starBtn -> {
                getShareKey(
                    (activity as CIFRootActivity).sharedPreferenceManager.customerDetails[0].id.toString(),
                    productItems!!.id.toString()
                )
            }
            R.id.orderItems -> {
                addToCart(productItems!!.id.toString())
            }
        }
    }

    fun getDate(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd", Locale.getDefault())
        return df.format(c)
    }

    private fun addToCart(prodId: String) {
        var request = AddToCartRequest()
        request.productId = prodId
        request.quantity = 1
        (activity as CIFRootActivity?)!!.globalClass!!.showDialog(activity)
        TCCStore.instance!!.addToCart(
            RetrofitEnums.URL_HBL,
            (activity as CIFRootActivity).token,
            request,
            object :
                AddToCartCallBack {
                override fun Success(response: AddtoCartResponse) {
                    ToastUtils.showToastWith(activity, "Product has been added successfully")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }

                override fun Failure(baseResponse: BaseResponse) {
                    ToastUtils.showToastWith(activity, baseResponse.message, "")
                    (activity as CIFRootActivity?)!!.globalClass!!.hideLoader()
                }
            })
    }


    private fun switchFragment(startDestId: Int) {
//        val fragmentContainer = view?.findViewById<View>(R.id.nav_host)
//        val navController = Navigation.findNavController(fragmentContainer!!)
        if (findNavController().navigateUp()) {
            findNavController().navigateUp()
        } else {
            val navController = findNavController()
            val inflater = navController.navInflater
            val graph = navController.graph
            graph.startDestination = startDestId
            navController.graph = graph
        }
    }
}