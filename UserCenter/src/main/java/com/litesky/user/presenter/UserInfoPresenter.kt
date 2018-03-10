package com.litesky.user.presenter

import com.litesky.base.ext.execute
import com.litesky.base.presenter.view.BasePresenter
import com.litesky.base.rx.BaseSubscriber
import com.litesky.user.data.UserInfo
import com.litesky.user.presenter.view.UserInfoView
import com.litesky.user.service.UploadService
import com.litesky.user.service.UserService
import javax.inject.Inject
import kotlin.jvm.internal.PropertyReference0

/**
 * Created by finefine.com on 2018/3/10.
 */
class UserInfoPresenter @Inject constructor(): BasePresenter<UserInfoView>(){


    @Inject
    lateinit var userService:UserService

    @Inject
    lateinit var uploadService:UploadService

    fun getUploadToken() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        uploadService.getUploadToken().execute(object : BaseSubscriber<String>(mView){
            override fun onNext(p0: String) {
                mView.onGetUploadTokenResult(p0)
            }
        },lifecycleProvider)
    }

    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.editUser(userIcon,userName,userGender,userSign).execute(object :BaseSubscriber<UserInfo>(mView){
            override fun onNext(p0: UserInfo) {
                mView.onEditUserInfo(p0)
            }
        },lifecycleProvider)
    }

}