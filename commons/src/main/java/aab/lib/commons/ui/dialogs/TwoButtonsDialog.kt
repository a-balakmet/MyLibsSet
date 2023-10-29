package aab.lib.commons.ui.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TwoButtonsDialog(
    dialogShape: Shape = RoundedCornerShape(8.dp),
    titleText: String? = null,
    messageText: String,
    titleStyle: TextStyle = TextStyle.Default,
    messageStyle: TextStyle = TextStyle.Default,
    leftButton: String,
    rightButton: String,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit,
) {
    Dialog(onDismissRequest = {
        onClickLeft()
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
                titleText?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
                        style = titleStyle,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = messageText,
                    modifier = Modifier.padding(start = 16.dp, top = if (titleText == null ) 16.dp else 0.dp, end = 16.dp, bottom = 16.dp).align(Alignment.CenterHorizontally),
                    style = messageStyle,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider(color = Color.Blue)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = leftButton,
                        modifier = Modifier.clickable {
                            onClickLeft()
                        },
                        style = titleStyle,
                        color = Color.Blue
                    )
                    Text(
                        text = rightButton,
                        modifier = Modifier.clickable {
                            onClickRight()
                            onClickLeft()
                        },
                        style = titleStyle,
                        color = Color.Blue
                    )
                }

            }
        }
    }
}