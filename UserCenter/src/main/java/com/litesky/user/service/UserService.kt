package com.litesky.user.service

import android.icu.lang.UScript
import com.litesky.user.data.UserInfo
import rx.Observable

/**
 * Created by finefine.com on 2018/2/25.
 */
interface UserService {
    fun register(mobile:String,verfyCode:String,pwd:String):Observable<Boolean>

    fun login(mobile: String,pwd: String,pushID:String):Observable<UserInfo>

    fun forgetPwd(mobile: String,verifyCode:String):Observable<Boolean>

    fun resetPwd(mobile: String,pwd: String):Observable<Boolean>

    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String):Observable<UserInfo>


}