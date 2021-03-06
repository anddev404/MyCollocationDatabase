package com.example.mycollocationdatabase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import com.example.mydatabase.CollocationDatabase
import com.example.mydatabase.dao.CollocationDao
import com.example.mydatabase.dao.SentenceDao
import com.example.mydatabase.dao.WordDao
import com.example.mydatabase.model.Collocation
import com.example.mydatabase.model.Sentence
import com.example.mydatabase.model.Word
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var sentenceDao: SentenceDao
    lateinit var wordDao: WordDao
    lateinit var collocationDao: CollocationDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var context = this
        val db = CollocationDatabase.initializeDatabase(context, "qwe")

        collocationDao = db.collocationDao()
        sentenceDao = db.sentenceDao()
        wordDao = db.wordDao()

        GlobalScope.launch {

            collocationDao.insert(Collocation("Test", "Translation", "relation", Random.nextInt()))
            sentenceDao.insert(Sentence("Hey", "Hej"))
            wordDao.insert(Word("Home", "Dom", "Hołm", Random.nextInt()))

            var c = collocationDao.getNumberOfCollocations()
            var s = sentenceDao.getNumberOfSentences()
            var w = wordDao.getNumberOfWords()

            var jeden = collocationDao.searchInCollocations("tra")
            var dwa = collocationDao.searchInTranslations("lat")

            var trzy = sentenceDao.searchInSentences("he")
            var cztery = sentenceDao.searchInTranslations("ej")

            var listWords=wordDao.getAll()

            Log.d(
                "DATABASE_MARCIN",
                "return ${jeden.size} ${dwa.size} ${trzy.size} ${cztery.size}"
            );

            Handler(Looper.getMainLooper()).postDelayed(
                {
                    Toast.makeText(context, "=== $c, $s, $w ===", Toast.LENGTH_LONG).show();
                },
                0
            )


        }
    }


}
