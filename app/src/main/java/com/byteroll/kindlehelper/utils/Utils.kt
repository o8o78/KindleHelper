package com.byteroll.kindlehelper.utils

import android.app.Activity
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.byteroll.kindlehelper.App

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
        fun getFromClipBoard(): String{
            try {
                val cm = App.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = cm.primaryClip
                if (clip!=null && clip.itemCount>0){
                    val text = clip.getItemAt(0).coerceToText(App.context) as CharSequence
                    if (!TextUtils.isEmpty(text)){
                        return text.toString()
                    }
                }
            } catch (e: Exception){
                e.toString().log()
            }
            return ""
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

fun String.toast(){
    Toast.makeText(App.context, this, Toast.LENGTH_SHORT).show()
}