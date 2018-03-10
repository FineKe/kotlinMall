package com.litesky.user.service.impl

import com.litesky.base.data.protocol.BaseResponse
import com.litesky.base.ext.convert
import com.litesky.base.ext.convertBoolean
import com.litesky.base.ext.execute
import com.litesky.base.rx.BaseException
import com.litesky.base.rx.BaseFuncBoolean
import com.litesky.base.rx.BaseSubscriber
import com.litesky.user.data.UserInfo
import com.litesky.user.data.repository.UserRepository
import com.litesky.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by finefine.com on 2018/2/25.
 */
class UserServiceImpl @Inject constructor():UserService {



    @Inject
    lateinit var repository:UserRepository

    override fun register(mobile: String, verfyCode: String, pwd: String): Observable<Boolean> {



        return repository.register(mobile,verfyCode,pwd).convertBoolean()
//                flatMap (BaseFuncBoolean())
    }

    override fun login(mobile: String, pwd: String, pushID: String): Observable<UserInfo> {

        return repository.login(mobile,pwd,pushID).convert()
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile,verifyCode).convertBoolean()
    }


    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile,pwd).convertBoolean()
    }

    override fun editUser(userIcon: String, userName: String,userGender: String, userSign: String): Observable<UserInfo> {
        return repository.editUser(userIcon,userName,userGender,userSign).convert()
    }
}
