package com.github.blarc

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.blarc.entity.Challenge
import com.github.blarc.entity.Item
import com.github.blarc.entity.User
import com.github.blarc.firebase.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class BaseViewModel : ViewModel() {
    val challenges = MutableLiveData<MutableList<Challenge>>(mutableListOf(
        Challenge("Title 1", "Desc 1", "Id 1", "Created by 1"),
        Challenge("Title 2", "Desc 2", "Id 2", "Created by 2"),
        Challenge("Title 3", "Desc 3", "Id 3", "Created by 3"),
        Challenge("Title 4", "Desc 4", "Id 4", "Created by 4")
    ))

    val friends = MutableLiveData<MutableList<User>>(mutableListOf(
        User("Martin"),
        User("Jakob"),
        User("Gabrijel"),
        User("Matic"),
        User("Gregor")
    ))

    val userCompletedChallenges = MutableLiveData<MutableList<Challenge>>(mutableListOf())
    val userItems = MutableLiveData<MutableList<Item>>(mutableListOf())
    val items = MutableLiveData<MutableList<Item>>(mutableListOf())
    var basicItem = Item()

    init {
        subscribeToUserChallengesOnFirebase()
        subscribeToUserItemsOnFirebase()
        subscribeToItemsOnFirebase()
    }

    private fun subscribeToUserChallengesOnFirebase() {
        // fill in user items list
        FirebaseUtils.database.getReference(FirebaseUtils.getIdOfCurUser()).child("challenges").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val challenges = mutableListOf<Challenge>()

                for (itemSnapshot in dataSnapshot.children) {
                    val challenge = itemSnapshot.getValue(Challenge::class.java)

                    if (challenge != null) {
                        challenges.add(challenge)
                    } else {
                        Log.d(TAG, "challenge je null")
                    }
                }

                userCompletedChallenges.value = challenges
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
            }
        })
    }

    private fun subscribeToUserItemsOnFirebase() {
        // fill in user items list
        FirebaseUtils.database.getReference(FirebaseUtils.getIdOfCurUser()).child("items").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userItemsIn = mutableListOf<Item>()

                // Loop through the children of the "items" node and create an Item object for each child
                for (itemSnapshot in dataSnapshot.children) {
                    val item = itemSnapshot.getValue(Item::class.java)

                    if (item != null) {
                        userItemsIn.add(item)
                    } else {
                        Log.d(TAG, "item can not be deserialized!")
                    }
                }

                userItems.value = userItemsIn
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
            }
        })
    }

    private fun subscribeToItemsOnFirebase() {
        // fill in user items list
        FirebaseUtils.database.getReference("items").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val itemsIn = mutableListOf<Item>()

                // Loop through the children of the "items" node and create an Item object for each child
                for (itemSnapshot in dataSnapshot.children) {
                    val itemId = itemSnapshot.key.toString()
                    val item = itemSnapshot.getValue(Item::class.java)

                    if (item != null) {
                        itemsIn.add(item)

                        if (itemId == "1") {
                            basicItem = item
                        }
                    } else {
                        Log.d(TAG, "challenge je null")
                    }
                }

                items.value = itemsIn
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
            }
        })
    }
}