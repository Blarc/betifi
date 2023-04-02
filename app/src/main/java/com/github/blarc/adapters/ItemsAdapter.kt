package com.github.blarc.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.blarc.MyApplication
import com.github.blarc.R
import com.github.blarc.UIUtils
import com.github.blarc.activities.ChallengeCreateActivity
import com.github.blarc.activities.MainActivity
import com.github.blarc.entity.Item
import com.github.blarc.firebase.FirebaseUtils
import com.github.blarc.fragments.ChallengeCreateFragment
import com.github.blarc.fragments.CharacterFragment
import com.github.blarc.inflate
import java.util.*

class ItemsAdapter(
    private var items: List<Item>,
    private var equippedList: MutableList<Item>?
): RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {

    private val rarityOpacity = mapOf(
        "common" to 0.4f,
        "rare" to 0.6f,
        "epic" to 0.8f,
        "legendary" to 1f
    )

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
            v?.context?.let { context ->
                if (context is ChallengeCreateActivity) {
                    context.selectedUser?.let {
                        if (it.userId == MyApplication.curUserId) {
                            context.challenge.givingItem = item
                        } else {
                            context.challenge.acceptingItem = item
                        }
                    }
                    context.supportFragmentManager.popBackStack()
                }

                if (context is MainActivity) {

                    val curEquippedList2 = equippedList?.filter { it.type != item?.type }?.toMutableList()
                    item?.let { curEquippedList2?.add(it) }

                    FirebaseUtils.updateUserEquippedItems(FirebaseUtils.getIdOfCurUser(), curEquippedList2);

                    UIUtils.replaceFragment(
                        context,
                        R.id.main_fragment_container,
                        CharacterFragment::class.java
                    )
                }
            }
        }

        fun bindItem(item: Item) {
            this.item = item

            val avatarImageView: ImageView = view.findViewById(R.id.inventory_item_avatar)
            view.context.resources.getIdentifier(
                "inventory_item_${item.iconRef}",
                "drawable",
                view.context.packageName
            ).let { avatarImageView.setImageResource(it) }

            val titleTextView: TextView = view.findViewById(R.id.inventory_item_title)
            titleTextView.text = item.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            val rarityTextView: TextView = view.findViewById(R.id.inventory_item_rarity)
            rarityTextView.text = item.rarity.uppercase()
            rarityTextView.alpha = rarityOpacity[item.rarity] ?: 1.0f

        }
    }
}