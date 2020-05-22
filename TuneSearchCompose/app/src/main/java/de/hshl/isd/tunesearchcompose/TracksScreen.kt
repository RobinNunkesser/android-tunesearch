package de.hshl.isd.tunesearchcompose

import android.content.Context
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.unit.dp

@Composable
fun TracksScreen(collections : Map<String, List<TrackViewModel>>) {
    Scaffold(
        topAppBar = {
            TopAppBar(title = { Text("Tracks") },
                navigationIcon = {
                    IconButton(onClick = {
                        Status.currentScreen = Screen.Search()
                    }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
        bodyContent = {
            VerticalScroller {
                Column {
                    collections.forEach { (title, tracks) ->
                        SectionHeader(title = title)
                        tracks.forEach { track ->
                            ItemRow(track)
                        }
                    }
                }
            }

        }
    )
}