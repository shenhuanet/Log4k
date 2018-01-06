package com.shenhua.libs.log4k.core

import android.util.Log
import com.shenhua.libs.log4k.LK
import com.shenhua.libs.log4k.ext.Constant
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by shenhua on 2018/1/6.
 * Email shenhuanet@126.com
 */
class StringHandler : BaseHandler(), Parser<String> {

    override fun handle(obj: Any): Boolean {
        if (obj is String) {
            val result = obj.trim { it <= ' ' }
            Log.d(LK.TAG, String.format(Box.getBox(), parser(result)))
            return true
        }
        return false
    }

    override fun parser(t: String): String {
        var message = ""
        try {
            when {
                t.startsWith("{") -> {
                    val jsonObject = JSONObject(t)
                    message = jsonObject.toString(Constant.JSON_INDENT)
                    message = message.replace("\n".toRegex(), "\n║ ")
                }
                t.startsWith("[") -> {
                    val jsonArray = JSONArray(t)
                    message = jsonArray.toString(Constant.JSON_INDENT)
                    message = message.replace("\n".toRegex(), "\n║ ")
                }
                else -> message = t
            }
        } catch (e: JSONException) {
            LK.e("Invalid Json: $t")
            message = ""
        }
        return message
    }
}
