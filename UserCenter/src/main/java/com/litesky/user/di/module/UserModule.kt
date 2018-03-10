package com.litesky.user.di.module

import com.litesky.user.service.UserService
import com.litesky.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by finefine.com on 2018/2/26.
 */
@Module
class UserModule {

    @Provides
    fun provideUserService(userService: UserServiceImpl): UserService {
        return userService
    }
}