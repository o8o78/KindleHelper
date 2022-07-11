package com.byteroll.kindlehelper.viewmodels

import androidx.lifecycle.ViewModel
import com.byteroll.kindlehelper.entity.Article

class MainViewModel : ViewModel() {

    private val articles = mutableListOf(
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

    fun initArticles() {
        articleList.clear()
        repeat(50) {
            val index = (0 until 1).random()
            articleList.add(articles[index])
        }
    }

}