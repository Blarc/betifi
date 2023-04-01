package com.github.blarc.firebase

import android.content.ContentValues
import android.util.Log
import com.github.blarc.MyApplication.Companion.curUserId
import com.github.blarc.entity.Challenge
import com.github.blarc.entity.Item
import com.github.blarc.entity.User
import com.google.firebase.database.*


object FirebaseUtils {

    val database =
        FirebaseDatabase.getInstance("https://estec-challenge-2023-default-rtdb.europe-west1.firebasedatabase.app/")

    fun getIdOfCurUser(): String {
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

    fun subscribeToFriendsChallengesOnFirebase(setValue: (List<Challenge>) -> Unit) {
        database.getReference(getIdOfCurUser()).child("challenges")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val challenges = mutableListOf<Challenge>()

                    for (itemSnapshot in dataSnapshot.children) {
                        val challenge = itemSnapshot.getValue(Challenge::class.java)

                        if (challenge != null) {
                            challenges.add(challenge)
                        } else {
                            Log.d(ContentValues.TAG, "challenge je null")
                        }
                    }
                    setValue(challenges)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
                }
            })
    }

    fun subscribeToGeneralChallengesOnFirebase(setValue: (List<Challenge>) -> Unit) {
        database.getReference("challenges").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val challenges = mutableListOf<Challenge>()

                for (itemSnapshot in dataSnapshot.children) {
                    val challenge = itemSnapshot.getValue(Challenge::class.java)

                    if (challenge != null) {
                        challenges.add(challenge)
                    } else {
                        Log.d(ContentValues.TAG, "challenge je null")
                    }
                }
                setValue(challenges)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
            }
        })
    }

    fun subscribeToUserItemsOnFirebase(setValue: (List<Item>) -> Unit) {
        // fill in user items list
        database.getReference(getIdOfCurUser()).child("items")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userItemsIn = mutableListOf<Item>()

                    // Loop through the children of the "items" node and create an Item object for each child
                    for (itemSnapshot in dataSnapshot.children) {
                        val item = itemSnapshot.getValue(Item::class.java)

                        if (item != null) {
                            userItemsIn.add(item)
                        } else {
                            Log.d(ContentValues.TAG, "item can not be deserialized!")
                        }
                    }

                    setValue(userItemsIn)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
                }
            })
    }

    fun subscribeToUsersOnFirebase(setValue: (List<User>) -> Unit) {
        // fill in user items list
        database.getReference("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val usersIn = mutableListOf<User>()

                // Loop through the children of the "items" node and create an Item object for each child
                for (itemSnapshot in dataSnapshot.children) {
                    val user = itemSnapshot.getValue(User::class.java)

                    if (user != null) {
                        usersIn.add(user)
                    } else {
                        Log.d(ContentValues.TAG, "user can not be deserialized!")
                    }
                }

                setValue(usersIn)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
            }
        })
    }
    fun subscribeToUserOnFirebase(setValue: (User?) -> Unit) {
        // fill in user items list
        database.getReference("users").child(getIdOfCurUser()).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                setValue(user)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
            }
        })
    }
    fun subscribeToItemsOnFirebase(setValue: (List<Item>) -> Unit) {
        // fill in user items list
        database.getReference("items").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val itemsIn = mutableListOf<Item>()

                // Loop through the children of the "items" node and create an Item object for each child
                for (itemSnapshot in dataSnapshot.children) {
                    val itemId = itemSnapshot.key.toString()
                    val item = itemSnapshot.getValue(Item::class.java)

                    if (item != null) {
                        itemsIn.add(item)
                    } else {
                        Log.d(ContentValues.TAG, "challenge je null")
                    }
                }

                setValue(itemsIn)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
            }
        })
    }

    fun subscribeToSpecificItem(itemId: String, setValue: (Item?) -> Unit) {
        // fill in user items list
        database.getReference("items").child(itemId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    setValue(dataSnapshot.getValue(Item::class.java))
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
                }
            })
    }
}