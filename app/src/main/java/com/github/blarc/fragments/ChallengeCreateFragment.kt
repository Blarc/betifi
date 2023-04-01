package com.github.blarc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.github.blarc.R
import com.github.blarc.UIUtils
import com.github.blarc.activities.ChallengeCreateActivity
import com.github.blarc.entity.Challenge
import com.github.blarc.firebase.FirebaseUtils


class ChallengeCreateFragment : Fragment() {

    private lateinit var challengeTitle: EditText
    private lateinit var challengeDescription: EditText
    private lateinit var challengesDate: EditText
    private lateinit var receiveItemBtn: Button
    private lateinit var giveItemBtn: Button
    private lateinit var createChallengeBtn: Button
    companion object {
        @JvmStatic
        fun newInstance() = ChallengeCreateFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_challenge_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        challengeTitle = view.findViewById(R.id.challenge_title_input)
        challengeDescription = view.findViewById(R.id.challenge_title_desc)
        challengesDate = view.findViewById(R.id.challenge_title_date)

        val challengeCreateActivity = view.context as ChallengeCreateActivity
        receiveItemBtn = view.findViewById(R.id.challenge_receive_item)
        receiveItemBtn.setOnClickListener {
            challengeCreateActivity.selectedUser = challengeCreateActivity.selectedFriend
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.challenge_create_fragment_container,
                InventoryFragment::class.java
            )
        }

        giveItemBtn = view.findViewById(R.id.challenge_give_item)
        giveItemBtn.setOnClickListener {
            FirebaseUtils.subscribeToUserOnFirebase {
                challengeCreateActivity.selectedUser = it
                UIUtils.replaceFragment(
                    requireActivity(),
                    R.id.challenge_create_fragment_container,
                    InventoryFragment::class.java
                )
            }
        }

        createChallengeBtn = view.findViewById(R.id.create_challenge_create_user_btn)
        createChallengeBtn.setOnClickListener {
            val newChallenge = createChallenge()
            FirebaseUtils.subscribeToUserOnFirebase {
                it?.let { user ->
                    FirebaseUtils.updateUserChallenges(
                        user.userId,
                        user.challenges.plus(newChallenge)
                    )}
            }

            val selectedUser = challengeCreateActivity.selectedUser
            selectedUser?.let {
                FirebaseUtils.updateUserChallenges(
                    it.userId,
                    it.challenges.plus(newChallenge)
                )
            }

            UIUtils.replaceFragment(
                requireActivity(),
                R.id.challenge_create_fragment_container,
                ChallengesFragment::class.java
            )
        }
    }

    private fun createChallenge(): Challenge {
        return Challenge(
            challengeTitle.text.toString(),
            challengeDescription.text.toString(),
            challengesDate.text.toString(),
            "friend",
            null,
            null,
            null,
            null,
            "in_progress"
        )
    }
}

