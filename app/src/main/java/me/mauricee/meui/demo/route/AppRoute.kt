package me.mauricee.meui.demo.route

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class AppRoute {
    data class Directions(val direction: NavDirections, val navOptions: NavOptions? = null) : AppRoute()
    data class OpenBrowser(val url: String) : AppRoute()
}