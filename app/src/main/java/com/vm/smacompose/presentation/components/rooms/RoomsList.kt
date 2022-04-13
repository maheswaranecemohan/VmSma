package com.vm.smacompose.presentation.components.rooms
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.smacompose.domain.model.People
import com.vm.smacompose.domain.model.Room
import com.vm.smacompose.presentation.components.LoadingPeopleListShimmer
import com.vm.smacompose.presentation.components.NothingHere
import com.vm.smacompose.presentation.navigation.Screen

@Composable
fun RoomsList(
    loading: Boolean,
    rooms: List<Room>
){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && rooms.isEmpty()) {
            LoadingPeopleListShimmer(imageHeight = 250.dp,)
        }
        else if(rooms.isEmpty()){
            NothingHere()
        }
        else {
            LazyColumn{
                itemsIndexed(
                    items = rooms
                ) { index, rooms ->
                    RoomsCard(
                        room = rooms,
                        onClick = {
                        }
                    )
                }
            }
        }
    }
}







