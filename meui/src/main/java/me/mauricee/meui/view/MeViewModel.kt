package me.mauricee.meui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import me.mauricee.meui.livedata.SingleLiveEvent

open class MeViewModel<State, Action, Route> : ViewModel(), MeView<Action, Route> {

    private val _actions: PublishSubject<Action> = PublishSubject.create()
    private val _state: MutableLiveData<State> = MutableLiveData()
    private val _route: MutableLiveData<Route> = SingleLiveEvent()

    override val actions: Flowable<Action>
        get() = _actions.toFlowable(BackpressureStrategy.LATEST)
    val state: LiveData<State>
        get() = _state
    val routes: LiveData<Route>
        get() = _route

    override fun routeTo(route: Route) = _route.postValue(route)

    fun addPresenter(presenter: MePresenter<State, Action, Route>): Disposable =
        presenter.attachView(this, _state.value)
            .subscribe(_state::postValue)

    fun sendAction(action: Action) = _actions.onNext(action)

    class Factory<State, Action, Route>(private val creator: () -> MeViewModel<State, Action, Route>) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = creator() as T
    }
}