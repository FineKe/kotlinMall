package com.litesky.base.ext

import android.sax.EndTextElementListener
import android.text.BoringLayout
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.kotlin.base.widgets.DefaultTextWatcher
import com.litesky.base.data.protocol.BaseResponse
import com.litesky.base.rx.BaseFunc
import com.litesky.base.rx.BaseFuncBoolean
import com.litesky.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by finefine.com on 2018/2/25.
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){

    this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

/*
    扩展点击事件
 */
fun View.onClick(listener:View.OnClickListener):View{
    setOnClickListener(listener)
    return this
}

/*
    扩展点击事件，参数为方法
 */
fun View.onClick(method:() -> Unit):View{
    setOnClickListener { method() }
    return this
}

fun <T> Observable<BaseResponse<T>>.convert():Observable<T>{
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResponse<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFuncBoolean())
}

fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object :DefaultTextWatcher(){

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            btn.isEnabled = method()
        }
    })
}