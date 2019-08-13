package com.karo.mapsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CategoriesAdapter(val Category:MutableList<String>, val listener: (Int) -> Unit) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View= LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = Category.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text=Category[position]
        /*Glide.with(holder.logoViewItems.context)
            .load(ItemsList?.find{it.name==items[position].name}?.logoImageURL)
            .into(holder.logoViewItems)*/
        return holder.bind(Category[position], position, listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val name:TextView = itemView.findViewById(R.id.categoryName)
        //val logoViewItems: ImageView = itemView.findViewById(R.id.logoItemsView)
        fun bind(category: String, pos: Int, listener: (Int) -> Unit) = with(itemView) {
            this.setOnClickListener {
                listener(pos)
            }
        }
    }
}