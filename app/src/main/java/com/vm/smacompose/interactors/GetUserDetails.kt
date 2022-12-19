package com.vm.smacompose.interactors
import com.vm.smacompose.domain.data.DataState
import com.vm.smacompose.domain.data.ProgressBarState
import com.vm.smacompose.domain.model.People
import com.vm.smacompose.domain.model.Room
import com.vm.smacompose.network.UserService
import com.vm.smacompose.network.handleUseCaseException
import com.vm.smacompose.network.model.people.PeopleDtoMapper
import com.vm.smacompose.network.model.room.RoomDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
class GetUserDetails(
    private val userService: UserService,
    private val roomDtoMapper: RoomDtoMapper,
    private val peopleDtoMapper: PeopleDtoMapper
) {

    fun fetchPeopleDetails(): Flow<DataState<List<People>>> = flow {
            //emit(DataState.loading())
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            delay(1000)
            var people = peopleDtoMapper.toDomainList(userService.getPeople())
                //emit(DataState.success(people))
                emit(DataState.Data(people))

        } .catch { e ->
            emit(handleUseCaseException(e))
        }.onCompletion {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    fun fetchRoomDetails(): Flow<DataState<List<Room>>> = flow {
            //emit(DataState.loading())
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            delay(1000)
            var rooms = roomDtoMapper.toDomainList(userService.getRooms())
                //emit(DataState.success(rooms))
                emit(DataState.Data(rooms))

        } .catch { e ->
            emit(handleUseCaseException(e))
        }.onCompletion {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
}