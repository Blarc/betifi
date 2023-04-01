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
import com.github.blarc.adapters.UsersAdapter
import com.github.blarc.entity.User
import com.github.blarc.firebase.FirebaseUtils


class ChallengeAssignFragment : Fragment() {
    private lateinit var friendsList: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: UsersAdapter

    companion object {
        @JvmStatic
        fun newInstance() = ChallengeAssignFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_challenge_assign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout = view.findViewById(R.id.assign_challenge_swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            // TODO @Blarc: Refresh user array
            swipeRefreshLayout.isRefreshing = false
        }

        friendsList = view.findViewById(R.id.assign_challenge_users_list)

        FirebaseUtils.subscribeToUsersOnFirebase {
            setupFriendsList(it)
        }

    }

    private fun setupFriendsList(users: List<User>) {
        linearLayoutManager = LinearLayoutManager(context)

        adapter = UsersAdapter(ArrayList(users), R.layout.assign_challenge_item, this.requireContext())

        friendsList.layoutManager = linearLayoutManager
        friendsList.adapter = adapter
    }
}