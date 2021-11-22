package com.example.mydatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydatabase.dao.WordDao
import com.example.mydatabase.model.Word

@Database(entities = [Word::class], version = 1)
abstract class CollocationDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
