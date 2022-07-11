package com.byteroll.kindlehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.byteroll.kindlehelper.adapter.HomeListAdapter
import com.byteroll.kindlehelper.databinding.ActivityMainBinding
import com.byteroll.kindlehelper.entity.Article
import com.byteroll.kindlehelper.utils.Utils
import com.byteroll.kindlehelper.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    val articles = mutableListOf(
        Article(
            "Game of throne",
            "There were fierce battles in gaza on Friday as Hamas and Israel both rejected the United Nations' call for cease-fire."
        ),
        Article(
            "Play of the UnNamed",
            "How does a bastard, orphan, son of a whole and Scottman, dropped in the middle of Caribean by province in squalr, grow up to be a hero and scolar?"
        )
    )

    val articleList = ArrayList<Article>()

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

    private fun init() {
        Utils.setUiFlags(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setSupportActionBar(binding.toolbar)
        initArticles()
        val layoutManager = GridLayoutManager(this, 1)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = HomeListAdapter(this, articleList)
        binding.recyclerView.adapter = adapter
    }

    private fun initArticles(){
        articleList.clear()
        repeat(50){
            val index = (0 until 1).random()
            articleList.add(articles[index])
        }
    }

}