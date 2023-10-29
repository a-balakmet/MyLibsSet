package aab.lib.commons.ui.navigation

import android.os.Parcelable
import androidx.navigation.NavOptions

interface NavigationAction {
    val destination: String
    val bottomIndex: Int?
        get() = null
    val parcelableArguments: Map<String, Parcelable>
        get() = emptyMap() // No parcelable arguments as default
    val navOptions: NavOptions
        get() = NavOptions.Builder().build()
}