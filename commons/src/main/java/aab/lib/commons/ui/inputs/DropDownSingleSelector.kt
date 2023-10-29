package aab.lib.commons.ui.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun <T> DropDownSingleSelector(
    expandedColor: Color? = Color.Red,
    nonExpandedBorderColor: Color? = Color.Gray,
    nonExpandedIconColor: Color? = Color.Gray,
    menuShape: Shape,
    dropDownShape: Shape,
    dropDownHeight: Dp = 200.dp,
    showSearchField: Boolean = true,
    searchHint: String,
    searchStyle: TextStyle,
    modifier: Modifier,
    searchIcon: Int,
    searchIconColor: Color? = Color.Gray,
    trailingIcon: Int?,
    listItems: List<T>,
    selectedItem: T,
    onSelection: (T) -> Unit,
    searchedValue: String,
    onSearch: (String) -> Unit,
    isSinglePane: Boolean = true,
    content: @Composable (T) -> Unit = {},
    additionalView: @Composable () -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val height = configuration.screenHeightDp.dp / 2
    var expanded by remember { mutableStateOf(false) }
    var fieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = modifier
            //.fillMaxWidth()
            .weight(1F)
            .border(
                width = if (expanded) 2.dp else 1.dp,
                color = if (expanded) expandedColor ?: Color.Red else nonExpandedBorderColor ?: Color.Gray,
                shape = menuShape
            )
            .padding(all = 12.dp)
            .clickable { expanded = !expanded }
            .onGloballyPositioned { coordinates ->
                fieldSize = coordinates.size.toSize()
            },
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    fieldSize = coordinates.size.toSize()
                },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1F)) {
                    content(selectedItem)
                }
                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "arrowIcon",
                    tint = if (expanded) expandedColor ?: Color.Red else nonExpandedIconColor ?: Color.Gray
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .height(if (listItems.size > 7) height else dropDownHeight)
                .width(configuration.screenWidthDp.dp / (if (isSinglePane) 1 else 2) - 32.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(1F)
                .width(with(LocalDensity.current) { fieldSize.width.toDp() })) {
                if (showSearchField) {
                    InputTextField(
                        value = searchedValue,
                        onChange = { onSearch(it) },
                        fieldShape = menuShape,
                        hintText = searchHint,
                        textStyle = searchStyle,
                        modifier = modifier,
                        leadingIcon = searchIcon,
                        leadingIconColor = searchIconColor ?: Color.Gray,
                        trailingIcon = trailingIcon,
                        trailingIconColor = searchIconColor,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                    )
                }
                Column(
                    modifier = Modifier
                        .height(if (listItems.size > 7) height - 40.dp else 200.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    listItems.forEach { item ->
                        Box(modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth()
                            .background(
                                shape = dropDownShape,
                                color = if (item == selectedItem) MaterialTheme.colorScheme.primary.copy(alpha = 0.5F) else Color.Transparent
                            )
                            .clickable {
                                expanded = false
                                onSelection(item)
                            }) {
                            content(item)
                        }
                    }
                }
            }
        }
        additionalView()
    }
}