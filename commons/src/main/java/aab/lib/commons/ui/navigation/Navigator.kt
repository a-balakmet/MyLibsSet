package aab.lib.commons.ui.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.StateFlow

interface Navigator {
    val navigationActions : StateFlow<NavigationAction?>
    fun setController(controller: NavHostController)
    fun getController() : NavHostController?
    fun navigate(navigationAction: NavigationAction?, bottomIndex: Int?)
}
