package com.byteroll.kindlehelper.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(val title: String, val content: String){

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}