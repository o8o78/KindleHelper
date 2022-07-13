package com.byteroll.kindlehelper.utils

import androidx.annotation.WorkerThread
import com.byteroll.kindlehelper.App
import com.byteroll.kindlehelper.dao.ArticleDao
import com.byteroll.kindlehelper.database.ArticleDatabase
import com.byteroll.kindlehelper.entity.Article
import kotlin.concurrent.thread

object ArticleDatabaseEditor {

    private var articleDao: ArticleDao = ArticleDatabase.getDatabase(App.context).articleDao()

    fun insertArticle(article: Article){
        thread {
            articleDao.insertArticle(article)
        }
    }

    fun updateArticle(article: Article){
        thread {
            articleDao.updateArticle(article)
        }
    }

    @WorkerThread
    fun loadAllArticles(): List<Article>{
        return articleDao.loadAllArticles()
    }

    fun clearArticles(){
        thread {
            articleDao.clearArticles()
        }
    }

}