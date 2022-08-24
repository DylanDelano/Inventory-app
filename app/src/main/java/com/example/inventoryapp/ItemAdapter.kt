package com.example.inventoryapp

import android.app.Notification
import androidx.activity.OnBackPressedCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryapp.databases.Itemsdata
import kotlinx.android.synthetic.main.custom_xml.view.*
import kotlinx.android.synthetic.main.fragment_list_item.view.*

class ItemAdapter:RecyclerView.Adapter<ItemAdapter.itemViewHolder>() {
    private var itemList= emptyList<Itemsdata>()
    class itemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_xml,parent,false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemView.name_row.text = currentItem.Item
        holder.itemView.price_row.text = currentItem.Price.toString()
        holder.itemView.quantity_row.text = currentItem.Quantity.toString()

        holder.itemView.list_items.setOnClickListener {
            val Action = ListFragmentDirections.actionListItemFragmentToEditFragment(currentItem)
            holder.itemView.findNavController().navigate(Action)
        }

    }
    fun setData(itemsdata:List<Itemsdata>){
        this.itemList = itemsdata
        notifyDataSetChanged()
    }

}