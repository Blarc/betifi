package com.github.blarc.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.github.blarc.R
import com.github.blarc.activities.MainActivity


class ChallengeCompleteFragment : Fragment() {

    private lateinit var completeChallengeBtn: Button;

    companion object {
        fun newInstance() = ChallengeCompleteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_challenge_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        completeChallengeBtn = view.findViewById(R.id.fragmentChallengeCompleteButtonComplete)

        completeChallengeBtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}