package com.litesky.base.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * Created by finefine.com on 2018/2/26.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }
}