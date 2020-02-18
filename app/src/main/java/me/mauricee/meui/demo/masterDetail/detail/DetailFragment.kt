package me.mauricee.meui.demo.masterDetail.detail

import kotlinx.android.synthetic.main.fragment_detail.*
import me.mauricee.meui.demo.R
import me.mauricee.meui.demo.masterDetail.MasterDetailContract
import me.mauricee.meui.ext.provideSharedViewModel
import me.mauricee.meui.view.MeFragment
import me.mauricee.meui.view.MePresenter
import me.mauricee.meui.view.MeViewModel

class DetailFragment :
    MeFragment<MasterDetailContract.State, MasterDetailContract.Action, MasterDetailContract.Route>(
        R.layout.fragment_detail
    ) {

    override val viewModel: MeViewModel<MasterDetailContract.State, MasterDetailContract.Action, MasterDetailContract.Route> by provideSharedViewModel()

    override val presenter: MePresenter<MasterDetailContract.State, MasterDetailContract.Action, MasterDetailContract.Route>
        get() = DetailPresenter()

    override fun renderState(state: MasterDetailContract.State) {
        super.renderState(state)
        detail_selectedNumber.text = state.selectedNumber?.let { "$it" } ?: "No number selected."
    }
}