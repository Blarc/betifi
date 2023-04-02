package com.github.blarc.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.blarc.MyApplication
import com.github.blarc.R
import com.github.blarc.entity.Challenge
import com.github.blarc.entity.User
import com.github.blarc.fragments.ChallengeAssignFragment

class ChallengeCreateActivity : AppCompatActivity() {

    var selectedFriend: User? = null
    var selectedUser: User? = null
    var challenge: Challenge = Challenge(challengeCreator = MyApplication.curUserId)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_create)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.challenge_create_fragment_container, ChallengeAssignFragment.newInstance())
                .commitNow()
        }
    }
}