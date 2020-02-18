package me.mauricee.meui.demo.counter

import me.mauricee.meui.demo.route.AppRoute
import me.mauricee.meui.ext.provideViewModel
import me.mauricee.meui.view.MeViewModel

class ScopedCounterFragment : CounterFragment() {

    override val viewModel: MeViewModel<TestContract.State, TestContract.Action, AppRoute> by provideViewModel()

    override fun renderState(state: TestContract.State) {
        super.renderState(state)
        activity?.title = "Scoped Counter: ${state.id}"
    }
}