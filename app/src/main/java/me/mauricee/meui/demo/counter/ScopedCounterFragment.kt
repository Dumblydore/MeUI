package me.mauricee.meui.demo.counter

import android.view.MenuItem
import me.mauricee.meui.demo.R
import me.mauricee.meui.demo.route.AppRoute
import me.mauricee.meui.ext.provideViewModel
import me.mauricee.meui.view.MeViewModel

class ScopedCounterFragment : CounterFragment() {

    override val viewModel: MeViewModel<TestContract.State, TestContract.Action, AppRoute> by provideViewModel()

    override fun renderState(state: TestContract.State) {
        super.renderState(state)
        activity?.title = "Scoped Counter: ${state.id}"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_web -> {
            onRoute(AppRoute.OpenBrowser("https://www.google.com"))
            true
        }
        R.id.action_scoped_fragment -> {
            onRoute(AppRoute.Directions(ScopedCounterFragmentDirections.actionScopedCounterFragmentSelf()))
            true
        }
        R.id.action_shared_fragment -> {
            onRoute(AppRoute.Directions(ScopedCounterFragmentDirections.actionScopedCounterFragmentToCounterFragment()))
            true
        }
        else -> false
    }
}