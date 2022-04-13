package com.vm.smacompose.presentation.ui.people_list
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.vm.smacompose.presentation.components.PeopleList
import com.vm.smacompose.presentation.theme.AppTheme
import com.vm.smacompose.utils.TAG

@Composable
fun PeopleListScreen(
  isDarkTheme: Boolean,
  isNetworkAvailable: Boolean,
  viewModel: PeopleListViewModel,
) {
  Log.d(TAG, "PeopleListScreen: ${viewModel}")

  val people = viewModel.people.value
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
      PeopleList(
        loading = loading,
        people = people
      )
    }
  }
}