package com.shenhua.libs.log4k.ext

import com.shenhua.libs.log4k.LK

/**
 * Created by shenhua on 2018/1/6.
 * Email shenhuanet@126.com
 */
object Utils {

    fun getStackOffset(): StackTraceElement {
        val trace = Thread.currentThread().stackTrace
        var i = Constant.MIN_STACK_OFFSET
        var result = 0
        while (i < trace.size) {
            val st = trace[i]
            val name = st.className
            if (name != Utils::class.java.name && name != LK::class.java.name) {
                result = --i - 2
                break
            }
            i++
        }
        return trace[result]
    }

    fun isPrimitiveType(value: Any?): Boolean = when (value) {
        is Boolean -> true
        is String -> true
        is Int -> true
        is Float -> true
        is Double -> true
        else -> false
    }
}