package com.litesky.base.rx

import com.litesky.base.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by finefine.com on 2018/2/25.
 */
open class BaseSubscriber<T>(val baseView:BaseView):Subscriber<T>(){


    override fun onCompleted() {
        baseView.hideLoading()

    }

    override fun onError(p0: Throwable?) {
        baseView.hideLoading()
        if (p0 is BaseException) {
            baseView.onError(p0.msg)
        }
    }

    override fun onNext(p0: T) {

    }

}