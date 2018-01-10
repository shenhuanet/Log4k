package com.shenhua.libs.log4k.handler

import android.util.Log
import com.google.gson.Gson
import com.shenhua.libs.log4k.LK
import com.shenhua.libs.log4k.core.BaseHandler
import com.shenhua.libs.log4k.core.Box
import com.shenhua.libs.log4k.core.Formatter
import com.shenhua.libs.log4k.ext.Constant
import com.shenhua.libs.log4k.ext.Utils
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by shenhua on 2018-01-10-0010.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class CollectionHandler : BaseHandler(), Formatter<Collection<*>> {

    override fun handle(obj: Any): Boolean {
        if (obj is Collection<*>) {
            val value = obj.firstOrNull()
            if (Utils.isPrimitiveType(value)) {
                val simpleName = obj.javaClass
                var msg = "%s size = %d" + Constant.BR
                msg = String.format(msg, simpleName, obj.size) + "║ "
                Log.d(LK.TAG, String.format(Box.getBox(), msg + obj.toString()))
                return true
            }
            Log.d(LK.TAG, String.format(Box.getBox(), format(obj)))
            return true
        }
        return false
    }

    override fun format(t: Collection<*>): String {
        val jsonArray = JSONArray()
        val simpleName = t.javaClass
        var msg = "%s size = %d" + Constant.BR
        msg = String.format(msg, simpleName, t.size) + "║ "
        val gson = Gson()
        t.map { it ->
            try {
                val objStr = gson.toJson(it)
                val jsonObject = JSONObject(objStr)
                jsonArray.put(jsonObject)
            } catch (e: JSONException) {
                LK.e("Invalid Json")
            }
        }
        var message = jsonArray.toString(Constant.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")
        return msg + message
    }
}