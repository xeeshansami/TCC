package com.paxees.tcc.views.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paxees.tcc.R
import com.paxees.tcc.models.DrawerItem
import com.paxees.tcc.models.DrawerModel
import kotlinx.android.synthetic.main.view_drawer_item.view.*

class DrawerAdapter(private val mList: MutableList<DrawerModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*   open inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
           val tvTitle = view.tvTitle!!
           val statusImg = view.statusImg2!!
       }*/


    open inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tvItemTitle!!
//        val statusImg = view.statusImg!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            /* 0, 1 -> {
                 val layout = LayoutInflater.from(parent.context)
                     .inflate(R.layout.view_drawer_header, parent, false)
 //                HeaderViewHolder(layout)
             }*/
            else -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_drawer_item, parent, false)
                ItemViewHolder(layout)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].item!!.ordinal
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (mList[position].item) {
            DrawerItem.ITEM_LINEUP -> {
                val headerHolder = holder as ItemViewHolder
                headerHolder.tvTitle.text = mList[position].title
            }
        }
    }

}