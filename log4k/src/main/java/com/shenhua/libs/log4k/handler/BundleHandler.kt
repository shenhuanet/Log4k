package com.shenhua.libs.log4k.handler

import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.shenhua.libs.log4k.LK
import com.shenhua.libs.log4k.core.BaseHandler
import com.shenhua.libs.log4k.core.Box
import com.shenhua.libs.log4k.ext.Constant
import com.shenhua.libs.log4k.ext.Utils
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by shenhua on 2018-01-09-0009.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class BundleHandler : BaseHandler() {

    override fun handle(obj: Any): Boolean {
        if (obj is Bundle) {
            val map = HashMap<String, Any>()
            val gson = Gson()
            for (key in obj.keySet()) {
                try {
                    if (Utils.isPrimitiveType(obj.get(key))) {
                        map.put(key.toString(), obj.get(key))
                    } else {
                        map.put(key.toString(), JSONObject(gson.toJson(obj.get(key))))
                    }
                } catch (e: JSONException) {
                    LK.e("Invalid Json")
                }
            }
            val jsonObject = JSONObject(map)
            var message = jsonObject.toString(Constant.JSON_INDENT)
            message = message.replace("\n".toRegex(), "\n║ ")
            val msg = obj.javaClass.toString() + Constant.BR + "║ "
            Log.d(LK.TAG, String.format(Box.getBox(), msg + message))
            return true
        }
        return false
    }
}