package me.mauricee.meui.demo.masterDetail.detail

import me.mauricee.meui.demo.masterDetail.MasterDetailContract

sealed class DetailAction : MasterDetailContract.Action() {
    object NextNumber : DetailAction()
    object PreviousNumber: DetailAction()
}