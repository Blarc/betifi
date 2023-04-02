package com.github.blarc.adapters

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.blarc.R
import com.github.blarc.activities.ChallengeDetailActivity
import com.github.blarc.entity.Challenge
import com.github.blarc.fragments.ARG_CHALLENGE_DETAILS
import com.github.blarc.inflate
import java.util.*
import kotlin.collections.ArrayList

class ChallengesAdapter(
    private var challenges: ArrayList<Challenge>
) : RecyclerView.Adapter<ChallengesAdapter.ChallengeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeHolder {
        return ChallengeHolder(parent.inflate(R.layout.challenge_item, false))
    }

    override fun getItemCount() = challenges.size

    override fun onBindViewHolder(holder: ChallengeHolder, position: Int) {
        if (challenges.isNotEmpty()) {
            val challenge = challenges[position]
            holder.bindChallenge(challenge)
        }
    }

    inner class ChallengeHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var challenge: Challenge? = null;

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(v!!.context, ChallengeDetailActivity::class.java)
            intent.putExtra(ARG_CHALLENGE_DETAILS, challenge)
            startActivity(v.context, intent, null)
        }

        fun bindChallenge(challenge: Challenge) {
            this.challenge = challenge

            val titleTextView: TextView = view.findViewById(R.id.challenge_item_challenge_title)
            titleTextView.text = challenge.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            val dueTextView: TextView = view.findViewById(R.id.challenge_item_due)
            dueTextView.text = "${challenge.duration.toString()} days"
        }
    }
}