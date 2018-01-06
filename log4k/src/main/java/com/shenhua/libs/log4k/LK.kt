package com.shenhua.libs.log4k

import android.text.TextUtils
import android.util.Log
import com.shenhua.libs.log4k.core.BaseHandler
import com.shenhua.libs.log4k.core.Box.getBox
import com.shenhua.libs.log4k.core.StringHandler
import java.util.*

/**
 * Created by shenhua on 2018/1/6.
 * Email shenhuanet@126.com
 */
object LK {

    val TAG = "LK"
    private var handlers: LinkedList<BaseHandler> = LinkedList<BaseHandler>()
    private var baseHandler: BaseHandler

    init {
        handlers.add(StringHandler())
        (0 until handlers.size)
                .filter { it > 0 }
                .forEach { handlers[it - 1].nextHandler = (handlers[it]) }
        baseHandler = handlers[0]
    }

    fun log(obj: Any?) {
        if (obj == null) {
            d("obj must not be null.")
        }
        baseHandler.handler(obj!!)
    }

    fun d(msg: String?) {
        if (TextUtils.isEmpty(msg!!)) {
            return
        } else {
            val s = getBox()
            if (msg.contains("\n")) {
                Log.d(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
            } else {
                Log.d(TAG, String.format(s, msg))
            }
        }
    }

    fun e(msg: String?) {
        if (TextUtils.isEmpty(msg!!)) {
            return
        } else {
            val s = getBox()
            if (msg.contains("\n")) {
                Log.e(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
            } else {
                Log.e(TAG, String.format(s, msg))
            }
        }
    }

}