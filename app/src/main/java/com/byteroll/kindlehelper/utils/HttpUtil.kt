package com.byteroll.kindlehelper.utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {

    interface HttpCallbackListener{
        fun onFinish(response: String)
        fun onFailed(e: String)
    }

    fun sendHttpRequest(address: String, listener: HttpCallbackListener){
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                //finish回调
                listener.onFinish(response.toString())
            } catch (e: Exception){
                e.printStackTrace()
                listener.onFailed(e.toString())
            } finally {
                connection?.disconnect()
            }
        }
    }

}