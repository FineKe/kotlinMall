package com.litesky.user.di.module

import com.litesky.user.service.UploadService
import com.litesky.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by finefine.com on 2018/3/10.
 */
@Module
class UploadModule {


    @Provides
    fun provideUploadService(uploadService: UploadServiceImpl):UploadService {
        return uploadService
    }
}