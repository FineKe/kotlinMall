package com.litesky.user.presenter

import com.litesky.base.ext.execute
import com.litesky.base.presenter.view.BasePresenter
import com.litesky.base.rx.BaseSubscriber
import com.litesky.user.presenter.view.ResetPwdView
import com.litesky.user.service.UserService
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/3/10.
 */
class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService:UserService


    fun resetPwd(mobile: String, pwd: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile,pwd).execute(object :BaseSubscriber<Boolean>(mView){
            override fun onNext(p0: Boolean) {

                if (p0) {
                    mView.onResetPwdResult("重置成功")
                }
            }
        },lifecycleProvider)
    }
}