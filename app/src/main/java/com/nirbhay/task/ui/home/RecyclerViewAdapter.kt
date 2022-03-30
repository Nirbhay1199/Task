package com.nirbhay.task.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nirbhay.task.R
import com.nirbhay.task.database.TheProject

class RecyclerViewAdapter(private val context: Context, private val listener: RVAdapter): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){

    private val allItems = ArrayList<TheProject>()

    inner class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imageView: ImageView? = itemView.findViewById(R.id.delete)
        val textView: TextView? = itemView.findViewById(R.id.text)
        val button: Button? = itemView.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv,parent,false))
        viewHolder.imageView?.setOnClickListener {
            listener.onItemClicked(allItems[viewHolder.adapterPosition])

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = allItems[position]
        holder.textView?.text = currentItem.anything
    }

    override fun getItemCount(): Int {
        return allItems.size
    }

    fun updateList(newList: List<TheProject>){
        allItems.clear()
        allItems.addAll(newList)

        notifyDataSetChanged()
    }

}

interface RVAdapter{
    fun onItemClicked(item: TheProject)
    fun onButtonClicked(item: TheProject)
}