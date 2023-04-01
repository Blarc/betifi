package com.github.blarc.entity

import java.io.Serializable

data class Challenge(
    val description: String = "",
    val name: String = "",
    val challengeCreator: String? = null,
    val type: String = "general",
    val dueTo: Int? = null, // timestamp
    val duration: Int? = null,
    val givingItem: Item? = null,
    val acceptingItem: Item? = null,
    val status: String? = null
) : Serializable