package com.byteroll.kindlehelper.utils

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View

class Utils {
    companion object{
        fun setUiFlags(activity: Activity){
            with(activity){
                window.statusBarColor = Color.TRANSPARENT
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
    }
}

private val LOG_MAX_LENGTH = 3 * 1024

fun String.log(){
    val tag = "AppLog"
    val strLength = this.length
    var start = 0
    var end = LOG_MAX_LENGTH
    for (i in 0..100){
        if (strLength > end){
            Log.d(tag + i, this.substring(start, end))
            start = end
            end += LOG_MAX_LENGTH
        } else{
            Log.d(tag+i, this.substring(start, strLength))
            break
        }
    }
}