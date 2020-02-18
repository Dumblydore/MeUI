package me.mauricee.meui.demo.masterDetail

import io.reactivex.Flowable
import me.mauricee.meui.demo.masterDetail.detail.DetailAction
import me.mauricee.meui.demo.masterDetail.master.MasterAction
import me.mauricee.meui.view.MePresenter
import me.mauricee.meui.view.MeView

abstract class MasterDetailPresenter :
    MePresenter<MasterDetailContract.State, MasterDetailContract.Action, MasterDetailContract.Route>(
        MasterDetailContract.State()
    ) {

    final override fun onAction(
        action: MasterDetailContract.Action,
        view: MeView<MasterDetailContract.Action, MasterDetailContract.Route>
    ): Flowable<MasterDetailContract.State> {
        return when (action) {
            is MasterAction -> onMasterAction(action, view)
            is DetailAction -> onDetailAction(action, view)
            else -> super.onAction(action, view)
        }
    }

    open fun onMasterAction(
        action: MasterAction,
        view: MeView<MasterDetailContract.Action, MasterDetailContract.Route>
    ): Flowable<MasterDetailContract.State> = Flowable.empty()

    open fun onDetailAction(
        action: DetailAction,
        view: MeView<MasterDetailContract.Action, MasterDetailContract.Route>
    ): Flowable<MasterDetailContract.State> = Flowable.empty()
}