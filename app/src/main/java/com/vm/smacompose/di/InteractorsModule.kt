package com.vm.smacompose.di
import com.vm.smacompose.interactors.GetUserDetails
import com.vm.smacompose.network.UserService
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
    userService: UserService,
    roomDtoMapper: RoomDtoMapper,
    peopleDtoMapper: PeopleDtoMapper,
  ): GetUserDetails {
    return GetUserDetails(
      userService = userService,
      roomDtoMapper = roomDtoMapper,
      peopleDtoMapper = peopleDtoMapper,
    )
  }
}











