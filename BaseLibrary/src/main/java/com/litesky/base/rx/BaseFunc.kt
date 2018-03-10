package com.litesky.base.rx

import com.litesky.base.common.ResultCode
import com.litesky.base.data.protocol.BaseResponse
import rx.Observable
import rx.functions.Func1

/**
 * Created by finefine.com on 2018/2/26.
 */

class BaseFunc<T>: Func1<BaseResponse<T>, Observable<T>> {

    override fun call(p0: BaseResponse<T>): Observable<T> {
        if (p0.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(p0.status, p0.message))
        }
        return Observable.just(p0.data)
    }
}