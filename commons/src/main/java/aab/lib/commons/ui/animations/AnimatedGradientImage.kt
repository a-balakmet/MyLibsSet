package aab.lib.commons.ui.animations

import androidx.compose.animation.core.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource

@Composable
fun AnimatedGradientImage(
    modifier: Modifier,
    colors:  List<Color>,
    imageId: Int,
    isBig: Boolean
) {
    val brush = colors.toAnimatedBrush(isBig)
    Icon(
        painter = painterResource(id = imageId),
        contentDescription = "image",
        modifier = modifier.drawWithContent {
            with(drawContext.canvas.nativeCanvas) {
                val checkPoint = saveLayer(null, null)
                drawContent()
                drawRect(
                    brush = brush,
                    blendMode = BlendMode.SrcIn
                )
                restoreToCount(checkPoint)
            }
        }
    )
}

@Composable
fun List<Color>.toAnimatedBrush(isBig: Boolean): Brush {
    val transition = rememberInfiniteTransition(label = "transition")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = if (isBig) 1000F else 100F,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000, easing = EaseIn),
            RepeatMode.Reverse
        ), label = "animation"
    )
    return Brush.linearGradient(
        colors = this,
        start = Offset(x = if (isBig) 1000F else 100F, y= 0f),
        end = Offset(translateAnim, translateAnim)
    )
}

@Composable
fun List<Color>.shimmerBrush(reversed: Boolean = false): Brush {
    val transition = rememberInfiniteTransition(label = "transition")
    val translateAnim by transition.animateFloat(
        initialValue = if (reversed) 2000f else 0f,
        targetValue = if (reversed) 0f else 2000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000, easing = FastOutLinearInEasing),
            RepeatMode.Restart
        ), label = "transition"
    )
    return Brush.linearGradient(
        colors = this,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
}