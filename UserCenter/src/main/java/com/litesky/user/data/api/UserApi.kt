package com.litesky.user.data.api

import com.litesky.base.data.protocol.BaseResponse
import com.litesky.user.data.UserInfo
import com.litesky.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by finefine.com on 2018/2/25.
 */
interface UserApi {

    @POST("userCenter/register")
    fun register(@Body req:RegisterReq):Observable<BaseResponse<String>>

    @POST("userCenter/login")
    fun login(@Body req: LoginReq):Observable<BaseResponse<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req: ForgetReq):Observable<BaseResponse<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: RestPwdReq):Observable<BaseResponse<String>>

    @POST("userCenter/editUser")
    fun editUser(@Body req: EditUserReq):Observable<BaseResponse<UserInfo>>
}