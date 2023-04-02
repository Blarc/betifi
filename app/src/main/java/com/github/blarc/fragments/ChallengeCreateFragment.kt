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
    private lateinit var challengeDate: EditText
    private lateinit var challengeDuration: EditText
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

        val challengeCreateActivity = view.context as ChallengeCreateActivity
        val challenge = challengeCreateActivity.challenge

        challengeTitle = view.findViewById(R.id.challenge_title_input)
        challengeTitle.setText(challenge.name)

        challengeDescription = view.findViewById(R.id.challenge_title_desc)
        challengeDescription.setText(challenge.description)

        challengeDate = view.findViewById(R.id.challenge_title_date)
        challengeDate.setText(challenge.dueTo)

        challengeDuration = view.findViewById(R.id.challenge_duration)
        challengeDuration.setText(challenge.duration?.toString())

        receiveItemBtn = view.findViewById(R.id.challenge_receive_item)
        challenge.acceptingItem?.let { receiveItemBtn.text = it.name }
        receiveItemBtn.setOnClickListener {
            updateChallenge(challenge)
            challengeCreateActivity.selectedUser = challengeCreateActivity.selectedFriend
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.challenge_create_fragment_container,
                InventoryFragment::class.java
            )
        }

        giveItemBtn = view.findViewById(R.id.challenge_give_item)
        challenge.givingItem?.let { giveItemBtn.text = it.name }
        giveItemBtn.setOnClickListener {
            updateChallenge(challenge)
            FirebaseUtils.getUserOnFirebase {
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
            updateChallenge(challenge)
            FirebaseUtils.getUserOnFirebase {
                it?.let { user ->
                    FirebaseUtils.updateUserChallenges(
                        user.userId,
                        user.challenges.plus(challengeCreateActivity.challenge)
                    )
                }
            }

            val selectedUser = challengeCreateActivity.selectedUser
            selectedUser?.let {
                FirebaseUtils.updateUserChallenges(
                    it.userId,
                    it.challenges.plus(challengeCreateActivity.challenge)
                )
            }

            UIUtils.replaceFragment(
                requireActivity(),
                R.id.challenge_create_fragment_container,
                ChallengesFragment::class.java
            )
        }
    }

    private fun updateChallenge(challenge: Challenge) {
        challenge.name = challengeTitle.text.toString()
        challenge.description = challengeDescription.text.toString()
        challenge.dueTo = challengeDate.text.toString()
        if (challengeDuration.text.isNotBlank()) {
            challenge.duration = challengeDuration.text.toString().toInt()
        }
        challenge.type = "friend"
        challenge.status = "in_progress"
    }
}

