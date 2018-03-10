package com.litesky.user.service

import com.litesky.base.data.protocol.BaseResponse
import rx.Observable

/**
 * Created by finefine.com on 2018/3/10.
 */
interface UploadService {
    fun getUploadToken():Observable<String>
}