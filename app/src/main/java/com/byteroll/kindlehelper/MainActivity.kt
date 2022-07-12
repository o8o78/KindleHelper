package com.byteroll.kindlehelper

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.byteroll.kindlehelper.adapter.HomeListAdapter
import com.byteroll.kindlehelper.dao.ArticleDao
import com.byteroll.kindlehelper.database.ArticleDatabase
import com.byteroll.kindlehelper.databinding.ActivityMainBinding
import com.byteroll.kindlehelper.dialog.TypeInDialog
import com.byteroll.kindlehelper.entity.Article
import com.byteroll.kindlehelper.utils.Utils
import com.byteroll.kindlehelper.utils.log
import com.byteroll.kindlehelper.viewmodels.MainViewModel
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var articleDao: ArticleDao

    private lateinit var adapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        processShare()
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
                        thread {
                            articleDao.insertArticle(Article("", result))
                            reloadArticles()
                        }
                    }
                    override fun onError(error: String) {
                        "grab failed".log()
                    }
                }).show()
            }
            R.id.fromClipBoard ->{
                TypeInDialog(this, Utils.getFromClipBoard(), object : TypeInDialog.Result{
                    override fun onResult(result: String) {
                        thread {
                            articleDao.insertArticle(Article("", result))
                            reloadArticles()
                        }
                    }
                    override fun onError(error: String) {
                        "grab failed with error: $error".log()
                    }
                }).show()
            }
            R.id.clearAll ->{
                thread {
                    clearArticles()
                }
            }
        }
        return true
    }

    private fun init() {
        Utils.setUiFlags(this)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        articleDao = ArticleDatabase.getDatabase(this).articleDao()
        adapter = HomeListAdapter(this, viewModel.articleList)
        val layoutManager = GridLayoutManager(this, 1)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        thread {
            reloadArticles()
        }
    }

    private fun processShare(){
        if (intent.action==Intent.ACTION_SEND){
            if ("text/plain" == intent.type){
                handleSendText(intent)
            }
        }
    }

    private fun handleSendText(intent: Intent){
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            TypeInDialog(this, it, object : TypeInDialog.Result{
                override fun onResult(result: String) {
                    thread {
                        articleDao.insertArticle(Article("", result))
                        reloadArticles()
                    }
                }
                override fun onError(error: String) {
                    "grab failed with error: $error".log()
                }
            }).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun reloadArticles(){
        viewModel.articleList.clear()
        viewModel.articleList.addAll(articleDao.loadAllArticles().asReversed())
        if(viewModel.articleList.size<=0) return
        runOnUiThread {
            adapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun clearArticles(){
        articleDao.deleteArticle()
        viewModel.articleList.clear()
        runOnUiThread {
            adapter.notifyDataSetChanged()
        }
    }

}