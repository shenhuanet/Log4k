package com.shenhua.libs.log4k.core

import com.shenhua.libs.log4k.ext.Constant
import com.shenhua.libs.log4k.ext.Utils

/**
 * Created by shenhua on 2018-01-06-0009.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
object Box {

    private var header: String? = ""
    var level = LogLevel.DEBUG

    fun getBox(): String {
        val stackOffset = Utils.getStackOffset()
        val builder = StringBuilder()
        builder.append(Constant.TOP_BORDER).append(Constant.BR)
        if (header != null && header!!.isNotEmpty()) {
            builder.append("║ " + "Header: " + header).append(Constant.BR)
                    .append(Constant.MIDDLE_BORDER).append(Constant.BR)
        }
        builder.append("║ " + "Thread: " + Thread.currentThread().name).append(Constant.BR)
                .append("║ ")
                .append(stackOffset.className)
                .append(".")
                .append(stackOffset.methodName)
                .append(" ")
                .append(" (")
                .append(stackOffset.fileName)
                .append(":")
                .append(stackOffset.lineNumber)
                .append(")")
                .append(Constant.BR)
                .append(Constant.MIDDLE_BORDER).append(Constant.BR)
                .append("║ ").append("%s").append(Constant.BR)
                .append(Constant.BOTTOM_BORDER).append(Constant.BR)
        return builder.toString()
    }

    fun getSingleBox(): String {
        val trace = Thread.currentThread().stackTrace
        val stackOffset = trace[4]
        val builder = StringBuilder()
        builder.append("║ ")
                .append("%s")
                .append(" ")
                .append(" (")
                .append(stackOffset.fileName)
                .append(":")
                .append(stackOffset.lineNumber)
                .append(")")
        return builder.toString()
    }

    enum class LogLevel {
        DEBUG, ERROR, WARN, INFO
    }
}