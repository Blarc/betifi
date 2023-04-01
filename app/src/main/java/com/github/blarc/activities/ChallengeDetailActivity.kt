package com.github.blarc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.blarc.R
import com.github.blarc.fragments.ChallengeCreateFragment
import com.github.blarc.fragments.ChallengeDetail

class ChallengeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activityChallengeDetailFrameLayout, ChallengeDetail.newInstance())
                .commitNow()
        }
    }
}