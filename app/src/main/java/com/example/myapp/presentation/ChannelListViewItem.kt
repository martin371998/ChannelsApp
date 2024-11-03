import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myapp.R
import com.example.myapp.entities.Channel
import com.example.myapp.presentation.VideoPlayer
import kotlinx.coroutines.delay

@Composable
fun ChannelListViewItem(
    channel: Channel,
    isHighlighted: Boolean,
    onGloballyPositioned: (LayoutCoordinates) -> Unit,
    onFullScreenClick: (videoUrl: String, time: Int) -> Unit
) {

    val animatedWidth by animateFloatAsState(
        targetValue = if (isHighlighted) 1f else 1 / 1.2f,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    var playVideo by remember { mutableStateOf(false) }
    LaunchedEffect(isHighlighted) {
        if (isHighlighted) {
            delay(500)
            playVideo = true
        } else {
            playVideo = false
        }
    }
    Column(
        modifier = Modifier
            .padding(bottom = 80.dp)
            .onGloballyPositioned(onGloballyPositioned)
            .fillMaxWidth(animatedWidth),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxSize()
                .aspectRatio(1f),
        ) {
            AsyncImage(
                model = if (playVideo) null else channel.thumbnailUrl.orEmpty(),
                contentDescription = channel.title.orEmpty(),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (playVideo) {
                VideoPlayer(
                    videoUrl = channel.videoUrl.orEmpty(), // Ensure this URL is available
                    modifier = Modifier.fillMaxSize(),
                    onFullScreenClick = onFullScreenClick
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = channel.title.orEmpty(),
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            maxLines = 2
        )
        Text(
            text = channel.description.orEmpty(),
            style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Justify),
            fontWeight = FontWeight.Medium,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}