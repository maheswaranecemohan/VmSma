package com.vm.smacompose.network

import com.vm.smacompose.network.model.PeopleDto
import com.vm.smacompose.network.model.room.RoomDto
import retrofit2.http.GET

interface UserService {
    @GET("people")
    suspend fun getPeople(): List<PeopleDto>

    @GET("rooms")
    suspend fun getRooms():List<RoomDto>
}











