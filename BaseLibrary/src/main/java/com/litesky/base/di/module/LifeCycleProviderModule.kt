package com.litesky.base.di.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by finefine.com on 2018/2/26.
 */
@Module
class LifeCycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {


    @Provides
    fun providerLifeCycle():LifecycleProvider<*>
    {
        return lifecycleProvider
    }
}