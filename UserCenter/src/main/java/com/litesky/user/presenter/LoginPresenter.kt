package com.litesky.user.presenter

import com.litesky.base.ext.execute
import com.litesky.base.presenter.view.BasePresenter
import com.litesky.base.rx.BaseSubscriber
import com.litesky.user.data.UserInfo
import com.litesky.user.presenter.view.LoginView
import com.litesky.user.service.UserService
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/3/10.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService:UserService

    fun login(mobile: String, pwd: String, pushId: String) {

        if (!checkNetWork()) {
            return
        }

        mView.showLoading()
        userService.login(mobile,pwd,pushId).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(p0: UserInfo) {
                mView.onLoginResult(p0)
            }
        },lifecycleProvider)
    }
}