package me.mauricee.meui.demo.masterDetail

interface MasterDetailContract {
    data class State(
        val numbers: List<Int> = emptyList(),
        val selectedNumber: Int? = null
    )

    open class Action

    sealed class Route {
        object ToDetail : Route()
    }
}