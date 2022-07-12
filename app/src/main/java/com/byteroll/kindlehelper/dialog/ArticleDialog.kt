package com.byteroll.kindlehelper.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.byteroll.kindlehelper.R
import com.byteroll.kindlehelper.databinding.DlgArticleBinding
import com.byteroll.kindlehelper.utils.Utils

class ArticleDialog(context: Context) : Dialog(context, R.style.TransDialog), View.OnClickListener {

    private lateinit var binding: DlgArticleBinding

    private var article: String? = null

    private var activity: Activity? = null

    constructor(context: Context, article: String) : this(context) {
        this.article = article
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setUIFlags()
        initWidget()
        initComponents()
    }

    private fun init() {
        binding = DlgArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = Utils.getActivityOfContext(context)
    }

    private fun setUIFlags() {
        activity?.let {
            window?.let {
                it.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                it.statusBarColor = Color.TRANSPARENT
                val lp = it.attributes
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                }
                it.attributes = lp
            }
        }
    }

    private fun initWidget() {
        window?.let {
            val params = it.attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            params.gravity = Gravity.CENTER
            it.attributes = params
        }
    }

    private fun initComponents() {
        binding.apply {
            containerShow.text = article
            back.setOnClickListener(this@ArticleDialog)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                back -> {
                    dismiss()
                }
            }
        }
    }

}