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

@Composable
fun SearchScreen() {
    val interactor = SearchInteractor(
        TrackListPresenter(),
        ITunesSearchGateway()
    )

    val displayer = object : Displayer<Map<String, List<TrackViewModel>>> {
        override fun display(success: Map<String, List<TrackViewModel>>, requestCode: Int) {
            Status.currentScreen = Screen.Tracks(success)
        }

        override fun display(error: Throwable) {
            Log.e("SearchScreen",error.localizedMessage)
        }

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
            Column(verticalArrangement = Arrangement.Center) {
                val searchTermTextField = state { TextFieldValue("Jack Johnson") }
                TextField(value = searchTermTextField.value,
                    modifier = Modifier.padding(8.dp),
                    onValueChange = {
                        searchTermTextField.value = it
                    })
                Button(onClick = {
                    interactor.execute(SearchRequest(searchTermTextField.value.text),displayer)
                }) {
                    Text("Search")
                }
            }
        }
    )
}