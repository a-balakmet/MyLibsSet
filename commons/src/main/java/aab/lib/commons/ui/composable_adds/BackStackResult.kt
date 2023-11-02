package aab.lib.commons.ui.composable_adds

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun <T> NavController.BackStackResult(keyResult: String, onResult: (T) -> Unit){
    val valueScreenResult = currentBackStackEntry
        ?.savedStateHandle
        ?.get<T>(keyResult)

    valueScreenResult?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}