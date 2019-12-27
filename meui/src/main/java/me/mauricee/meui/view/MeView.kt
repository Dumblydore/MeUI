package me.mauricee.meui.view

import io.reactivex.Flowable

interface MeView<Action, Route>  {
    val actions: Flowable<Action>
    fun routeTo(route: Route)
}