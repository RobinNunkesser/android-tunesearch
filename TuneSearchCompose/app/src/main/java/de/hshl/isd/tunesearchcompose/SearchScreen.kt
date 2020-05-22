package de.hshl.isd.tunesearchcompose

import android.content.Context
import android.content.Intent
import androidx.compose.Composable
import androidx.core.content.ContextCompat.startActivity
import androidx.ui.animation.animate
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.drawOpacity
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
fun SearchScreen() {
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
            Button(onClick = {
                Status.currentScreen = Screen.Tracks()
            }) {
                Text("Tracks")
            }
        }
    )
}