package com.litesky.user.presenter

import android.widget.Toast
import com.litesky.base.ext.execute
import com.litesky.base.presenter.view.BasePresenter
import com.litesky.base.rx.BaseSubscriber
import com.litesky.user.presenter.view.RegisterView
import com.litesky.user.service.impl.UserServiceImpl
import com.litesky.user.ui.activity.RegisterActivity
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/2/25.
 */
class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService:UserServiceImpl

    fun register(mobile: String, verifyCode: String,pwd:String) {


        if (!checkNetWork()) {
            println("网络不可用")
            return
        }
        mView.showLoading()

        userService.register(mobile,verifyCode,pwd)
                .execute(object :BaseSubscriber<Boolean>(mView)
                {
                    override fun onNext(p0: Boolean) {
                        if (p0) {
                            mView.onRegisterResult("注册成功")
                        } else {
                            mView.onRegisterResult("注册失败")
                        }
                        mView.hideLoading()
                    }
                },lifecycleProvider)

    }

}