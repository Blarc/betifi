package com.github.blarc.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.blarc.R
import com.github.blarc.entity.Challenge
import com.github.blarc.entity.User
import com.github.blarc.fragments.ChallengeAssignFragment
import com.github.blarc.fragments.ChallengeCreateFragment

class ChallengeCreateActivity : AppCompatActivity() {

    var selectedFriends: MutableList<User> = mutableListOf()
    var challenge: Challenge? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_create)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.challenge_create_fragment_container, ChallengeAssignFragment.newInstance())
                .commitNow()
        }
    }
    fun addOrRemoveUserFromSelectedList(user: User) {
        if (selectedFriends.contains(user)) {
            selectedFriends.remove(user)
        }
        else {
            selectedFriends.add(user)
        }
    }
}