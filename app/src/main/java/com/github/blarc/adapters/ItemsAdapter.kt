package com.github.blarc.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.blarc.R
import com.github.blarc.entity.Item
import com.github.blarc.inflate
import java.util.*

class ItemsAdapter(
    private var items: List<Item>
): RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ItemHolder {

        return ItemHolder(parent.inflate(R.layout.inventory_item, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemsAdapter.ItemHolder, position: Int) {
        if (items.isNotEmpty()) {
            val user = items[position]
            holder.bindItem(user)
        }
    }


    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var item: Item? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }

        fun bindItem(item: Item) {
            this.item = item

            val titleTextView: TextView = view.findViewById(R.id.inventory_item_title)
            titleTextView.text = item.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            val rarityTextView: TextView = view.findViewById(R.id.inventory_item_rarity)
            rarityTextView.text = item.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

        }
    }
}