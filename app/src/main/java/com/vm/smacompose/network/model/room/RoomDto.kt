package com.vm.smacompose.network.model.room
import com.google.gson.annotations.SerializedName
    data class RoomDto(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("isOccupied")
        val isOccupied: Boolean,
        @SerializedName("maxOccupancy")
        val maxOccupancy: Int,
        @SerializedName("id")
        val id: String
    )
