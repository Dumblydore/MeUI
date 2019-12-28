package me.mauricee.meui.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import me.mauricee.meui.view.MeView
import me.mauricee.meui.view.MeViewModel

inline fun <State, Action, Route, reified T : MeViewModel<State, Action, Route>> Fragment.provideSharedViewModel(
    noinline creator: () -> MeViewModel<State, Action, Route> = { MeViewModel() }
): Lazy<MeViewModel<State, Action, Route>> = lazy {
    ViewModelProviders.of(requireActivity(), MeViewModel.Factory(creator))[T::class.java]
}

inline fun <State, Action, Route, reified T : MeViewModel<State, Action, Route>> Fragment.provideViewModel(
    noinline creator: () -> MeViewModel<State, Action, Route> = { MeViewModel() }
): Lazy<MeViewModel<State, Action, Route>> = lazy {
    ViewModelProviders.of(this, MeViewModel.Factory(creator))[T::class.java]
}