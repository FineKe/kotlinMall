package com.litesky.user.ui.activity

import android.os.Bundle
import android.view.View
import com.litesky.base.ext.enable
import com.litesky.base.ui.activity.BaseMvpActivity
import com.litesky.user.R
import com.litesky.user.di.component.DaggerUserComponent
import com.litesky.user.di.module.UserModule
import com.litesky.user.presenter.ResetPwdPresenter
import com.litesky.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * Created by finefine.com on 2018/3/10.
 */
class ResetPwdaActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
    }



    private fun initView() {
        mConfirmBtn.enable(mPwdEt,{isEnable()})
        mConfirmBtn.enable(mPwdConfirmEt,{isEnable()})
        mConfirmBtn.setOnClickListener {
            if (!mPwdEt.text.toString().equals(mPwdConfirmEt.text.toString())) {
                toast("两次密码不一致")

            } else {
                mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
            }
        }

    }

    override fun daggerInject() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView=this
    }


    override fun onClick(p0: View?) {

    }

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

    private fun isEnable():Boolean {
        return mPwdEt.text.isNullOrEmpty().not() and  mPwdConfirmEt.text.isNullOrEmpty().not()
    }

}
