package me.mauricee.meui.demo.counter

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.fragment_counter.view.*
import me.mauricee.meui.demo.AppRouter
import me.mauricee.meui.demo.MockService
import me.mauricee.meui.demo.R
import me.mauricee.meui.demo.route.AppRoute
import me.mauricee.meui.ext.provideSharedViewModel
import me.mauricee.meui.view.MeFragment
import me.mauricee.meui.view.MeViewModel

open class CounterFragment :
    MeFragment<TestContract.State, TestContract.Action, AppRoute>(R.layout.fragment_counter) {

    private val router: AppRouter by lazy { AppRouter(requireActivity()) }
    override val viewModel: MeViewModel<TestContract.State, TestContract.Action, AppRoute> by provideSharedViewModel()
    override val presenter: CounterPresenter by lazy { CounterPresenter(MockService()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.counter.setOnClickListener { sendAction(TestContract.Action.NextNumber) }
    }

    override fun renderState(state: TestContract.State) {
        activity?.title = "Shared Counter: ${state.id}"
        requireView().apply {
            timer.text = "${state.timer}"
            counter.text = "${state.number}"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_web -> {
            onRoute(AppRoute.OpenBrowser("https://www.google.com"))
            true
        }
        R.id.action_scoped_fragment -> {
            onRoute(AppRoute.Directions(CounterFragmentDirections.actionCounterFragmentToScopedCounterFragment()))
            true
        }
        R.id.action_shared_fragment -> {
            onRoute(AppRoute.Directions(CounterFragmentDirections.actionCounterFragmentSelf()))
            true
        }
        else -> false
    }

    override fun onRoute(route: AppRoute) = router.onRoute(route)
}