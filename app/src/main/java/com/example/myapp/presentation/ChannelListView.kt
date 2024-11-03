package com.example.myapp.presentation

import ChannelListViewItem
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.myapp.entities.Channel

@Composable
fun ChannelListView(
    channels: List<Channel>,
    onFullScreenClick: (videoUrl: String, time: Int) -> Unit
) {
    val visibleIndex = remember { mutableIntStateOf(-1) }
    val localConfiguration = LocalConfiguration.current
    val localDensity = LocalDensity.current

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(channels.size) { index ->
            ChannelListViewItem(
                channel = channels[index],
                isHighlighted = index == visibleIndex.intValue,
                onGloballyPositioned = { coordinates ->
                    val position = coordinates.positionInRoot()
                    val height = coordinates.size.height
                    val screenHeight =
                        with(localDensity) { localConfiguration.screenHeightDp.dp.toPx() }
                    if (position.y >= 0 && position.y + height <= screenHeight) {
                        visibleIndex.intValue = index
                    }
                },
                onFullScreenClick = onFullScreenClick
            )
        }
    }
}