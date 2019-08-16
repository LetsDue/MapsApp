package com.karo.mapsapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build

var ItemsList:MutableList<Item>?=null
var CategoryList:MutableList<String>?=null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if(Build.VERSION.SDK_INT >= 21) {
            window.statusBarColor = 0xFF202058.toInt()
            window.navigationBarColor = 0xFFC8C8FF.toInt()

        }
    }

}

