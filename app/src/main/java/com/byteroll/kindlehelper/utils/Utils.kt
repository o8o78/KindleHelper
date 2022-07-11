package com.byteroll.kindlehelper.utils

import android.app.Activity
import android.graphics.Color
import android.view.VerifiedInputEvent
import android.view.View

class Utils {
    companion object{
        fun setUiFlags(activity: Activity){
            with(activity){
                window.statusBarColor = Color.TRANSPARENT
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
    }
}