package aab.lib.commons.ui.bottons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun EnablingButton(
    buttonShape: Shape,
    isEnabled: Boolean,
    onClick: () -> Unit,
    isLoading: Boolean,
    buttonText: String,
    textStyle: TextStyle,
    enabledTextColor: Color? = Color.White,
    enabledButtonColor: Color = MaterialTheme.colorScheme.primary,
    disabledButtonColor: Color?,
    disabledTextColor: Color,
    loadingView: @Composable () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = if (isEnabled) enabledButtonColor else disabledButtonColor ?: enabledButtonColor.copy(alpha = 0.2F),
                shape = buttonShape
            )
            .clickable {
                if (isEnabled) {
                    onClick()
                }
            }
    ) {
        if (isLoading) {
            loadingView()
        } else {
            Text(
                buttonText,
                modifier = Modifier.align(Alignment.Center),
                style = textStyle,
                color = if (isEnabled) enabledTextColor ?: Color.White else disabledTextColor
            )
        }
    }
}