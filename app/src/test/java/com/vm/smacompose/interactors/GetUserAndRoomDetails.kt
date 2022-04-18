package com.vm.smacompose.interactors
import com.google.gson.GsonBuilder
import com.vm.smacompose.domain.model.People
import com.vm.smacompose.domain.model.Room
import com.vm.smacompose.network.UserService
import com.vm.smacompose.network.data.MockServiceResponses
import com.vm.smacompose.network.model.people.PeopleDtoMapper
import com.vm.smacompose.network.model.room.RoomDtoMapper
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class GetUserAndRoomDetails {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    // system in test
    private lateinit var getUserDetails: GetUserDetails
    // Dependencies
    private lateinit var userService: UserService
    private  val roomDtoMapper = RoomDtoMapper()
    private  val peopleDtoMapper =  PeopleDtoMapper()

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("mockapi.io/api/v1/")
        userService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(UserService::class.java)

        // instantiate the system in test
        getUserDetails = GetUserDetails(
            userService = userService,
            roomDtoMapper = roomDtoMapper,
            peopleDtoMapper = peopleDtoMapper,
        )
    }
    /**
     * 1. Get people and rooms from the network
     */
    @Test
    fun getPeopleFromNetwork(): Unit = runBlocking {
        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockServiceResponses.peopleResponses)
        )
        val getPeopleAsFlow = getUserDetails.fetchPeopleDetails().toList()

        // first emission should be `loading`
        assert(getPeopleAsFlow[0].loading)

        // second emission should be the people
        val people = getPeopleAsFlow[1].data
        assert(people?.get(0)?.firstName.equals("Maggie"))

        // confirm it is actually a Recipe object
        assert(people?.get(0) is People)

        // 'loading' should be false now
        assert(!getPeopleAsFlow[1].loading)
    }
    /**
     * 1. Get Rooms and rooms from the network
     */
    @Test
    fun getRoomsFromNetwork(): Unit = runBlocking {
        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockServiceResponses.peopleResponses)
        )
        val getRoomsAsFlow = getUserDetails.fetchRoomDetails().toList()

        // first emission should be `loading`
        assert(getRoomsAsFlow[0].loading)

        // second emission should be the people
        val rooms = getRoomsAsFlow[1].data
        assert(rooms?.get(0)?.id.equals("1"))

        // confirm it is actually a Recipe object
        assert(rooms?.get(0) is Room)

        // 'loading' should be false now
        assert(!getRoomsAsFlow[1].loading)
    }
    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }
}