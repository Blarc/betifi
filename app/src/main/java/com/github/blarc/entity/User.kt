package com.github.blarc.entity

import java.io.Serializable

data class User(
    var items: List<Item> = mutableListOf(),
    var challenges: List<Challenge> = mutableListOf()
) : Serializable