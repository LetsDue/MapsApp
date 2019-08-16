package com.karo.mapsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(private var Categories:MutableList<String>, private val listener: (Int) -> Unit) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View= LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = Categories.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text=Categories[position]
        /*Glide.with(holder.logoViewItems.context)
            .load(ItemsList?.find{it.name==items[position].name}?.logoImageURL)
            .into(holder.logoViewItems)*/
        return holder.bind(position, listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val name:TextView = itemView.findViewById(R.id.categoryName)
        //val logoViewItems: ImageView = itemView.findViewById(R.id.logoItemsView)
        fun bind(pos: Int, listener: (Int) -> Unit) = with(itemView) {
            this.setOnClickListener {
                listener(pos)
            }
        }
    }
    fun filterList(newList:MutableList<String>)
    {
        Categories = newList
        notifyDataSetChanged()
    }
}