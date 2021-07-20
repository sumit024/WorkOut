package com.app_devs.workout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_status.view.*

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>, val ctx: Context)
                            : RecyclerView.Adapter<ExerciseStatusAdapter.mViewHolder>() {

    class mViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val tvItem=view.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_rv_status,parent,false)
        return mViewHolder(v)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val model:ExerciseModel=items[position]
        holder.tvItem.text=model.getId().toString()
        if(model.getIsSelected())
        {
           holder.tvItem.background= ContextCompat.getDrawable(ctx,R.drawable.item_circular_number)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }
        else if(model.getIsCompleted())
        {
            holder.tvItem.background= ContextCompat.getDrawable(ctx,R.drawable.circle_background_custom)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else{
            holder.tvItem.background= ContextCompat.getDrawable(ctx,R.drawable.item_circular_color_gray_bg)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }
    }

    override fun getItemCount(): Int {
        return items.size;
    }

}