package com.github.blarc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.blarc.R
import com.github.blarc.fragments.ChallengeCreateFragment

class ChallengeCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_create)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.challenge_create_fragment_container, ChallengeCreateFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
    }
}