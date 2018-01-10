package com.shenhua.log4k

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shenhua.libs.log4k.LK
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = Uri.parse("content://" + "com.shenhua.libs.log4k" + "/user")

        val user = User("zhangsan", 12)

        val bundle = Bundle()
        bundle.putInt("a", 10)
        bundle.putSerializable("user", user)

        val map = HashMap<String, Any>()
        map.put("abc", 123)
        map.put("def", true)
        map.put("user", user)

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        LK.log(intent)

        val ids = ArrayList<String>()
        ids.add("123")
        ids.add("456")

        val users = ArrayList<User>()
        users.add(User("zhangsan", 12))
        users.add(User("lisi", 14))


        LK.log(ids)
    }

    data class User(val name: String, val age: Int) : Serializable
}
