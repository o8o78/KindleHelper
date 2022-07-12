package com.byteroll.kindlehelper.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.byteroll.kindlehelper.R
import com.byteroll.kindlehelper.databinding.DlgArticleBinding

class ArticleDialog(context: Context) : Dialog(context, R.style.TransDialog), View.OnClickListener {

    private lateinit var binding: DlgArticleBinding

    private var article: String? = null

    constructor(context: Context, article: String): this(context){
        this.article = article
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initWidget()
        initComponents()
    }

    private fun init(){
        binding = DlgArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    private fun initComponents(){
        binding.apply{
            containerShow.text = article
            back.setOnClickListener(this@ArticleDialog)
        }
    }

    override fun onClick(v: View?) {
        with(binding){
            when(v){
                back ->{
                    dismiss()
                }
            }
        }
    }

}