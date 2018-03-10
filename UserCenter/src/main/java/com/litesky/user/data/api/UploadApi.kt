package com.litesky.user.data.api

import com.litesky.base.data.protocol.BaseResponse
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

/**
 * Created by finefine.com on 2018/3/10.
 */
interface UploadApi {

    @POST("common/getUploadToken")
    fun getUploadToken():Observable<BaseResponse<String>>
}