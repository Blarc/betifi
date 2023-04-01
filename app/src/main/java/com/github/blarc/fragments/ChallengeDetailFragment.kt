package com.github.blarc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.blarc.R
import com.github.blarc.UIUtils.replaceFragment
import com.github.blarc.entity.Challenge


const val ARG_CHALLENGE_DETAILS = "challenge"

class ChallengeDetailFragment : Fragment() {
    private lateinit var dueOrCompletedTextView: TextView
    private lateinit var challengeFinishButton: Button
    private lateinit var descriptionTextView: TextView
    private lateinit var titleTextView: TextView

    private var challenge: Challenge? = null


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

        dueOrCompletedTextView = view.findViewById(R.id.details_challenge_due_or_completed)
        challengeFinishButton = view.findViewById(R.id.details_challenge_finish_btn)
        titleTextView = view.findViewById(R.id.challenge_title_input)
        descriptionTextView = view.findViewById(R.id.challenge_title_desc)

        titleTextView.text = challenge?.name ?: "No title."
        descriptionTextView.text = challenge?.description ?: "No description"

        challengeFinishButton.setOnClickListener {
            replaceFragment(
                requireActivity(),
                R.id.activityChallengeDetailFrameLayout,
                ChallengeCompleteFragment::class.java
            )
        }
    }
}