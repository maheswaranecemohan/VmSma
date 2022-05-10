package com.vm.smacompose.network.model

import com.google.gson.annotations.SerializedName
    data class PeopleDto(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("firstName")
        val firstName: String,
        @SerializedName("avatar")
        val avatar: String,
        @SerializedName("lastName")
        val lastName: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("jobtitle")
        val jobtitle: String,
        @SerializedName("favouriteColor")
        val favouriteColor: String,
        @SerializedName("id")
        val id: String
    )

