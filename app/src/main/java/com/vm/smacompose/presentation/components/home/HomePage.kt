package com.vm.smacompose.presentation.components.home
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomePage(
    navigate: (String) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                .clickable { navigate("Colleague") }
        ) {
            Text(
                text = "Colleague Details",
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.h3
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                .clickable { navigate("Rooms") }
        ) {
            Text(
                text = "Room Details",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.h3
            )
        }
    }
}



