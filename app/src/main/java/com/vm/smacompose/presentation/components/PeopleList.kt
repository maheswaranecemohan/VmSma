package com.vm.smacompose.presentation.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.smacompose.domain.model.People
@Composable
fun PeopleList(
    loading: Boolean,
    people: List<People>
){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && people.isEmpty()) {
            LoadingPeopleListShimmer(imageHeight = 250.dp,)
        }
        else if(people.isEmpty()){
            NothingHere()
        }
        else {
            LazyColumn{
                itemsIndexed(
                    items = people
                ) { index, people ->
                    PeopleCard(
                        people = people,
                        onClick = {
                        }
                    )
                }
            }
        }
    }
}







