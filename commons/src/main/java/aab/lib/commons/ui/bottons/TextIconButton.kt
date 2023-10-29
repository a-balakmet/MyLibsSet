package aab.lib.commons.ui.bottons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class IconPosition {
    LEFT, RIGHT
}

@Composable
fun TextAndIconButton(
    modifier: Modifier,
    onClick: () -> Unit,
    buttonColor: Color = Color.Transparent,
    buttonShape: Shape,
    inButtonVerticalPadding: Dp,
    inButtonHorizontalPadding: Dp,
    iconPosition: IconPosition = IconPosition.RIGHT,
    icon: Int? = null,
    iconSize: Dp? = null,
    contentColor: Color,
    space: Dp? = null,
    buttonText: String,
    textStyle: TextStyle,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .background(color = buttonColor, shape = buttonShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(vertical = inButtonVerticalPadding, horizontal = inButtonHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (iconPosition == IconPosition.LEFT && icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(iconSize ?: 24.dp)
                        .padding(end = space ?: 16.dp),
                    tint = contentColor
                )
            }
            Text(
                text = buttonText,
                style = textStyle,
                color = contentColor
            )
            if (iconPosition == IconPosition.RIGHT && icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(iconSize ?: 24.dp)
                        .padding(start = space ?: 16.dp),
                    tint = contentColor
                )
            }
        }
    }
}