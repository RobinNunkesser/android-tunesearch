package de.hshl.isd.tunesearchcompose

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp

@Composable
fun ItemRow(item: TrackViewModel) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = item.title,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.body1
        )
        Text(
            text = item.subtitle,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.caption
        )
        Divider(
            Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
    }
}