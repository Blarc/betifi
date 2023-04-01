package com.github.blarc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.blarc.R
import com.github.blarc.activities.ChallengeCreateActivity
import com.github.blarc.adapters.ItemsAdapter
import com.github.blarc.entity.Item
import com.github.blarc.entity.User
import com.github.blarc.firebase.FirebaseUtils

class InventoryFragment : Fragment() {

    private lateinit var itemsList: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: ItemsAdapter

    companion object {
        @JvmStatic
        fun newInstance() = InventoryFragment()
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

        var selectedFriend: User? = null
        if (context is ChallengeCreateActivity) {
            selectedFriend = (context as ChallengeCreateActivity).selectedFriend
        }

        itemsList = view.findViewById(R.id.fragmentInventoryRecylerView)
        if (selectedFriend != null) {
            setupItemsList(selectedFriend.items)
        } else {
            FirebaseUtils.subscribeToUserItemsOnFirebase {
                setupItemsList(it)
            }
        }

        swipeRefreshLayout = view.findViewById(R.id.fragmentInventorySwipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            // TODO @Blarc: Refresh challenges array
            swipeRefreshLayout.isRefreshing = false
        }
    }
    private fun setupItemsList(items: List<Item>) {
        linearLayoutManager = LinearLayoutManager(context)

        adapter = ItemsAdapter(items)
        itemsList.layoutManager = linearLayoutManager
        itemsList.adapter = adapter
    }
}