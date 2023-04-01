package com.github.blarc

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.github.blarc.activities.MainActivity
import com.github.blarc.entity.User
import com.github.blarc.firebase.FirebaseUtils

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
            // This is just reference how to connect
//            val database = FirebaseDatabase.getInstance("https://estec-challenge-2023-default-rtdb.europe-west1.firebasedatabase.app/")
//            val myRef = database.getReference("message")

            val userIdValue = userId.text.toString()
            if (userIdValue.isEmpty()) {
                Toast.makeText(this, "Please enter a user id", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            MyApplication.curUserId = userIdValue
            FirebaseUtils.getUserOnFirebase {
                if (it != null) {
                    Log.d(TAG, "User $userIdValue already exists")
                    navigateToMainActivity()
                    return@getUserOnFirebase
                }

                // Create the user in the database
                FirebaseUtils.subscribeToSpecificItem("0") {item ->

                    val user = User(userIdValue, listOf(), listOf())
                    if (item != null) {
                        user.items = listOf(item)
                    }

                    FirebaseUtils.createUser(user, userIdValue)
                    Log.d(TAG, "User $userIdValue created")

                    navigateToMainActivity()
                }
            }
        }
    }

    fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}