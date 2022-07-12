package com.byteroll.kindlehelper.dao

import androidx.room.*
import com.byteroll.kindlehelper.entity.Article

@Dao
interface ArticleDao {

    @Insert
    fun insertArticle(article: Article): Long

    @Update
    fun updateArticle(newArticle: Article)

    @Query("select * from Article")
    fun loadAllArticles(): List<Article>

    @Query("delete from Article")
    fun deleteArticle()

}