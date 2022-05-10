package com.vm.smacompose.presentation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.vm.smacompose.datastore.SettingsDataStore
import com.vm.smacompose.presentation.navigation.Screen
import com.vm.smacompose.presentation.ui.HomeScreen
import com.vm.smacompose.presentation.ui.people_list.PeopleListScreen
import com.vm.smacompose.presentation.ui.people_list.PeopleListViewModel
import com.vm.smacompose.presentation.ui.rooms_list.RoomsListScreen
import com.vm.smacompose.presentation.ui.rooms_list.RoomsListViewModel
import com.vm.smacompose.presentation.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.HomePage.route) {

                composable(route = Screen.HomePage.route) { navBackStackEntry ->
                    HomeScreen(isDarkTheme = settingsDataStore.isDark.value, navigate = { type ->
                        if(type.equals("Colleague"))
                        {
                            navController.navigate(Screen.PeopleList.route)
                        }
                        else{
                            navController.navigate(Screen.RoomsList.route)
                        }
                    })
                }

                composable(route = Screen.PeopleList.route) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: PeopleListViewModel = viewModel("PeopleListViewModel", factory)
                    PeopleListScreen(
                        isDarkTheme = settingsDataStore.isDark.value,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        viewModel = viewModel,
                    )
                }

                composable(route = Screen.RoomsList.route) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: RoomsListViewModel = viewModel("RoomsListViewModel", factory)
                    RoomsListScreen(
                        isDarkTheme = settingsDataStore.isDark.value,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        viewModel = viewModel,
                    )
                }



            }
        }
    }

}

