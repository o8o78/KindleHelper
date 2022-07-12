package com.byteroll.kindlehelper

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.byteroll.kindlehelper.App.Companion.context
import com.byteroll.kindlehelper.adapter.HomeListAdapter
import com.byteroll.kindlehelper.databinding.ActivityMainBinding
import com.byteroll.kindlehelper.dialog.TypeInDialog
import com.byteroll.kindlehelper.entity.Article
import com.byteroll.kindlehelper.utils.Utils
import com.byteroll.kindlehelper.utils.log
import com.byteroll.kindlehelper.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addManually -> {
                TypeInDialog(this, object : TypeInDialog.Result{
                    override fun onResult(result: String) {
                        reloadArticles(result)
                    }
                    override fun onError(error: String) {
                        "grab failed".log()
                    }
                }).show()
            }
            R.id.fromClipBoard ->{
                TypeInDialog(this, Utils.getFromClipBoard(), object : TypeInDialog.Result{
                    override fun onResult(result: String) {
                        reloadArticles(result)
                    }
                    override fun onError(error: String) {
                        "grab failed with error: $error".log()
                    }
                }).show()
            }
        }
        return true
    }

    private fun init() {
        Utils.setUiFlags(this)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel.initArticles()
        adapter= HomeListAdapter(this, viewModel.articleList)
        val layoutManager = GridLayoutManager(this, 1)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun reloadArticles(){
        reloadArticles("")
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun reloadArticles(content: String){
        if(!TextUtils.isEmpty(content)){
            viewModel.articleList.add(Article("", content))
        }
        if(viewModel.articleList.size<=0) return
        runOnUiThread {
            adapter.notifyDataSetChanged()
        }
    }

}