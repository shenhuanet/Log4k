package com.shenhua.libs.log4k

import android.text.TextUtils
import android.util.Log
import com.shenhua.libs.log4k.core.BaseHandler
import com.shenhua.libs.log4k.core.Box
import com.shenhua.libs.log4k.handler.*

/**
 * Created by shenhua on 2018/1/6.
 * Email shenhuanet@126.com
 */
object LK {

    val TAG = "LK"
    private var handlers = ArrayList<BaseHandler>()
    private var baseHandler: BaseHandler

    init {
        handlers.add(StringHandler())
        handlers.add(CollectionHandler())
        handlers.add(MapHandler())
        handlers.add(BundleHandler())
        handlers.add(IntentHandler())
        handlers.add(PojoHandler())
        (0 until handlers.size)
                .filter { it > 0 }
                .forEach { handlers[it - 1].successor = handlers[it] }
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
        }
        val s = Box.getSingleBox()
        if (msg.contains("\n")) {
            Log.d(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
        } else {
            Log.d(TAG, String.format(s, msg))
        }
    }

    fun v(msg: String?) {
        if (TextUtils.isEmpty(msg!!)) {
            return
        }
        val s = Box.getSingleBox()
        if (msg.contains("\n")) {
            Log.v(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
        } else {
            Log.v(TAG, String.format(s, msg))
        }
    }

    fun i(msg: String?) {
        if (TextUtils.isEmpty(msg!!)) {
            return
        }
        val s = Box.getSingleBox()
        if (msg.contains("\n")) {
            Log.i(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
        } else {
            Log.i(TAG, String.format(s, msg))
        }
    }

    fun w(msg: String?) {
        if (TextUtils.isEmpty(msg!!)) {
            return
        }
        val s = Box.getSingleBox()
        if (msg.contains("\n")) {
            Log.w(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
        } else {
            Log.w(TAG, String.format(s, msg))
        }
    }

    fun e(msg: String?) {
        if (TextUtils.isEmpty(msg!!)) {
            return
        }
        val s = Box.getSingleBox()
        if (msg.contains("\n")) {
            Log.e(TAG, String.format(s, msg.replace("\n".toRegex(), "\n║ ")))
        } else {
            Log.e(TAG, String.format(s, msg))
        }
    }

    fun addHandler(handler: BaseHandler): LK {
        handlers.add(handlers.size - 1, handler)
        (0 until handlers.size)
                .filter { it > 0 }
                .forEach { handlers[it - 1].successor = handlers[it] }
        return this
    }

}