package com.example.masterdetail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AvengerAdapter(val list: ArrayList<AvengerData>):RecyclerView.Adapter<AvengerAdapter.AvengerViewHolder> (){
    private lateinit var mtListener: onItemSelectListener
    interface onItemSelectListener{
        fun onItemSelect(pos:Int)
    }
    fun setOnItemSelectListener(listener: onItemSelectListener)
    {
        mtListener=listener
    }
    class AvengerViewHolder(view:View, val listener: onItemSelectListener):RecyclerView.ViewHolder(view) {
        val txtname=view.findViewById<TextView>(R.id.txtName)
        val txtrating=view.findViewById<TextView>(R.id.txtRating)
        val imgimage=view.findViewById<ImageView>(R.id.imgView)
        init {
            view.setOnClickListener {
                listener.onItemSelect(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvengerViewHolder {
        return AvengerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.avenger_single,parent,false),mtListener)
    }

    override fun onBindViewHolder(holder: AvengerViewHolder, position: Int) {
        val avenger=list[position]
        holder.txtname.text=avenger.name
        holder.txtrating.text=avenger.rating
        holder.imgimage.setImageBitmap(avenger.image)
    }

    override fun getItemCount() = list.size
}