package com.github.blarc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.blarc.R
import com.github.blarc.entity.Challenge
import com.github.blarc.fragments.ARG_CHALLENGE_DETAILS
import com.github.blarc.fragments.ChallengeDetailFragment

class ChallengeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.activityChallengeDetailFrameLayout,
                    ChallengeDetailFragment.newInstance(
                        intent.getSerializableExtra(ARG_CHALLENGE_DETAILS) as Challenge
                    )
                )
                .commitNow()
        }
    }
}