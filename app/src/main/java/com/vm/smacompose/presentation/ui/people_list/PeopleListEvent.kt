package com.vm.smacompose.presentation.ui.people_list

sealed class PeopleListEvent {
    object GetPeopleEvent : PeopleListEvent()
}