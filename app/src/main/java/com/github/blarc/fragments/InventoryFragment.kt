package com.github.blarc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.blarc.R
import com.github.blarc.activities.ChallengeCreateActivity
import com.github.blarc.adapters.ItemsAdapter
import com.github.blarc.entity.Item
import com.github.blarc.entity.User
import com.github.blarc.firebase.FirebaseUtils

class InventoryFragment(private val itemType: String? = null, val equipmentList: MutableList<Item>? = mutableListOf()) : Fragment() {

    private lateinit var itemsList: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: ItemsAdapter
    private lateinit var emptyTextView: TextView

    companion object {
        @JvmStatic
        fun newInstance(itemType: String, equipmentList: MutableList<Item>) = InventoryFragment(itemType, equipmentList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emptyTextView = view.findViewById(R.id.fragmentInventoryEmptyInventoryTextView)
        swipeRefreshLayout = view.findViewById(R.id.fragmentInventorySwipeRefreshLayout)
        itemsList = view.findViewById(R.id.fragmentInventoryRecylerView)


        var selectedUser: User? = null
        if (context is ChallengeCreateActivity) {
            selectedUser = (context as ChallengeCreateActivity).selectedUser
        }

        if (selectedUser != null) {
            setupItemsList(selectedUser.items)
        } else {
            FirebaseUtils.subscribeToUserItemsOnFirebase {
                if (itemType != null) {
                    setupItemsList(it.filter { item -> item.type == itemType })
                } else {
                    setupItemsList(it)
                }
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            // TODO @Blarc: Refresh challenges array
            swipeRefreshLayout.isRefreshing = false
        }

    }
    private fun setupItemsList(items: List<Item>) {

        if (items.isEmpty()) {
            emptyTextView.visibility = View.VISIBLE
            swipeRefreshLayout.visibility = View.GONE
        } else {
            emptyTextView.visibility = View.GONE
            swipeRefreshLayout.visibility = View.VISIBLE
        }

        linearLayoutManager = LinearLayoutManager(context)

        adapter = ItemsAdapter(items, equipmentList)
        itemsList.layoutManager = linearLayoutManager
        itemsList.adapter = adapter
    }
}