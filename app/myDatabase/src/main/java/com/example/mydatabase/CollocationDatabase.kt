package com.example.mydatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1)
abstract class CollocationDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
