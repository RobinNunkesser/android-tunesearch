package de.hshl.isd.tunesearchcompose

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppContent()
            }
        }
    }
}

sealed class Screen {
    class Search() : Screen()
    data class Tracks(val collections: List<CollectionViewModel>) : Screen()
}

@Model
object Status  {
    var currentScreen : Screen = Screen.Search()
}

@Composable
private fun AppContent() {
    Crossfade(Status.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Search -> SearchScreen()
                is Screen.Tracks -> TracksScreen(screen.collections)
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        AppContent()
    }
}