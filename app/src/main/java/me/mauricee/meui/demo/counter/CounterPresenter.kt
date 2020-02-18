package me.mauricee.meui.demo.counter

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import me.mauricee.meui.demo.MockService
import me.mauricee.meui.demo.route.AppRoute
import me.mauricee.meui.view.MePresenter
import me.mauricee.meui.view.MeView
import java.util.concurrent.TimeUnit

class CounterPresenter(private val mockService: MockService) :
    MePresenter<TestContract.State, TestContract.Action, AppRoute>(TestContract.State()) {

    override fun onViewAttached(
        previousState: TestContract.State,
        view: MeView<TestContract.Action, AppRoute>
    ): Flowable<TestContract.State> =
        Flowable.interval(1000, TimeUnit.MILLISECONDS, Schedulers.io())
            .flatMap { if (state.timer == 0) nextNumber() else Flowable.just(state.copy(timer = state.timer - 1)) }
            .startWith(previousState)

    override fun onAction(
        action: TestContract.Action,
        view: MeView<TestContract.Action, AppRoute>
    ): Flowable<TestContract.State> =
        when (action) {
            TestContract.Action.NextNumber -> nextNumber()
        }

    private fun nextNumber(): Flowable<TestContract.State> =
        mockService.randomNumber.toFlowable().map {
            state.copy(
                number = it,
                timer = 5
            )
        }
}