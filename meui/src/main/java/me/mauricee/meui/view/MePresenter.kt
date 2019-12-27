package me.mauricee.meui.view

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * @param initialState the initial value that state is set to
 * @param subscribeThread defines the thread the presenter will run in
 *
 * */
abstract class MePresenter<State, Action, Route>(initialState: State,
                                                 private val subscribeThread: Scheduler = Schedulers.computation()) {

    protected var state: State = initialState
        private set

    /**
     *
     * @param previousState the first value that emits when the stream is subscribed to.
     * defaults to state
     *
     * */
    fun attachView(view: MeView<Action, Route>, previousState: State? = null): Flowable<State> = view.actions.flatMap(::onAction)
        .subscribeOn(subscribeThread)
        .startWith(onViewAttached(previousState ?: state, view))
        .distinctUntilChanged()
        .map(::updateState)

    protected open fun onViewAttached(previousState: State, view: MeView<Action, Route>): Flowable<State> =
        Flowable.just(previousState)

    protected open fun onAction(action: Action): Flowable<State> = Flowable.empty()

    protected fun stateless(action: () -> Unit): Flowable<State> = Completable.fromAction(action)
        .andThen(Flowable.empty())

    private fun updateState(state: State): State = state.also { this.state = it }
}