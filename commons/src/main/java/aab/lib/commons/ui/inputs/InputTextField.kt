package aab.lib.commons.ui.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun InputTextField(
    value: String,
    onChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
    fieldShape: Shape,
    hintText: String,
    textStyle: TextStyle,
    hintColor: Color = Gray,
    modifier: Modifier,
    leadingIcon: Int? = null,
    leadingIconColor: Color? = null,
    trailingIcon: Int? = null,
    trailingIconColor: Color? = null,
    focusedBorderColor: Color,
    unfocusedBorderColor: Color,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    showIcons: Boolean = true,
    showTrailing: Boolean = false,
    withLabel: Boolean = false,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val leadingIconView = @Composable {
        if (leadingIcon != null)
        Icon(
            painter = painterResource(id = leadingIcon),
            contentDescription = "icon_$leadingIcon",
            tint = leadingIconColor ?: Gray
        ) else {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search")
        }
    }

    val trailingIconView = @Composable {
        if (trailingIcon != null)
            Icon(
                painter = painterResource(id = trailingIcon),
                contentDescription = "icon_$trailingIcon",
                modifier = Modifier.clickable { onChange("") },
                tint = trailingIconColor ?: Gray
            ) else {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")
        }
    }

    val labelView = @Composable {
        Text(text = hintText)
    }

    OutlinedTextField(
        value = value,
        onValueChange = { onChange(it) },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        shape = fieldShape,
        modifier = modifier.fillMaxWidth(),
        maxLines = 1,
        label = if (withLabel) labelView else null,
        placeholder = {
            Text(
                text = hintText,
                style = textStyle,
                color = hintColor,
                maxLines = 1,
            )
        },
        leadingIcon = if (showIcons) leadingIconView else null,
        trailingIcon = if (showIcons || showTrailing) trailingIconView else null,
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Unspecified,
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
        ),
        textStyle = textStyle
    )
}