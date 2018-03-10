package com.litesky.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.user.utils.UserPrefsUtils
import com.litesky.base.ext.enable
import com.litesky.base.ext.onClick
import com.litesky.base.ui.activity.BaseMvpActivity
import com.litesky.user.R
import com.litesky.user.data.UserInfo
import com.litesky.user.di.component.DaggerUserComponent
import com.litesky.user.di.module.UserModule
import com.litesky.user.presenter.LoginPresenter
import com.litesky.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by finefine.com on 2018/3/10.
 */
class LoginActivity:BaseMvpActivity<LoginPresenter>(),LoginView,View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView();
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt,{isEnable()})
        mLoginBtn.enable(mPwdEt,{isEnable()})
        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.setOnClickListener(this)
    }


    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(result)
        startActivity<UserInfoActivity>()
    }

    override fun daggerInject() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
            }

            R.id.mRightTv -> {startActivity<RegisterActivity>()}

            R.id.mForgetPwdTv ->{startActivity<ForgetPwdActivity>()}
        }
    }

    private fun isEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mPwdEt.text.isNullOrEmpty().not()

    }


}