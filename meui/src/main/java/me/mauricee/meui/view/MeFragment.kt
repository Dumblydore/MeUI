package me.mauricee.meui.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import me.mauricee.meui.ext.provideViewModel

abstract class MeFragment<State, Action, Route>(@LayoutRes layoutId: Int = 0) :
    Fragment(layoutId) {

    val subscriptions = CompositeDisposable()
    abstract val presenter: MePresenter<State, Action, Route>
    open val viewModel by provideViewModel { MeViewModel<State, Action, Route>() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.routes.observe(this, Observer(::onRoute))
        viewModel.state.observe(this, Observer(::renderState))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscriptions += viewModel.addPresenter(presenter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscriptions.clear()
    }

    open fun onRoute(route: Route) {

    }

    open fun renderState(state: State) {

    }

    protected fun sendAction(action: Action) = viewModel.sendAction(action)
}

