package com.github.blarc.activities

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.blarc.MyApplication
import com.github.blarc.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val curUserId = MyApplication.curUserId

        Log.d(TAG, "Welcome user $curUserId")

    }
}