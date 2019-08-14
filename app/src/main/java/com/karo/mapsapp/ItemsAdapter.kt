package com.karo.mapsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemsAdapter(var items: MutableList<Item>,val listener: (Int) -> Unit) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view:View= LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text=items[position].name
        Glide.with(holder.logoViewItems.context)
            .load(ItemsList?.find{it.name==items[position].name}?.logoImageURL)
            .into(holder.logoViewItems)
        return holder.bind(items[position], position, listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val name:TextView = itemView.findViewById(R.id.name)
        val logoViewItems: ImageView = itemView.findViewById(R.id.logoItemsView)
        fun bind(item: Item, pos: Int, listener: (Int) -> Unit) = with(itemView) {
            this.setOnClickListener {
                listener(pos)
            }
        }
    }
    fun filterList(newList:MutableList<Item>)
    {
        items = newList
        notifyDataSetChanged()
    }

}
