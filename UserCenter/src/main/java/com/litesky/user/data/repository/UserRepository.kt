package com.litesky.user.data.repository

import com.litesky.base.data.net.RetrofitFactory
import com.litesky.base.data.protocol.BaseResponse
import com.litesky.user.data.UserInfo
import com.litesky.user.data.api.UserApi
import com.litesky.user.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/2/25.
 */
class UserRepository @Inject constructor(){
    fun register(mobile: String, verfyCode: String, pwd: String): Observable<BaseResponse<String>> {

        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile,pwd,verfyCode))

    }

    fun login(mobile: String ,pwd: String,pushId:String): Observable<BaseResponse<UserInfo>> {

        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(LoginReq(mobile,pwd,pushId))

    }

    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .forgetPwd(ForgetReq(mobile,verifyCode))
    }

    fun resetPwd(mobile: String, pwd: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .resetPwd(RestPwdReq(mobile,pwd))
    }

    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<BaseResponse<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).editUser(EditUserReq(userIcon,userName,userGender,userSign))
    }
}