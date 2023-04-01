package com.github.blarc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.blarc.R
import com.github.blarc.UIUtils


class ChallengeCreateFragment : Fragment() {

    private lateinit var createChallengeBtn: Button;

    private lateinit var challengeTitle: EditText
    private lateinit var challengeDescription: EditText
    private lateinit var challengesDate: EditText
    private lateinit var receiveItemBtn: Button
    private lateinit var giveItemBtn: Button

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
//        createChallengeBtn = view.findViewById(R.id.)
//
//        createChallengeBtn.setOnClickListener {
//            val intent = Intent(context, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//        }

        challengeTitle = view.findViewById(R.id.challenge_title_input)
        challengeDescription = view.findViewById(R.id.challenge_title_desc)
        challengesDate = view.findViewById(R.id.challenge_title_date)

        receiveItemBtn = view.findViewById(R.id.challenge_receive_item)
        giveItemBtn = view.findViewById(R.id.challenge_give_item)

        receiveItemBtn.setOnClickListener {
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.challenge_create_fragment_container,
                InventoryFragment::class.java
            )
        }

        giveItemBtn.setOnClickListener {
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.challenge_create_fragment_container,
                InventoryFragment::class.java
            )
        }

    }
}