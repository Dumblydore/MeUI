package me.mauricee.meui.demo.masterDetail.master

import me.mauricee.meui.demo.masterDetail.MasterDetailContract

sealed class MasterAction : MasterDetailContract.Action() {
    object Refresh : MasterAction()
    class SelectNumber(val index: Int) : MasterAction()
}