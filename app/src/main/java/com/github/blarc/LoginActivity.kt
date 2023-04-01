package com.github.blarc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.github.blarc.activities.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    lateinit var userId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.activityLoginButtonLogin)
        userId = findViewById(R.id.activityLoginEditTextName)

        loginButton.setOnClickListener {

            // TODO @martinb: Check if user exists in database

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}