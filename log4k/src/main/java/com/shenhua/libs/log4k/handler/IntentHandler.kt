package com.shenhua.libs.log4k.handler

import android.content.Intent
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
 * Created by shenhua on 2018-01-10-0010.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class IntentHandler : BaseHandler() {

    override fun handle(obj: Any): Boolean {
        if (obj is Intent) {
            val msg = obj.javaClass.toString() + Constant.BR + "║ "
            val jsonObject = JSONObject()
            jsonObject.put("Scheme", obj.scheme)
            jsonObject.put("Action", obj.action)
            jsonObject.put("DataString", obj.dataString)
            jsonObject.put("Type", obj.type)
            jsonObject.put("Package", obj.`package`)
            jsonObject.put("ComponentInfo", obj.component)
            jsonObject.put("Categories", obj.categories)
            if (obj.extras != null) {
                jsonObject.put("Extras", JSONObject(parseBundleString(obj.extras)))
            }
            var message = jsonObject.toString(Constant.JSON_INDENT)
            message = message.replace("\n".toRegex(), "\n║ ")
            Log.d(LK.TAG, String.format(Box.getBox(), msg + message))
            return true
        }
        return false
    }

    private fun parseBundleString(extras: Bundle): String {
        val map = HashMap<String, Any>()
        val gson = Gson()
        for (key in extras.keySet()) {
            val isPrimitiveType = Utils.isPrimitiveType(extras.get(key))
            try {
                if (isPrimitiveType) {
                    map.put(key.toString(), extras.get(key))
                } else {
                    map.put(key.toString(), JSONObject(gson.toJson(extras.get(key))))
                }
            } catch (e: JSONException) {
                LK.e("Invalid Json")
            }
        }
        return map.toString()
    }
}