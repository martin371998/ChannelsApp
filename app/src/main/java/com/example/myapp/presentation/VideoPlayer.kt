package com.example.myapp.presentation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.myapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f)
        .background(Color.Black),
    onFullScreenClick: (videoUrl: String, time: Int) -> Unit
) {
    val context = LocalContext.current
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            setMediaItem(mediaItem)
            prepare()
        }
    }
    val isMuted = remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            player.playWhenReady = true
            coroutineScope.launch {
                while (isVisible) {
                    delay(1000)
                    val currentPosition = player.currentPosition
                    player.volume = if (isMuted.value) 0f else 1f
                    if (currentPosition >= 10000) {
                        isMuted.value = true
                        break
                    }
                }
            }
        } else {
            player.playWhenReady = false
        }
    }

    Box {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    this.player = player
                    useController = false
                }
            },
            modifier = modifier.onGloballyPositioned { layoutCoordinates ->
                isVisible = layoutCoordinates.isAttached
            },
            update = { playerView ->
                playerView.player = player
            }
        )
        Icon(
            painter = painterResource(id = if (isMuted.value) R.drawable.ic_unmute else R.drawable.ic_mute),
            contentDescription = "Mute",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 24.dp)
                .size(24.dp)
                .clickable {
                    isMuted.value = isMuted.value.not()
                }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_fullscreen),
            contentDescription = "Fullscreen",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 24.dp)
                .size(24.dp)
                .clickable {
                    onFullScreenClick(videoUrl, player.currentPosition.toInt())
                }
        )
    }
}