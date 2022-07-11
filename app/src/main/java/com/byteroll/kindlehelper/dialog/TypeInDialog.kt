package com.byteroll.kindlehelper.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.byteroll.kindlehelper.R
import com.byteroll.kindlehelper.databinding.DlgTypeInBinding

class TypeInDialog(context: Context): Dialog(context, R.style.TransDialog), View.OnClickListener {

    private lateinit var binding: DlgTypeInBinding

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

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}