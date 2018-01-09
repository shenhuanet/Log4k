package com.shenhua.libs.log4k.handler

import android.util.Log
import com.google.gson.Gson
import com.shenhua.libs.log4k.LK
import com.shenhua.libs.log4k.core.BaseHandler
import com.shenhua.libs.log4k.core.Box
import com.shenhua.libs.log4k.ext.Constant
import org.json.JSONObject

/**
 * Created by shenhua on 2018-01-09-0009.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class PojoHandler : BaseHandler() {

    override fun handle(obj: Any): Boolean {
        val jsonObject = JSONObject(Gson().toJson(obj))
        var message = jsonObject.toString(Constant.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")
        val msg = obj.javaClass.toString() + Constant.BR + "║ "
        Log.d(LK.TAG, String.format(Box.getBox(), msg + message))
        return true
    }
}