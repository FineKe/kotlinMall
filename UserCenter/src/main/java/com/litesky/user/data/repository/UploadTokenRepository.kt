package com.litesky.user.data.repository

import com.litesky.base.data.net.RetrofitFactory
import com.litesky.base.data.protocol.BaseResponse
import com.litesky.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/3/10.
 */
class UploadTokenRepository @Inject constructor() {
    fun getUploadToken(): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }
}