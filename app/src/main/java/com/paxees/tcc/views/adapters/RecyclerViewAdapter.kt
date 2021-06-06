package com.paxees.tcc.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paxees.tcc.R
import com.paxees.tcc.models.Plants
import java.util.*

class RecyclerViewAdapter(context: Context, data: ArrayList<Plants>, pos: Int) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var row_index = 0
    private val mData: ArrayList<Plants>
    private var view: View? = null
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private val context: Context
    private var pos = 0

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = if (pos == 1) {
            mInflater.inflate(R.layout.item_views_findus, parent, false)
        }else if (pos == 2) {
            mInflater.inflate(R.layout.item_views_diagnose, parent, false)
        } else {
            mInflater.inflate(R.layout.item_views, parent, false)
        }
        return ViewHolder(view!!)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = mData[position]
        holder.plantsId.text = animal.plantValue
        holder.itemView.setOnClickListener {
            this.mClickListener?.onItemClick(view, position)
            row_index = position
            notifyDataSetChanged()
        }
        if (pos == 0 || pos == 1) {
            if (row_index == position) {
                holder.plantsId.background = context.resources.getDrawable(R.drawable.bg_border_square_green)
            } else {
                holder.plantsId.background = context.resources.getDrawable(R.drawable.bg_border_square_black)
            }
        } else if (pos == 2) {
            if (row_index == position) {
                holder.plantsId.background = context.resources.getDrawable(R.drawable.bg_border_square_green_diagnose)
            } else {
                holder.plantsId.background = context.resources.getDrawable(R.drawable.bg_border_square_diagnose)
            }
        }
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var plantsId: TextView
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            plantsId = itemView.findViewById(R.id.plantsId)
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Plants {
        return mData[id]
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
        this.pos = pos
    }
}