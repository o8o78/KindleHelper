package com.byteroll.kindlehelper.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.byteroll.kindlehelper.databinding.ActivitySendBinding
import com.byteroll.kindlehelper.utils.Utils
import com.byteroll.kindlehelper.viewmodels.SendViewModel

class SendActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendBinding

    private lateinit var viewModel: SendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setUIFlags()
        getExtraString()
    }

    private fun init(){
        binding = ActivitySendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SendViewModel::class.java)
    }

    private fun setUIFlags(){
        Utils.setUiFlags(this)
    }

    private fun getExtraString() {
        viewModel.article = intent.getStringExtra("article") ?: ""
    }

}