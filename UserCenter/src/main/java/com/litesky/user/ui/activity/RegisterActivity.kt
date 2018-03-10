package com.litesky.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.widgets.VerifyButton
import com.litesky.base.common.AppManager
import com.litesky.base.ext.enable
import com.litesky.base.ext.onClick
import com.litesky.base.ui.activity.BaseMvpActivity
import com.litesky.user.R
import com.litesky.user.di.component.DaggerUserComponent
import com.litesky.user.di.module.UserModule
import com.litesky.user.presenter.RegisterPresenter
import com.litesky.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView,View.OnClickListener{
    private var pressTime = 0L

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView();


    }

    /**
     * 初始化视图
     */
    private fun initView() {

        mRegisterBtn.enable(mMobileEt,{isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt,{isBtnEnable()})

        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)


    }

    override fun daggerInject() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onBackPressed() {
        val  time = System.currentTimeMillis()
        if (time - pressTime >= 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    override fun onClick(p0: View) {
        when(p0.id)
        {
            R.id.mVerifyCodeBtn ->{
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }
            R.id.mRegisterBtn ->{
                mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
            }
        }
    }

    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() and
                mVerifyCodeEt.text.isNullOrEmpty().not() and
                mPwdEt.text.isNullOrEmpty().not() and
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
