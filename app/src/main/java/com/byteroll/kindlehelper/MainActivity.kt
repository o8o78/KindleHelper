package com.byteroll.kindlehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.byteroll.kindlehelper.databinding.ActivityMainBinding
import com.byteroll.kindlehelper.utils.Utils
import com.byteroll.kindlehelper.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        Utils.setUiFlags(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }



}