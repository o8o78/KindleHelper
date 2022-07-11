package com.byteroll.kindlehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.byteroll.kindlehelper.utils.Utils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        Utils.setUiFlags(this)
    }

}