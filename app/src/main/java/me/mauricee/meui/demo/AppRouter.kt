package me.mauricee.meui.demo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.navigation.Navigation
import me.mauricee.meui.demo.route.AppRoute

class AppRouter(private val activity: Activity) {
    fun onRoute(route: AppRoute): Unit = when (route) {
        is AppRoute.Directions -> Navigation.findNavController(activity, R.id.main_nav)
            .navigate(route.direction, route.navOptions)
        is AppRoute.OpenBrowser -> {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(route.url) }
            activity.startActivity(intent)
        }
    }
}