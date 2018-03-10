package com.litesky.base.di.component

import android.app.Activity
import android.content.Context
import com.litesky.base.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by finefine.com on 2018/2/26.
 */
@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun context():Context
}