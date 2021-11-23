package com.example.mycollocationdatabase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import com.example.mydatabase.CollocationDatabase
import com.example.mydatabase.model.Word
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var context = this

        val db = initializeDatabase()
        val wordDao = db.wordDao()
        var list: List<Word> = ArrayList<Word>()

        GlobalScope.launch {


            var exampleWord1 = Word(
                "home", null, Word.NOUN
            )
            var exampleWord2 = Word(
                "fast", null, Word.ADJECTIVE
            )
            var exampleWord3 = Word(
                "play", null, Word.VERB
            )
//            wordDao.insert(exampleWord1)
//            wordDao.insert(exampleWord2)
//            wordDao.insert(exampleWord3)

            list = wordDao.getAll()

            Handler(Looper.getMainLooper()).postDelayed(
                {
                    Toast.makeText(context, "pobrano: " + list.size, Toast.LENGTH_LONG).show();
                },
                0
            )
        }

    }

    fun initializeDatabase(): CollocationDatabase {
        return databaseBuilder(
            applicationContext,
            CollocationDatabase::class.java, "collocation_database"
        ).build()
    }
}