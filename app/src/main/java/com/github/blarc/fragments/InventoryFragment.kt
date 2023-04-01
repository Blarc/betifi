package com.github.blarc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.blarc.R
import com.github.blarc.entity.Challenge

const val ARG_INVENTORY_USER = "inventory_user"

class InventoryFragment : Fragment() {

    private var userId: String? = null

    companion object {
        @JvmStatic
        fun newInstance(challenge: Challenge) =
            ChallengeDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_INVENTORY_USER, challenge)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(ARG_INVENTORY_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

}