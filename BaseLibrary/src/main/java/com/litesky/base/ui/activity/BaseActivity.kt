package com.litesky.base.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.litesky.base.common.AppManager
import com.trello.rxlifecycle.components.RxActivity
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * Created by finefine.com on 2018/2/25.
 */
open class BaseActivity:RxAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}