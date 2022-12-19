package com.vm.smacompose.presentation.ui.rooms_list
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vm.smacompose.domain.data.DataState
import com.vm.smacompose.domain.data.ProgressBarState
import com.vm.smacompose.domain.model.Room
import com.vm.smacompose.interactors.GetUserDetails
import com.vm.smacompose.presentation.ui.util.DialogQueue
import com.vm.smacompose.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsListViewModel
@Inject
constructor(
    private val getUserDetails: GetUserDetails
) : ViewModel() {
    val rooms: MutableState<List<Room>> = mutableStateOf(ArrayList())
    val loading = mutableStateOf(false)
    val dialogQueue = DialogQueue()

    init {
        onTriggerEvent(RoomsListEvent.GetRoomsEvent)
    }

    fun onTriggerEvent(event: RoomsListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is RoomsListEvent.GetRoomsEvent -> {
                        newSearch()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            } finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
    }
    private fun newSearch() {
        Log.d(TAG, "newSearch: query")
        // New search. Reset the state
        resetSearchState()
        getUserDetails.fetchRoomDetails()
            .onEach { dataState ->
                when(dataState){
                    is DataState.Data ->{
                        rooms.value = dataState.data
                    }
                    is DataState.Loading ->{
                        when(dataState.progressBarState){
                            is ProgressBarState.Idle ->{loading.value =false}
                            is ProgressBarState.Loading ->{loading.value =true}
                        }
                    }
                    is DataState.Response ->{
                        dialogQueue.appendErrorMessage("An Error Occurred", dataState.toString())
                    }
                }
            }.launchIn(viewModelScope)
    }
    /**
     * Called when a new search is executed.
     */
    private fun resetSearchState() {
        rooms.value = listOf()
    }
}
















