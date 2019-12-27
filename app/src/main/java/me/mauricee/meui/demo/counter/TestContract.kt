package me.mauricee.meui.demo.counter

interface TestContract {
    data class State(val id: Int = ++initialStateId, val number: Int = 0, val timer: Int = 5)
    sealed class Action {
        object NextNumber : Action()
    }

    companion object {
        private var initialStateId: Int = 0
    }
}