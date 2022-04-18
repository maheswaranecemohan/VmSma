package com.vm.smacompose.interactors
import com.vm.smacompose.domain.data.DataState
import com.vm.smacompose.domain.model.People
import com.vm.smacompose.domain.model.Room
import com.vm.smacompose.network.UserService
import com.vm.smacompose.network.model.people.PeopleDtoMapper
import com.vm.smacompose.network.model.room.RoomDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserDetails(
    private val userService: UserService,
    private val roomDtoMapper: RoomDtoMapper,
    private val peopleDtoMapper: PeopleDtoMapper
) {

    fun fetchPeopleDetails(): Flow<DataState<List<People>>> = flow {
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
        return peopleDtoMapper.toDomainList(userService.getPeople())
    }

    fun fetchRoomDetails(): Flow<DataState<List<Room>>> = flow {
        try {
            emit(DataState.loading())
            delay(1000)
            var rooms = getRoomsfromNetwork()
            if (rooms != null) {
                emit(DataState.success(rooms))
            }
        } catch (e: Exception) {
            emit(DataState.error<List<Room>>(e.message?: "Unknown Error"))
        }
    }

    suspend fun getRoomsfromNetwork(): List<Room> {
        return roomDtoMapper.toDomainList(userService.getRooms())
    }

}