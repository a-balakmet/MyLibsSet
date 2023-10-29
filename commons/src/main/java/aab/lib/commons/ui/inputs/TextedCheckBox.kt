package aab.lib.commons.ui.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TextedCheckBox(
    isChecked: Boolean,
    boxShape: Shape,
    checkColor: Color,
    uncheckedColor: Color?,
    onChange: (Boolean) -> Unit,
    text: String,
    textStyle: TextStyle
) {
    Row(
        modifier = Modifier.clickable { onChange(!isChecked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(end = 6.dp)
                .size(20.dp)
                .background(
                    shape = boxShape,
                    color = if (isChecked) checkColor else Color.Transparent
                )
                .border(
                    width = 1.dp,
                    color = if (isChecked) Color.Transparent else uncheckedColor ?: Gray,
                    shape = boxShape
                )
        ) {
            if(isChecked)
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "check",
                    modifier = Modifier.padding(all = 2.dp),
                    tint = Color.White
                )
        }
        Text(text = text, style = textStyle)
    }
}