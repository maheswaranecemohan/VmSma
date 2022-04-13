package com.vm.smacompose.presentation.components.rooms
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.vm.smacompose.domain.model.Room

@Composable
fun RoomsCard(
    room: Room,
    onClick: () -> Unit,
) {

    val status = if(room.isOccupied){
        "Occupied"
    }else{
        "Not Occupied"
    }

    Card(

        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(R.color.androidx_core_ripple_material_light))
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
            ) {
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = "Room ID - "+room.id,
                        modifier = Modifier
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3
                    )

                    Text(
                        text = "Status - "+status,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )

                    val maxOccupency = room.maxOccupancy.toString()
                    Text(
                        text = "Maximum Occupancy - "+maxOccupency,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}


