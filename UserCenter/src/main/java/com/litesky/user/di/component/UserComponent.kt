package com.litesky.user.di.component

import com.litesky.base.di.component.ActivityComponent
import com.litesky.base.di.scope.PerComponentScope
import com.litesky.user.di.module.UploadModule
import com.litesky.user.di.module.UserModule
import com.litesky.user.ui.activity.*
import dagger.Component

/**
 * Created by finefine.com on 2018/2/26.
 */

@PerComponentScope
@Component(modules = arrayOf(UserModule::class,UploadModule::class),dependencies = arrayOf(ActivityComponent::class))
interface UserComponent {

    fun inject(activity: RegisterActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: ForgetPwdActivity)

    fun inject(activity: ResetPwdaActivity)
    fun inject(activity: UserInfoActivity)
}