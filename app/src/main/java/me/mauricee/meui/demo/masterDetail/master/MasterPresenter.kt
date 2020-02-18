package me.mauricee.meui.demo.masterDetail.master

import io.reactivex.Flowable
import me.mauricee.meui.demo.MockService
import me.mauricee.meui.demo.masterDetail.MasterDetailContract
import me.mauricee.meui.demo.masterDetail.MasterDetailPresenter
import me.mauricee.meui.view.MeView

class MasterPresenter(
    private val isLandscape: Boolean,
    private val mockService: MockService
) : MasterDetailPresenter() {

    override fun onViewAttached(
        previousState: MasterDetailContract.State,
        view: MeView<MasterDetailContract.Action, MasterDetailContract.Route>
    ): Flowable<MasterDetailContract.State> {
        return if (previousState.numbers.isEmpty())
            refresh().startWith(super.onViewAttached(previousState, view))
        else
            super.onViewAttached(previousState, view)
    }

    override fun onMasterAction(
        action: MasterAction,
        view: MeView<MasterDetailContract.Action, MasterDetailContract.Route>
    ): Flowable<MasterDetailContract.State> {
        return when (action) {
            MasterAction.Refresh -> refresh()
            is MasterAction.SelectNumber -> selectNumber(action.index, view)
        }
    }

    private fun refresh(): Flowable<MasterDetailContract.State> = mockService.rangeOf(5)
        .toFlowable().map { state.copy(numbers = it) }

    private fun selectNumber(
        index: Int,
        view: MeView<MasterDetailContract.Action, MasterDetailContract.Route>
    ) = Flowable.fromCallable {
        state.copy(selectedNumber = index)
    }.doAfterNext { if (isLandscape) view.routeTo(MasterDetailContract.Route.ToDetail) }
}