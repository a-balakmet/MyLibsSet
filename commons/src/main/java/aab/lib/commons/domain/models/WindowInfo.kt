package aab.lib.commons.domain.models

import androidx.compose.ui.unit.Dp

data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp
) {
    sealed class WindowType {
        data object Compact: WindowType()
        data object Medium: WindowType()
        data object Expanded: WindowType()
    }
}