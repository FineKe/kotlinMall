package com.litesky.base.data.protocol

/**
 * Created by finefine.com on 2018/2/25.
 */
open class BaseResponse<out T>(val status: Int, val message: String,val data:T) {
}