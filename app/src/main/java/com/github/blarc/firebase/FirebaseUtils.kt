package com.github.blarc.firebase

import com.github.blarc.MyApplication.Companion.curUserId
import com.github.blarc.entity.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


object FirebaseUtils {

    val database = FirebaseDatabase.getInstance("https://estec-challenge-2023-default-rtdb.europe-west1.firebasedatabase.app/")

    fun getIdOfCurUser() : String {
        return curUserId
    }

    private fun getUsersRef(): DatabaseReference {
        return database.getReference("users")
    }

    fun getUser(userId: String): DatabaseReference {
        return getUsersRef().child(userId)
    }

    fun addUser(user: User) {
        val myRef = database.getReference(user.id)

        myRef.setValue(user)
    }



}