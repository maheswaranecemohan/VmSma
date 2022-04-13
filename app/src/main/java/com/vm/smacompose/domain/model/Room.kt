package com.vm.smacompose.domain.model

data class Room(
    val createdAt: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int,
    val id: String
)