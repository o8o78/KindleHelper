package com.byteroll.kindlehelper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.byteroll.kindlehelper.dao.ArticleDao
import com.byteroll.kindlehelper.entity.Article
import com.byteroll.kindlehelper.utils.ConstUtil

@Database(version = 1, entities = [Article::class])
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {

        private var instance: ArticleDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): ArticleDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                ConstUtil.DATABASE_ARTICLE_NAME
            ).build().apply {
                instance = this
            }
        }

    }

}