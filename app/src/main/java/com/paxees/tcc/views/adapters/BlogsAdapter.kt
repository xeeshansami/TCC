package com.paxees.tcc.views.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.paxees.tcc.R
import com.paxees.tcc.network.ResponseHandlers.callbacks.ImageCallBack
import com.paxees.tcc.network.enums.RetrofitEnums
import com.paxees.tcc.network.networkmodels.response.baseResponses.BaseResponse
import com.paxees.tcc.network.networkmodels.response.baseResponses.ImageResponse
import com.paxees.tcc.network.networkmodels.response.models.CategoriesResponse
import com.paxees.tcc.network.store.TCCStore
import com.paxees.tcc.utils.ToastUtils
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class BlogsAdapter(context: Context, data: CategoriesResponse) :
    RecyclerView.Adapter<BlogsAdapter.ViewHolder>() {
    private val row_index = 0
    private val mData: CategoriesResponse
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private val context: Context
    private val branchImage: String? = null

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_views_blogs, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData
        getImage(data[position].meta.categoryImage,holder)
        holder.titleLbl.text = data[position].name
        holder.value1.text = data[position].count.toString() + "K"
        holder.value2.text = data[position].id.toString() + "K"
    }

    private fun getImage(code: String, holder: ViewHolder) {
        // set up the RecyclerView
        TCCStore.instance!!.getImage(RetrofitEnums.URL_HBL, code,object :
            ImageCallBack {
            override fun Success(response: ImageResponse?) {
                Glide.with(context).load(response?.get(0)!!.imageUrl).into(object :
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
    fun getBitmapFromURL(imageUrl: String?): Bitmap? {
        return try {
            val url = URL(imageUrl)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
    private fun ImageOperations(ctx: Context, url: String, saveFilename: String): Drawable? {
        return try {
            val `is` = fetch(url) as InputStream
            Drawable.createFromStream(`is`, saveFilename)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            null
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    @Throws(MalformedURLException::class, IOException::class)
    fun fetch(address: String?): Any {
        val url = URL(address)
        return url.content
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var titleLbl: TextView
        var value1: TextView
        var value2: TextView
        var imageView: ImageView
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            titleLbl = itemView.findViewById(R.id.txtPopluarName)
            value1 = itemView.findViewById(R.id.value1)
            value2 = itemView.findViewById(R.id.value2)
            imageView = itemView.findViewById(R.id.imageid)
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): CategoriesResponse {
        return mData
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
        this.context = context
    }
}