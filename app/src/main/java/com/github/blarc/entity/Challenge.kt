package com.github.blarc.entity

import java.io.Serializable

data class Challenge(
    var description: String = "",
    var name: String = "",
    val challengeCreator: String? = null,
    var type: String = "general",
    var dueTo: String? = null, // timestamp
    var duration: Int? = null,
    var givingItem: Item? = null,
    var acceptingItem: Item? = null,
    var status: String? = null
) : Serializable