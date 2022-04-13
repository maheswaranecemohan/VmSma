package com.vm.smacompose.di
import com.vm.smacompose.interactors.GetUserDetails
import com.vm.smacompose.network.PeopleService
import com.vm.smacompose.network.RoomService
import com.vm.smacompose.network.model.people.PeopleDtoMapper
import com.vm.smacompose.network.model.room.RoomDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {
  @ViewModelScoped
  @Provides
  fun provideGetUserDetails(
    roomService: RoomService,
    peopleService: PeopleService,
    roomDtoMapper: RoomDtoMapper,
    peopleDtoMapper: PeopleDtoMapper,
  ): GetUserDetails {
    return GetUserDetails(
      roomService = roomService,
      peopleService = peopleService,
      roomDtoMapper = roomDtoMapper,
      peopleDtoMapper = peopleDtoMapper,
    )
  }
}











