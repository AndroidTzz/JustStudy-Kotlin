package com.zero.tzz.kotlinbasemvplibs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tzz.kotlin.baselibs.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var index = 0
        tv.setOnClickListener {
            index++
            ToastUtil.show("啊啊啊啊 $index")
        }
    }
}
