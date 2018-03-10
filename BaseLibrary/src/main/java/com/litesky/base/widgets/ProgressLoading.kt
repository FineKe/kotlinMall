package com.litesky.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.litesky.base.R
import org.jetbrains.anko.find

/**
 * Created by finefine.com on 2018/2/27.
 */
class ProgressLoading private constructor(context: Context?, themeResId: Int) : Dialog(context, themeResId) {
    companion object {
        private lateinit var mDIalog:ProgressLoading
        private  var animDrawable:AnimationDrawable? = null

        fun create(context: Context):ProgressLoading{
            mDIalog = ProgressLoading(context,R.style.LightProgressDialog)
            mDIalog.setContentView(R.layout.progress_dialog)
            mDIalog.setCancelable(true)
            mDIalog.setCanceledOnTouchOutside(false)
            mDIalog.window.attributes.gravity = Gravity.CENTER
            val lp = mDIalog.window.attributes
            lp.dimAmount = 0.2f //设置灰度
            mDIalog.window.attributes = lp
            val loadingView = mDIalog.find<ImageView>(R.id.iv_loading)

            animDrawable = loadingView.background as AnimationDrawable
            animDrawable
            return  mDIalog
        }
    }

    fun showLoading() {
        super.show()
        animDrawable?.start()
    }

    fun hideLOading() {
        super.dismiss()
        animDrawable?.stop()
    }
}