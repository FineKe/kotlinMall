package com.litesky.base.presenter.view

/**
 * Created by finefine.com on 2018/2/25.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(string: String)
}