package de.hshl.isd.tunesearchcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import de.hshl.isd.tunesearchcompose.ui.theme.TuneSearchComposeTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent(viewModel = mainViewModel)
        }
    }
}

@Composable
fun MainContent(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavigationHost(navController,viewModel)
}