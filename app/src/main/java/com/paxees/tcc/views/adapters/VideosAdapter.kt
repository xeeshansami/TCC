package com.paxees.tcc.views.adapters

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore.Images.Thumbnails.MINI_KIND
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.paxees.tcc.R
import com.paxees.tcc.models.mFilterDashboard
import com.paxees.tcc.network.networkmodels.response.baseResponses.VideosListResponse


class VideosAdapter(context: Context, data: VideosListResponse) :
    RecyclerView.Adapter<VideosAdapter.ViewHolder>() {
    private val row_index = 0
    var mData: VideosListResponse
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null
    private val context: Context

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_views_result, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData
        val filename: String =   data[position].meta.videoUrl.substring(  data[position].meta.videoUrl.lastIndexOf("/") + 1)
        val requestOptions = RequestOptions()
        requestOptions.isMemoryCacheable
        Glide.with(context).setDefaultRequestOptions(requestOptions).load("https://img.youtube.com/vi/$filename/$position.jpg").placeholder(R.drawable.logo).into(holder.imgid)
        holder.txt.text = data[position].title.rendered
        holder.playVideo.setOnClickListener(View.OnClickListener {
            watchYoutubeVideo(context,filename)
        })
    }
    private fun watchYoutubeVideo(context: Context, id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$id")
        )
        try {
            context.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(webIntent)
        }
    }

    fun getThumblineImage(videoPath: String?): Bitmap? {
        return ThumbnailUtils.createVideoThumbnail(videoPath!!, MINI_KIND)
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }


    fun filterList(filteredList: VideosListResponse) {
        mData = filteredList
        notifyDataSetChanged()
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgid: ImageView = itemView.findViewById(R.id.imgid)
        var playVideo: ImageView = itemView.findViewById(R.id.playVideo)
        var txt: TextView = itemView.findViewById(R.id.tvId)

    }

    // convenience method for getting data at click position
    fun getItem(id: Int): VideosListResponse {
        return mData
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int, mFilterDashboard: mFilterDashboard?)
    }

    // data is passed into the constructor
    init {
        mData = data
        this.context = context
    }
}