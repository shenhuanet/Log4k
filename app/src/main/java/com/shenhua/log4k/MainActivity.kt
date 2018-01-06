package com.shenhua.log4k

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shenhua.libs.log4k.LK

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LK.log("abc")
    }
}
