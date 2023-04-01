package com.github.blarc.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.blarc.R
import com.github.blarc.UIUtils.replaceFragment
import com.github.blarc.fragments.ChallengesFragment
import com.github.blarc.fragments.CharacterFragment
import com.github.blarc.fragments.InventoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavBar: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, CharacterFragment.newInstance())
                .commitNow()
        }

        bottomNavBar = findViewById(R.id.main_bottom_nav_bar)
        bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_character -> replaceFragment(
                    this,
                    R.id.main_fragment_container,
                    CharacterFragment::class.java
                )
                R.id.bottom_nav_challenges -> replaceFragment(
                    this,
                    R.id.main_fragment_container,
                    ChallengesFragment::class.java
                )
                R.id.bottom_nav_feed -> replaceFragment(
                    this,
                    R.id.main_fragment_container,
                    InventoryFragment::class.java
                )
            }
            true
        }

        bottomNavBar.selectedItemId = R.id.bottom_nav_character
    }
}