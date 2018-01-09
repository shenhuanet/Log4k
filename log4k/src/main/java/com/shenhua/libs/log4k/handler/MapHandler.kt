package com.shenhua.libs.log4k.handler

import android.util.Log
import com.google.gson.Gson
import com.shenhua.libs.log4k.LK
import com.shenhua.libs.log4k.core.BaseHandler
import com.shenhua.libs.log4k.core.Box
import com.shenhua.libs.log4k.core.Formatter
import com.shenhua.libs.log4k.ext.Constant
import com.shenhua.libs.log4k.ext.Utils
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by shenhua on 2018-01-09-0009.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class MapHandler() : BaseHandler(), Formatter<Map<*, *>> {

    override fun handle(obj: Any): Boolean {
        if (obj is Map<*, *>) {
            Log.d(LK.TAG, String.format(Box.getBox(), format(obj)))
            return true
        }
        return false
    }

    override fun format(t: Map<*, *>): String {
        val jsonObject = JSONObject()
        val gson = Gson()
        t.keys.map { it ->
            try {
                if (Utils.isPrimitiveType(t.values.firstOrNull())) {
                    jsonObject.put(it.toString(), t[it])
                } else {
                    jsonObject.put(it.toString(), JSONObject(gson.toJson(t[it])))
                }
            } catch (e: JSONException) {
                LK.e("Invalid Json")
            }
        }
        var message = jsonObject.toString(Constant.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")
        val msg = t.javaClass.toString() + Constant.BR + "║ "
        return msg + message
    }
}