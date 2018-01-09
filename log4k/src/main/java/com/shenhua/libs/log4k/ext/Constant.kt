package com.shenhua.libs.log4k.ext

/**
 * Created by shenhua on 2018/1/6.
 * Email shenhuanet@126.com
 */
object Constant {


    private val TOP_LEFT_CORNER = '╔'
    private val BOTTOM_LEFT_CORNER = '╚'
    private val MIDDLE_CORNER = '╟'
    private val HORIZONTAL_DOUBLE_LINE = '║'
    private val DOUBLE_DIVIDER = "════════════════════════════════════════════"
    private val SINGLE_DIVIDER = "────────────────────────────────────────────"

    val TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    val BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    val MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER
    val BR = System.getProperty("line.separator")

    val JSON_INDENT = 2
    val MIN_STACK_OFFSET = 10

}