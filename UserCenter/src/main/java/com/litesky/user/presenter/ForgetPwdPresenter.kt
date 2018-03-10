package com.litesky.user.presenter

import com.litesky.base.ext.execute
import com.litesky.base.presenter.view.BasePresenter
import com.litesky.base.rx.BaseSubscriber
import com.litesky.user.presenter.view.ForgetPwdView
import com.litesky.user.service.UserService
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/3/10.
 */
class ForgetPwdPresenter @Inject constructor(): BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService:UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        userService.forgetPwd(mobile,verifyCode).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(p0: Boolean) {
                if (p0) {
                    mView.onForgetPwdResult("验证成功")
                }
            }
        },lifecycleProvider)
    }
}