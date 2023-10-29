package aab.lib.commons.ui.grids

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> GridListView(
    columns: Int,
    scrollState: LazyGridState,
    contentPadding: PaddingValues,
    verticalSpace: Dp? = 16.dp,
    horizontalSpace: Dp? = 16.dp,
    list: List<T>,
    listItem: @Composable (T) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = scrollState,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(verticalSpace ?: 16.dp),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpace ?: 16.dp),
        content = {
            items(list.size) { index ->
                listItem(list[index])
            }
        }
    )
}