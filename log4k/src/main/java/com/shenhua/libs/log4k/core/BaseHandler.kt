package com.shenhua.libs.log4k.core

/**
 * Created by shenhua on 2018/1/6.
 * Email shenhuanet@126.com
 */
abstract class BaseHandler {

    /**
     * 责任链的下一个处理者
     */
    var nextHandler: BaseHandler? = null

    fun handler(obj: Any) {
        /**
         * 当前处理者未处理，传递到下一处理者
         */
        if (!handle(obj)) {
            if (this.nextHandler != null) {
                this.nextHandler!!.handle(obj)
            }
        }
    }

    protected abstract fun handle(obj: Any): Boolean
}