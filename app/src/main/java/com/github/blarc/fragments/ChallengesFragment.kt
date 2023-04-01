package com.github.blarc.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.github.blarc.BaseViewModel
import com.github.blarc.R
import com.github.blarc.UIUtils.replaceFragment
import com.github.blarc.activities.ChallengeCreateActivity
import com.github.blarc.activities.ChallengeDetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChallengesFragment : Fragment() {

    private lateinit var createChallengeBtn: FloatingActionButton
    private lateinit var detailChallengeBtn: Button

    private val baseViewModel: BaseViewModel by activityViewModels()

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

        detailChallengeBtn = view.findViewById(R.id.fragmentChallengesButtonDetail)
        detailChallengeBtn.setOnClickListener {
            val intent = Intent(context, ChallengeDetailActivity::class.java)
            startActivity(intent)
        }

        createChallengeBtn = view.findViewById(R.id.fragmentChallengesButtonCreate)
        createChallengeBtn.setOnClickListener {
            val intent = Intent(context, ChallengeCreateActivity::class.java)
            startActivity(intent)
        }

    }
}