package de.hshl.isd.tunesearchcompose

import android.util.Log
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.foundation.TextFieldValue
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.unit.dp
import de.hshl.isd.basiccleanarch.Displayer
import de.hshl.isd.tunesearchcompose.core.MockSearchTracksCommand
import de.hshl.isd.tunesearchcompose.core.ports.CollectionEntity
import de.hshl.isd.tunesearchcompose.core.ports.SearchTracksDTO

@Composable
fun SearchScreen() {
    val service = MockSearchTracksCommand()

    fun success(collections: List<CollectionEntity>) {
        val viewModel = collections.map { collection ->
            CollectionViewModel(name = collection.name,
            tracks = collection.tracks.map { track -> TrackViewModel(track.artistName, track.artworkUrl, "${track.trackNumber} - ${track.trackName}") }) }
        Status.currentScreen = Screen.Tracks(viewModel)
    }

    fun failure(error: Throwable) {
        Log.e("SearchScreen",error.localizedMessage)
    }

    Scaffold(
        topAppBar = {
            TopAppBar(title = { Text("Search") }/*,
                actions =
                {
                    Button(onClick = {
                        startActivity(context,
                            Intent(context,
                                OssLicensesMenuActivity::class.java),null
                        )
                    }) {
                        Text("Statistics")
                    }
                }*/
            )
        },
        bodyContent = {
            Column(Modifier.fillMaxSize().padding(8.dp), verticalArrangement = Arrangement.Center) {
            val searchTermTextField = state { TextFieldValue("Jack Johnson") }
                TextField(value = searchTermTextField.value,
                    modifier = Modifier.padding(8.dp),
                    onValueChange = {
                        searchTermTextField.value = it
                    })
                Button(onClick = {
                    service.execute(
                        SearchTracksDTO(searchTermTextField.value.text),
                        ::success,
                        ::failure)
                }) {
                    Text("Search")
                }
            }
        }
    )
}