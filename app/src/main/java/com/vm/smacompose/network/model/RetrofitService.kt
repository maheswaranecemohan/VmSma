package com.vm.smacompose.network

import com.vm.smacompose.network.model.PeopleDto
import com.vm.smacompose.network.model.room.RoomDto
import retrofit2.http.GET

interface PeopleService {
    @GET("people")
    suspend fun get(): List<PeopleDto>
}

interface RoomService {
    @GET("rooms")
    suspend fun get(): List<RoomDto>
}











