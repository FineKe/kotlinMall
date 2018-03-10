package com.litesky.base.ui.activity

import android.os.Bundle
import com.litesky.base.common.BaseApplication
import com.litesky.base.di.component.ActivityComponent
import com.litesky.base.di.component.DaggerActivityComponent
import com.litesky.base.di.module.ActivityModule
import com.litesky.base.di.module.LifeCycleProviderModule
import com.litesky.base.presenter.view.BasePresenter
import com.litesky.base.presenter.view.BaseView
import com.litesky.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/2/25.
 */
abstract class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView{

    lateinit var activityComponent:ActivityComponent
    private lateinit var mLoadingDialog:ProgressLoading


    @Inject
    lateinit var mPresenter:T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        daggerInject()
        mLoadingDialog = ProgressLoading.create(this)
    }


    private fun initActivityInjection() {
            activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent).activityModule(ActivityModule(this))
                    .lifeCycleProviderModule(LifeCycleProviderModule(this))
                    .build()
    }


    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLOading()
    }

    override fun onError(string: String) {
        toast(string)
    }

    abstract fun daggerInject()

}