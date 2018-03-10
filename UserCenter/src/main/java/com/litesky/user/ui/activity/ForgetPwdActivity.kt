package com.litesky.user.ui.activity

import android.os.Bundle
import android.view.View
import com.litesky.base.ext.enable
import com.litesky.base.ui.activity.BaseMvpActivity
import com.litesky.user.R
import com.litesky.user.di.component.DaggerUserComponent
import com.litesky.user.di.module.UserModule
import com.litesky.user.presenter.ForgetPwdPresenter
import com.litesky.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by finefine.com on 2018/3/10.
 */
class ForgetPwdActivity: BaseMvpActivity<ForgetPwdPresenter>(),ForgetPwdView ,View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }


    private fun initView() {
        mVerifyCodeBtn.setOnClickListener(this)
        mNextBtn.setOnClickListener(this)
        mNextBtn.enable(mMobileEt,{isEnable()})
        mNextBtn.enable(mVerifyCodeEt,{isEnable()})
    }

    override fun onForgetPwdResult(result: String) {
       startActivity<ResetPwdaActivity>("mobile" to mMobileEt.text.toString())
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.mVerifyCodeBtn ->{
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("验证码发送成功")
            }

            R.id.mNextBtn ->{
                mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeEt.text.toString())
            }
        }
    }


    override fun daggerInject() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    private fun isEnable():Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and mVerifyCodeEt.text.isNullOrEmpty().not()
    }
}