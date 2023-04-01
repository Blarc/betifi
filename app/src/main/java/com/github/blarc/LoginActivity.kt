package com.github.blarc

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.github.blarc.activities.MainActivity
import com.github.blarc.firebase.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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
//
//            myRef.setValue("Hello, World!")
            val userIdValue = userId.text.toString()

            val userRef = FirebaseUtils.getUser(userIdValue)

            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MyApplication.curUserId = userIdValue
                        Log.d(TAG, "User $userIdValue exists")

                        navigateToMainActivity()
                    } else {
                        MyApplication.curUserId = userIdValue
                        // Create the user in the database
                        userRef.setValue("your_user_data")
                        Log.d(TAG, "User $userIdValue created")

                        navigateToMainActivity()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException())
                }
            })
        }
    }

    fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}