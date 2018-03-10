package com.litesky.base.di.component

import android.app.Activity
import android.content.Context
import com.litesky.base.di.module.ActivityModule
import com.litesky.base.di.module.LifeCycleProviderModule
import com.litesky.base.di.scope.ActivityScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Created by finefine.com on 2018/2/26.
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class,LifeCycleProviderModule::class),dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    fun lifeCycleProvider():LifecycleProvider<*>
    fun activity():Activity
    fun context():Context

}