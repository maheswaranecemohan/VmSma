package com.vm.smacompose.di
import com.google.gson.GsonBuilder
import com.vm.smacompose.network.UserService
import com.vm.smacompose.network.model.people.PeopleDtoMapper
import com.vm.smacompose.network.model.room.RoomDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://61e947967bc0550017bc61bf.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDtoMapper(): RoomDtoMapper {
        return RoomDtoMapper()
    }

    @Singleton
    @Provides
    fun providePeopleDtoMapper(): PeopleDtoMapper {
        return PeopleDtoMapper()
    }
}