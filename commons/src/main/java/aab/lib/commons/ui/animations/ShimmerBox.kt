package aab.lib.commons.ui.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerBox(
    modifier: Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    colors: List<Color> = listOf(
        Color(0xFFBDBCBC),
        Color(0xFFE2E0E0),
        Color(0xFFBDBCBC)
    )
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = shape,
                brush = colors.shimmerBrush()
            ),
    )
}

@Preview
@Composable
fun ShimmerBoxPreview(){
    Box(modifier = Modifier.size(120.dp)) {
        ShimmerBox(modifier = Modifier.fillMaxSize())
    }
}
