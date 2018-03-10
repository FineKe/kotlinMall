package com.litesky.base.common

import android.app.Application
import android.content.Context
import com.litesky.base.di.component.AppComponent
import com.litesky.base.di.component.DaggerAppComponent
import com.litesky.base.di.module.AppModule

/**
 * Created by finefine.com on 2018/2/26.
 */
class BaseApplication:Application() {

    lateinit var appComponent:AppComponent
    override fun onCreate() {
        super.onCreate()

        initAppInjection()
        context = this
    }

    private fun initAppInjection() {
         appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
    companion object {

        lateinit var context:Context
    }
}