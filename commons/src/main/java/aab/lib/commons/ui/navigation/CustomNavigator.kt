package aab.lib.commons.ui.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * This navigator is based on this example:
 * https://patrykkosieradzki.com/how-to-make-jetpack-compose-navigation-easier-and-testable/
**/

class CustomNavigator : Navigator {

    private var navController: NavHostController? = null

    private val navActions: MutableStateFlow<NavigationAction?> by lazy {
        MutableStateFlow(null)
    }

    private val bottomItem: MutableStateFlow<Int?> by lazy {
        MutableStateFlow(null)
    }

    override fun setController(controller: NavHostController) {
        if (navController == null) {
            navController = controller
        }
    }

    override fun getController(): NavHostController? {
        return navController
    }

    override val navigationActions: StateFlow<NavigationAction?> = navActions.asStateFlow()

    override fun navigate(navigationAction: NavigationAction?, bottomIndex: Int?) {
        bottomItem.update {
            bottomIndex
        }
        navActions.update {
            navigationAction
        }
    }
}