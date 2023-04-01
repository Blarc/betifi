package com.github.blarc.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.blarc.BaseViewModel
import com.github.blarc.R
import com.github.blarc.activities.MainActivity


class ChallengeCreateFragment : Fragment() {

    private lateinit var createChallengeBtn: Button;

    private val baseViewModel: BaseViewModel by activityViewModels()

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
        createChallengeBtn = view.findViewById(R.id.fragmentChallengeButtonCreate)

        createChallengeBtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }
}