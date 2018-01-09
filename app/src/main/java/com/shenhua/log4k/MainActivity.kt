package com.shenhua.log4k

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

        val bundle = Bundle()
        bundle.putInt("a", 10)
        bundle.putSerializable("user", User("zhangsan", 12))

        val map = HashMap<String ,Any>()
        map.put("abc",123)
        map.put("def",true)
        map.put("user",User("zhangsan", 123))

        LK.log(map)
    }

    data class User(val name: String, val age: Int) : Serializable
}
