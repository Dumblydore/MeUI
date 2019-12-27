package me.mauricee.meui.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import me.mauricee.meui.view.MeViewModel

inline fun <reified T: ViewModel> Fragment.provideViewModel(noinline creator: () -> T):Lazy<T>
        = lazy { ViewModelProviders.of(requireActivity(), MeViewModel.Factory(creator))[T::class.java] }
inline fun <reified T: ViewModel> Fragment.provideScopedViewModel(noinline creator: () -> T):Lazy<T>
        = lazy { ViewModelProviders.of(this, MeViewModel.Factory(creator))[T::class.java] }