package com.vm.smacompose.presentation.navigation

sealed class Screen(
    val route: String,
){
    object PeopleList: Screen("peopleList")

    object RoomsList: Screen("RoomsList")

    object HomePage: Screen("HomePage")
}