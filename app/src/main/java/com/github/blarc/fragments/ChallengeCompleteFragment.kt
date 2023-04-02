package com.github.blarc.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.blarc.R
import com.github.blarc.activities.MainActivity
import com.github.blarc.entity.Item


class ChallengeCompleteFragment(private val item: Item?) : Fragment() {

    private lateinit var completeChallengeBtn: Button;
    private lateinit var completeChallengeItemImage: ImageView
    private lateinit var completeChallengeTextView: TextView

    companion object {
        fun newInstance(item: Item?) = ChallengeCompleteFragment(item)
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
        completeChallengeItemImage = view.findViewById(R.id.fragmentChallengeCompleteImageView)
        completeChallengeTextView = view.findViewById(R.id.fragmentChallengeCompleteTextView)

        item?.let {

            completeChallengeTextView.text = item.name

            view.context.resources.getIdentifier(
                "inventory_item_${it.iconRef}",
                "drawable",
                view.context.packageName
            ).let { imageId -> completeChallengeItemImage.setImageResource(imageId) }
        }


        completeChallengeBtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}