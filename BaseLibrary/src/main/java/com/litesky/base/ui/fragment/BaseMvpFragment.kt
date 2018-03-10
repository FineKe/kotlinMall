package com.litesky.base.ui.fragment

import android.os.Bundle
import com.litesky.base.common.BaseApplication
import com.litesky.base.di.component.ActivityComponent
import com.litesky.base.di.component.DaggerActivityComponent
import com.litesky.base.di.module.ActivityModule
import com.litesky.base.di.module.LifeCycleProviderModule
import com.litesky.base.presenter.view.BasePresenter
import com.litesky.base.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/2/26.
 */
 abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    lateinit var activityComponent: ActivityComponent

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(string: String) {

    }

    @Inject
    lateinit var mPresenter:T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        daggerInject()
    }


    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication).appComponent).activityModule(ActivityModule(activity))
                .lifeCycleProviderModule(LifeCycleProviderModule(this))
                .build()
    }


    abstract fun daggerInject()


}