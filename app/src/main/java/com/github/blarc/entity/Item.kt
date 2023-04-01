package com.github.blarc.entity

import java.io.Serializable

data class Item(
    val name: String = "",
    val type: String = "",
    val iconRef: String = "",
    val staked: Boolean = false,
    val canBeStaked: Boolean = false
) : Serializable