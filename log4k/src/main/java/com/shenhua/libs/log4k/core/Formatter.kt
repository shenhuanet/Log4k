package com.shenhua.libs.log4k.core

/**
 * Created by shenhua on 2018/1/6.
 * Email shenhuanet@126.com
 */
interface Formatter<in T> {

    fun format(t: T): String

}