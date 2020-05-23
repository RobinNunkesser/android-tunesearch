package de.hshl.isd.tunesearchcompose

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp

@Composable
fun TrackRow(item: TrackViewModel) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(modifier = Modifier.padding(top = 4.dp)) {
            NetworkImageComponentPicasso(
                item.image, modifier = Modifier.preferredHeightIn(maxHeight = 200.dp) +
                        Modifier.preferredWidthIn(maxWidth = 200.dp) + Modifier.padding(end = 4.dp)
            )
            Column {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = item.subtitle,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Divider(
            Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
    }
}
