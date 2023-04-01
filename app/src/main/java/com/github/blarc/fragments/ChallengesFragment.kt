package com.github.blarc.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.blarc.R
import com.github.blarc.activities.ChallengeCreateActivity
import com.github.blarc.entity.Challenge
import com.github.blarc.adapters.ChallengesAdapter
import com.github.blarc.firebase.FirebaseUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChallengesFragment : Fragment() {

    private lateinit var generalChallengesBtn: Button
    private lateinit var friendsChallengesBtn: Button
    private lateinit var challengesList: RecyclerView

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: ChallengesAdapter
    private lateinit var createChallengeBtn: FloatingActionButton

    private var generalChallenges = ArrayList<Challenge>()
    private var friendsChallenges = ArrayList<Challenge>()

    private var firstTime = true
    private var isGeneral = true

    companion object {
        @JvmStatic
        fun newInstance() = ChallengesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_challenges, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseUtils.subscribeToGeneralChallengesOnFirebase {
            generalChallenges = ArrayList(it)
            if (firstTime || isGeneral) {
                setupChallengesList(generalChallenges)
                firstTime = false
            }
        }

        FirebaseUtils.subscribeToFriendsChallengesOnFirebase {
            friendsChallenges = ArrayList(it)
        }

        generalChallengesBtn = view.findViewById(R.id.fragmentChallengesButtonGeneral)
        generalChallengesBtn.setOnClickListener {
            isGeneral = true
            setupChallengesList(generalChallenges)
        }

        friendsChallengesBtn = view.findViewById(R.id.fragmentChallengesButtonFriends)
        friendsChallengesBtn.setOnClickListener {
            isGeneral = false
            setupChallengesList(friendsChallenges)
        }

        createChallengeBtn = view.findViewById(R.id.fragmentChallengesButtonCreate)
        createChallengeBtn.setOnClickListener {
            val intent = Intent(context, ChallengeCreateActivity::class.java)
            startActivity(intent)
        }


        swipeRefreshLayout = view.findViewById(R.id.fragmentChallengesSwipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
            // TODO @Blarc: Refresh challenges array
            swipeRefreshLayout.isRefreshing = false
        }

        challengesList = view.findViewById(R.id.fragmentChallengesRecylerView)

    }

    private fun setupChallengesList(challenges: ArrayList<Challenge>) {
        linearLayoutManager = LinearLayoutManager(context)

        adapter = ChallengesAdapter(challenges)
        challengesList.layoutManager = linearLayoutManager
        challengesList.adapter = adapter
    }
}