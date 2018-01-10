package com.shenhua.libs.log4k.ext

import com.shenhua.libs.log4k.LK

/**
 * Created by shenhua on 2018-01-06-0009.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
object Utils {

    fun getStackOffset(): StackTraceElement {
        val stackElements = Thread.currentThread().stackTrace
        var index = 0
        for (i in stackElements.indices) {
            if (stackElements[i].className == LK::class.java.name) {
                index = i + 1
                break
            }
        }
        return stackElements[index]
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