package me.mauricee.meui.demo.masterDetail.master

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_master.*
import kotlinx.android.synthetic.main.fragment_master.view.*
import me.mauricee.meui.demo.MockService
import me.mauricee.meui.demo.R
import me.mauricee.meui.demo.masterDetail.MasterDetailContract
import me.mauricee.meui.ext.provideSharedViewModel
import me.mauricee.meui.view.MeFragment
import me.mauricee.meui.view.MePresenter
import me.mauricee.meui.view.MeViewModel

class MasterFragment :
    MeFragment<MasterDetailContract.State, MasterDetailContract.Action, MasterDetailContract.Route>(
        R.layout.fragment_master
    ) {

    override val viewModel: MeViewModel<MasterDetailContract.State, MasterDetailContract.Action, MasterDetailContract.Route> by provideSharedViewModel()
    private val adapter by lazy { MasterNumberAdapter() }
    override val presenter: MePresenter<MasterDetailContract.State, MasterDetailContract.Action, MasterDetailContract.Route>
        get() = MasterPresenter(
            resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE,
            MockService()
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.master_refresh.setOnClickListener { sendAction(MasterAction.Refresh) }
        subscriptions += adapter.selectedNumber.subscribe(::sendAction)
        master_list.adapter = adapter
    }

    override fun renderState(state: MasterDetailContract.State) {
        super.renderState(state)
        adapter.numbers = state.numbers
    }
}