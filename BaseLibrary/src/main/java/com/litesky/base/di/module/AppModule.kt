package com.litesky.base.di.module

import android.content.Context
import com.litesky.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by finefine.com on 2018/2/26.
 */
@Module
class AppModule(private val context:BaseApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}