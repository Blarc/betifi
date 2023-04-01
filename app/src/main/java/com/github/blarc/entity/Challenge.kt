package com.github.blarc.entity

import java.io.Serializable

data class Challenge(
    val description: String = "",
    val name: String = "",
    val challengeCreator: String = "",
    val type: String = "",
    val dueTo: Int = 0, // timestamp
    val duration: Int = 0,
    val givingItem: Item = Item(),
    val acceptingItem: Item = Item(),
    val status: String = ""
) : Serializable