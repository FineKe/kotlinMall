package com.litesky.user.presenter.view

import com.litesky.base.presenter.view.BaseView
import com.litesky.user.data.UserInfo

/**
 * Created by finefine.com on 2018/3/10.
 */
interface LoginView:BaseView {
    fun onLoginResult(result:UserInfo)

}