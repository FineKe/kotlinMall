package com.litesky.user.service.impl

import com.litesky.base.ext.convert
import com.litesky.user.data.repository.UploadTokenRepository
import com.litesky.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/3/10.
 */
class UploadServiceImpl @Inject constructor():UploadService {

    @Inject
    lateinit var upLoadupTokenRepository:UploadTokenRepository

    override fun getUploadToken(): Observable<String> {
        return upLoadupTokenRepository.getUploadToken().convert()
    }
}