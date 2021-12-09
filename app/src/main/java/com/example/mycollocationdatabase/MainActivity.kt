package com.example.mycollocationdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import com.example.mydatabase.CollocationDatabase
import com.example.mydatabase.dao.SentenceDao


class MainActivity : AppCompatActivity() {

    lateinit var sentenceDao: SentenceDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var context = this
        val db = initializeDatabase()
        sentenceDao = db.sentenceDao()

    }


    fun initializeDatabase(): CollocationDatabase {
        return databaseBuilder(
            applicationContext,
            CollocationDatabase::class.java, "collocation_database"
        ).build()
    }

}
