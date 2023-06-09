package com.github.blarc.entity

import java.io.Serializable

data class User(
    var userId: String = "",
    var level: Int = 1,
    var equipment: List<Item> = mutableListOf(),
    var items: List<Item> = mutableListOf(),
    var challenges: List<Challenge> = mutableListOf()
) : Serializable