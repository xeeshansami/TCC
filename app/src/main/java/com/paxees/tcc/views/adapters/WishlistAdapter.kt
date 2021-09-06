package com.paxees.tcc.views.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.paxees.tcc.R
import com.paxees.tcc.network.ResponseHandlers.callbacks.WishlistImageCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.GetWishlistResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.WishlistImageResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.ToastUtils

class WishlistAdapter(
    context: Context,
    data: GetWishlistResponse,
    mClickListener: ItemClickListener,
    mClickListener2: ItemClickListener
) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {
    private val row_index = 0
    private val mData: GetWishlistResponse
    private val mInflater: LayoutInflater
    var mClickListener: ItemClickListener
    var mClickListener2: ItemClickListener
    private val context: Context
    private val branchImage: String? = null

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_views_wishlist, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData
        getImage(data[position].productId.toString(),holder)
        holder.priceTv.text = "$" + data[position].price
        holder.addtoCart.setOnClickListener { v -> mClickListener.onItemClick(data, v, position) }
        holder.removeProd.setOnClickListener { v -> mClickListener2.onItemClick(data, v, position) }
    }
    private fun getImage(code: String, holder: ViewHolder) {
        // set up the RecyclerView
        TCCStore.instance!!.getImageForWishList(RetrofitEnums.URL_HBL, code,object :
            WishlistImageCallBack {
            override fun Success(response: WishlistImageResponse?) {
                holder.titleLbl.text = response?.get(0)!!.productTitle + ""
                Glide.with(context).load(response?.get(0)!!.productImage).into(object :
                    CustomTarget<Drawable>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        holder.imageView.background=resource
                    }
                })
            }
            override fun  Failure(baseResponse: BaseResponse) {
                ToastUtils.showToastWith(context, baseResponse.message, "")
            }
        })
    }
    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var titleLbl: TextView
        var priceTv: TextView
        var imageView: ImageView
        var removeProd: ImageView
        var addtoCart: LinearLayout

        init {
            titleLbl = itemView.findViewById(R.id.txtPopluarName)
            imageView = itemView.findViewById(R.id.imgid)
            priceTv = itemView.findViewById(R.id.priceTv)
            removeProd = itemView.findViewById(R.id.removeProd)
            addtoCart = itemView.findViewById(R.id.addtoCart)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): GetWishlistResponse {
        return mData
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(data: GetWishlistResponse?, view: View?, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
        this.context = context
        this.mClickListener = mClickListener
        this.mClickListener2 = mClickListener2
    }
}