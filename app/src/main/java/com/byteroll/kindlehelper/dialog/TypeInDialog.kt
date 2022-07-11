package com.byteroll.kindlehelper.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.byteroll.kindlehelper.R
import com.byteroll.kindlehelper.databinding.DlgTypeInBinding
import com.byteroll.kindlehelper.utils.HttpUtil
import com.byteroll.kindlehelper.utils.toast

class TypeInDialog(context: Context,val cbk: Result): Dialog(context, R.style.TransDialog), View.OnClickListener {

    private lateinit var binding: DlgTypeInBinding

    interface Result{
        fun onResult(result: String)
        fun onError(error: String)
    }

    private var clipData : String? = null

    constructor(context: Context, msg: String, cbk: Result): this(context, cbk){
        clipData = msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DlgTypeInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWidget()
        init()
    }

    private fun initWidget(){
        window?.let {
            val params = it.attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            params.gravity = Gravity.CENTER
            it.attributes = params
        }
    }

    private fun init(){
         with(binding){
             confirm.setOnClickListener(this@TypeInDialog)
             confirm.isClickable = false
             if (clipData!=null){
                 edit.setText(clipData)
             }
             edit.addTextChangedListener{
                 confirm.isClickable = !TextUtils.isEmpty(edit.text)
             }
         }

    }

    override fun onClick(v: View?) {
        with(binding){
            when(v){
                confirm->{
                    processUrl(edit.editableText.toString())
                }
            }
        }
    }

    private fun processUrl(url: String){
        if (checkFormat(url)){
            HttpUtil.sendHttpRequest(url, object : HttpUtil.HttpCallbackListener{
                override fun onFailed(e: String) {
                    cbk.onError(e)
                }
                override fun onFinish(response: String) {
                    val result = processResponse(response)
                    cbk.onResult(result)
                }
            })
        } else {
            "invalid url format".toast()
        }
    }

    private fun processResponse(response: String) : String{
        val regScript = Regex("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>")
        val regStyle = Regex("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>")
        val regHtml = Regex("<[^>]+>")
        val regEnter = Regex("\\s*")
        var ret = ""
        ret = regScript.replace(response, "")
        ret = regStyle.replace(ret, "")
        ret = regHtml.replace(ret, "")
        ret = ret.trim()
        ret = regEnter.replace(ret, "")
        return ret
    }

    private fun checkFormat(url: String) : Boolean{
        val regex = Regex("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]")
        return regex.matches(url)
    }

}