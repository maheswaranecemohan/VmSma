package com.vm.smacompose.interactors

import com.vm.smacompose.domain.data.DataState
import com.vm.smacompose.domain.model.People
import com.vm.smacompose.network.PeopleService
import com.vm.smacompose.network.RoomService
import com.vm.smacompose.network.model.people.PeopleDtoMapper
import com.vm.smacompose.network.model.room.RoomDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetUserDetails(
    private val roomService: RoomService,
    private val peopleService: PeopleService,
    private val roomDtoMapper: RoomDtoMapper,
    private val peopleDtoMapper: PeopleDtoMapper
) {

    fun execute(): Flow<DataState<List<People>>> = flow {
        try {
            emit(DataState.loading())
            delay(1000)
            var people = getPeoplefromNetwork()
            if (people != null) {
                emit(DataState.success(people))
            }
        } catch (e: Exception) {
            emit(DataState.error<List<People>>(e.message?: "Unknown Error"))
        }
    }

    suspend fun getPeoplefromNetwork(): List<People> {
        return peopleDtoMapper.toDomainList(peopleService.get())
    }

}