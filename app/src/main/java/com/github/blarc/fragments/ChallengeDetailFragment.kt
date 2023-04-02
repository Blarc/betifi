package com.github.blarc.fragments

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.blarc.R
import com.github.blarc.UIUtils.replaceFragment
import com.github.blarc.activities.ChallengeDetailActivity
import com.github.blarc.activities.MainActivity
import com.github.blarc.entity.Challenge
import com.github.blarc.entity.Item
import com.github.blarc.firebase.FirebaseUtils


const val ARG_CHALLENGE_DETAILS = "challenge"

class ChallengeDetailFragment : Fragment() {
    private lateinit var dueOrCompletedTextView: TextView
    private lateinit var challengeFinishButton: Button
    private lateinit var descriptionTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var durationTextView: TextView

    private lateinit var requestedItemLayout: LinearLayout
    private lateinit var bettedItemLayout: LinearLayout
    private lateinit var acceptDeclineLayout: LinearLayout
    private lateinit var acceptChallengeBtn: Button
    private lateinit var declineChallengeBtn: Button

    private lateinit var requestedItemImage: ImageView

    private lateinit var bettedItemImage: ImageView

    private lateinit var challenge: Challenge


    companion object {
        @JvmStatic
        fun newInstance(challenge: Challenge) =
            ChallengeDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CHALLENGE_DETAILS, challenge)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            challenge = it.getSerializable(ARG_CHALLENGE_DETAILS) as Challenge
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_challenge_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestedItemLayout = view.findViewById(R.id.challenge_detail_requested_item_layout)
        bettedItemLayout = view.findViewById(R.id.challenge_detail_betted_item_layout)
        acceptDeclineLayout = view.findViewById(R.id.details_challenge_accept_decline_layout)

        requestedItemImage = view.findViewById(R.id.challenge_detail_requested_item_icon)
        bettedItemImage = view.findViewById(R.id.challenge_detail_betted_item_icon)

        dueOrCompletedTextView = view.findViewById(R.id.challenge_title_date)
        challengeFinishButton = view.findViewById(R.id.details_challenge_finish_btn)
        titleTextView = view.findViewById(R.id.challenge_title_input)
        descriptionTextView = view.findViewById(R.id.challenge_title_desc)
        durationTextView = view.findViewById(R.id.challenge_detail_duration)

        acceptChallengeBtn = view.findViewById(R.id.details_challenge_accept_btn)
        declineChallengeBtn = view.findViewById(R.id.details_challenge_decline_btn)

        var item: Item? = null

        if (challenge.type == "friend") {
            requestedItemLayout.visibility = View.VISIBLE
            bettedItemLayout.visibility = View.VISIBLE
            acceptDeclineLayout.visibility = View.VISIBLE
            challengeFinishButton.visibility = View.GONE

            challenge.acceptingItem?.let { Log.w("", it.iconRef) }
            challenge.acceptingItem?.let {
                view.context.resources.getIdentifier(
                    "inventory_item_${it.iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { imageId -> requestedItemImage.setImageResource(imageId) }
            }

            challenge.givingItem?.let { Log.w("", it.iconRef) }
            challenge.givingItem?.let {
                view.context.resources.getIdentifier(
                    "inventory_item_${it.iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { imageId -> bettedItemImage.setImageResource(imageId) }
            }

            acceptChallengeBtn.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

        } else {
            requestedItemLayout.visibility = View.GONE
            bettedItemLayout.visibility = View.GONE
            acceptDeclineLayout.visibility = View.GONE
            challengeFinishButton.visibility = View.VISIBLE
        }

        titleTextView.text = challenge.name
        descriptionTextView.text = challenge.description
        if (challenge.duration != null) {
            durationTextView.text = challenge.duration.toString()
        }

        challengeFinishButton.setOnClickListener {

            if (item == null) {
                FirebaseUtils.subscribeToItemsOnFirebase {
                    item = it.random()
                    replaceFragment(
                        requireActivity(),
                        R.id.activityChallengeDetailFrameLayout,
                        ChallengeCompleteFragment.newInstance(item)
                    )
                }
            }
            else {
                replaceFragment(
                    requireActivity(),
                    R.id.activityChallengeDetailFrameLayout,
                    ChallengeCompleteFragment.newInstance(item)
                )
            }
        }
    }
}