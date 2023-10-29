package aab.lib.commons.ui.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun OKDialog(dialogShape: Shape, text: String, textStyle: TextStyle, isOpened: Boolean? = true, onClick: () -> Unit) {

    var dialogOpen by remember { mutableStateOf(isOpened) }

    if (dialogOpen == true) {
        Dialog(onDismissRequest = {
            dialogOpen = false
            onClick()
        }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 32.dp)
                    .wrapContentHeight(),
                shape = dialogShape,
                color = Color.White
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.padding(all = 16.dp),
                        textAlign = TextAlign.Center,
                        style = textStyle,
                        color = Color.Black

                    )
                    HorizontalDivider(color = Color.Blue)
                    Text(
                        text = "OK",
                        modifier = Modifier
                            .padding(all = 16.dp)
                            .clickable(onClick = {
                                dialogOpen = false
                                onClick()
                            }),
                        style = textStyle,
                        color = Color.Blue
                    )
                }
            }
        }
    }
}