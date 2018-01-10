package com.shenhua.log4k

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.shenhua.libs.log4k.LK
import com.shenhua.libs.log4k.core.BaseHandler
import com.shenhua.libs.log4k.core.Box
import com.shenhua.libs.log4k.core.Formatter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintWriter
import java.io.Serializable
import java.io.StringWriter

/**
 * Created by shenhua on 2018/1/6.
 * Email shenhuanet@126.com
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = User("shenhua", 23)

        // 1 string
        val string = "Hello world."

        // 2 json
        val json = "{\n" +
                "\t\"key1\": true,\n" +
                "\t\"key2\": 123,\n" +
                "\t\"key3\": {\n" +
                "\t\t\"a\": \"123132\",\n" +
                "\t\t\"b\": 1.23\n" +
                "\t},\n" +
                "\t\"key4\": [{\n" +
                "\t\t\"key1\": true,\n" +
                "\t\t\"key2\": 123\n" +
                "\t}, {\n" +
                "\t\t\"key1\": true,\n" +
                "\t\t\"key2\": 123\n" +
                "\t}]\n" +
                "}"

        // 3 Collection
        val ids = ArrayList<String>()
        ids.add("123")
        ids.add("456")

        val users = ArrayList<User>()
        users.add(User("zhangsan", 12))
        users.add(User("lisi", 14))

        // 4 Map
        val map = HashMap<String, Any>()
        map.put("abc", 123)
        map.put("def", true)

        // 5 Bundle
        val bundle = Bundle()
        bundle.putInt("a", 10)
        bundle.putSerializable("user", user)

        // 6 Intent
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))

        // 7 Object
        val uri = Uri.parse("content://" + "com.shenhua.libs.log4k" + "/user")

        stringBtn.setOnClickListener { LK.log(string) }
        jsonBtn.setOnClickListener { LK.log(json) }
        collectBtn1.setOnClickListener { LK.log(ids) }
        collectBtn2.setOnClickListener { LK.log(users) }
        mapBtn.setOnClickListener { LK.log(map) }
        bundleBtn.setOnClickListener { LK.log(bundle) }
        intentBtn.setOnClickListener { LK.log(intent) }
        objectBtn.setOnClickListener { LK.log(uri) }
        dBtn.setOnClickListener { LK.d(string) }
        customBtn.setOnClickListener {
            LK.addHandler(ThrowableHandler())
            LK.log(NullPointerException("This is a NullPointerException."))
        }
    }

    inner class ThrowableHandler : BaseHandler(), Formatter<Throwable> {

        override fun handle(obj: Any): Boolean {
            if (obj is Throwable) {
                Log.e(LK.TAG, String.format(Box.getBox(), format(obj)))
                return true
            }
            return false
        }

        override fun format(t: Throwable): String {
            val sw = StringWriter(256)
            val pw = PrintWriter(sw, false)
            t.printStackTrace(pw)
            pw.flush()
            var message = sw.toString()
            message = message.replace("\n".toRegex(), "\n║ ")
            return message
        }
    }

    data class User(val name: String, val age: Int) : Serializable
}
