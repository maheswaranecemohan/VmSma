package com.vm.smacompose.presentation.ui
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.vm.smacompose.presentation.components.home.HomePage
import com.vm.smacompose.presentation.theme.AppTheme

@Composable
fun HomeScreen(
  isDarkTheme: Boolean,
  navigate: (String) -> Unit,
) {
  val scaffoldState = rememberScaffoldState()
  AppTheme(
    displayProgressBar = false,
    scaffoldState = scaffoldState,
    darkTheme = isDarkTheme,
    isNetworkAvailable = true,
    dialogQueue = null,
  ) {
    Scaffold(
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
    ) {
      HomePage(
        navigate = navigate
      )
    }
  }
}