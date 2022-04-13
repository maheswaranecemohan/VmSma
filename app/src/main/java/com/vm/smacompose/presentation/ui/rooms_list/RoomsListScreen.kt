package com.vm.smacompose.presentation.ui.rooms_list
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.vm.smacompose.presentation.components.rooms.RoomsList
import com.vm.smacompose.presentation.theme.AppTheme
import com.vm.smacompose.utils.TAG

@Composable
fun RoomsListScreen(
  isDarkTheme: Boolean,
  isNetworkAvailable: Boolean,
  viewModel: RoomsListViewModel,
) {
  Log.d(TAG, "PeopleListScreen: ${viewModel}")
  val rooms = viewModel.rooms.value
  val loading = viewModel.loading.value
  val dialogQueue = viewModel.dialogQueue
  val scaffoldState = rememberScaffoldState()

  AppTheme(
    displayProgressBar = loading,
    scaffoldState = scaffoldState,
    darkTheme = isDarkTheme,
    isNetworkAvailable = isNetworkAvailable,
    dialogQueue = dialogQueue.queue.value,
  ) {
    Scaffold(
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
    ) {
      RoomsList(
        loading = loading,
        rooms = rooms
      )
    }
  }
}