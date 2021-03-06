package me.mauricee.meui.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.mauricee.meui.view.MeViewModel

inline fun <reified State, Action, Route, reified T : MeViewModel<State, Action, Route>> Fragment.provideSharedViewModel(
    noinline creator: () -> MeViewModel<State, Action, Route> = { MeViewModel() },
    key: String = State::class.java.name
): Lazy<MeViewModel<State, Action, Route>> = lazy {
    ViewModelProvider(requireActivity(), MeViewModel.Factory(creator))[key, T::class.java]
}

inline fun <State, Action, Route, reified T : MeViewModel<State, Action, Route>> Fragment.provideViewModel(
    noinline creator: () -> MeViewModel<State, Action, Route> = { MeViewModel() }
): Lazy<MeViewModel<State, Action, Route>> = lazy {
    ViewModelProvider(this, MeViewModel.Factory(creator))[T::class.java]
}