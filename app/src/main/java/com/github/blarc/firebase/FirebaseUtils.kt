package com.github.blarc.firebase

import com.github.blarc.MyApplication.Companion.curUserId
import com.github.blarc.entity.Challenge
import com.github.blarc.entity.Item
import com.github.blarc.entity.User
import com.google.firebase.database.*


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

    fun createUser(user: User, userId: String) {
        getUser(userId).setValue(user)
    }

    fun assignChallengeToUser(challenge: Challenge) {
        val newChallengeRef = getUser(getIdOfCurUser()).child("challenges").push()

        newChallengeRef.setValue(challenge)
    }

    fun assignItemToUser(item: Item) {
        val newItemRef = getUser(getIdOfCurUser()).child("items").push()

        newItemRef.setValue(item);
    }
}