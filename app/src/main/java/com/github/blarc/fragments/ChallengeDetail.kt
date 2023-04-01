package com.github.blarc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.blarc.BaseViewModel
import com.github.blarc.R
import com.github.blarc.UIUtils.replaceFragment

class ChallengeDetail : Fragment() {
    private lateinit var createChallengeBtn: Button;

    private val baseViewModel: BaseViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance() = ChallengeDetail()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_challenge_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createChallengeBtn = view.findViewById(R.id.fragmentChallengeDetailButtonComplete)

        createChallengeBtn.setOnClickListener {

            replaceFragment(requireActivity(), R.id.activityChallengeDetailFrameLayout, ChallengeCompleteFragment::class.java)

        }

    }
}