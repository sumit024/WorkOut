package com.app_devs.workout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history_row.view.*

class HistoryAdapter(val context: Context, val list:ArrayList<String>)
    : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val layoutItem=view.historyItem
        val tvItem=view.tvItem
        val tvPosition=view.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_history_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date:String= list[position]
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.layoutItem.setBackgroundColor(
                    Color.parseColor("#EBEBEB")
            )
        } else {
            holder.layoutItem.setBackgroundColor(
                    Color.parseColor("#FFFFFF")
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}