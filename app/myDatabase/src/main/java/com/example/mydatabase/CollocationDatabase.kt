package com.example.mydatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydatabase.dao.CollocationDao
import com.example.mydatabase.dao.SentenceDao
import com.example.mydatabase.dao.WordDao
import com.example.mydatabase.model.Collocation
import com.example.mydatabase.model.Sentence
import com.example.mydatabase.model.Word

@Database(entities = [Word::class, Collocation::class, Sentence::class], version = 1)
abstract class CollocationDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun collocationDao(): CollocationDao
    abstract fun sentenceDao(): SentenceDao
}
